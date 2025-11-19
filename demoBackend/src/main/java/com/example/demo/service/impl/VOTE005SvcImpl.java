package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.VoteActivitiesEntity;
import com.example.demo.entity.VoteOptionsEntity;
import com.example.demo.entity.VoteRecordsEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.InsertFailException;
import com.example.demo.exception.UpdateFailException;
import com.example.demo.model.ReturnCodeAndDescEnum;
import com.example.demo.repository.VoteActivitiesRepository;
import com.example.demo.repository.VoteOptionsRepository;
import com.example.demo.repository.VoteRecordsRepository;
import com.example.demo.service.VOTE005Svc;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class VOTE005SvcImpl implements VOTE005Svc {

    private final VoteActivitiesRepository voteActivitiesRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    private final VoteRecordsRepository voteRecordsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Res<VOTE005Tranrs> vote005(Long activityId, Req<VOTE005Tranrq> requestBody) throws DataNotFoundException, InsertFailException, UpdateFailException {

        log.info("[VOTE-005] 新增投票計數 API 啟動");

        if (voteRecordsRepository.existsByUserIdAndActivityId(requestBody.getTranrq().getUserId(), activityId)) {
            log.warn("[VOTE-005] 此用戶已投票，不可重複投票");
            return null;
        }

        VoteActivitiesEntity voteActivitiesEntity = voteActivitiesRepository.findById(activityId).orElseThrow(() -> {
            log.error("[VOTE-005] 表格 vote_activities 查無 activityId 為 {} 的資料", activityId);
            return new DataNotFoundException();
        });

        if (voteActivitiesEntity.getStartTime().isAfter(LocalDateTime.now())) {
            log.warn("[VOTE-005] 投票尚未開始");
            return null;
        }

        if (voteActivitiesEntity.getEndTime().isBefore(LocalDateTime.now())) {
            log.warn("[VOTE-005] 投票已結束");
            return null;
        }

        VoteOptionsEntity voteOptionsEntity = voteOptionsRepository.findByActivityIdAndOptionSymbol(activityId, requestBody.getTranrq().getOptionSymbol()).orElseThrow(() -> {
            log.error("[VOTE-005] 表格 vote_options 查無 activityId 為 {} 的資料", activityId);
            return new DataNotFoundException();
        });

        voteOptionsEntity.setVoteCount(voteOptionsEntity.getVoteCount() + 1);

        try {
            voteOptionsRepository.save(voteOptionsEntity);
        } catch (Exception e) {
            log.error("[VOTE-005] 更新資料至 vote_options 表格有誤");
            throw new UpdateFailException();
        }

        VoteRecordsEntity voteRecordsEntity = new VoteRecordsEntity();
        voteRecordsEntity.setUserId(requestBody.getTranrq().getUserId());
        voteRecordsEntity.setActivityId(activityId);
        voteRecordsEntity.setOptionSymbol(requestBody.getTranrq().getOptionSymbol());

        try {
            voteRecordsRepository.save(voteRecordsEntity);
        } catch (Exception e) {
            log.error("[VOTE-005] 新增資料至 vote_records 表格有誤");
            throw new InsertFailException();
        }

        log.info("[VOTE-005] 新增投票計數 API 執行完成");

        return new Res<VOTE005Tranrs>(new ResMwHeader(ReturnCodeAndDescEnum.SUCCESS), new VOTE005Tranrs(activityId));
    }
}

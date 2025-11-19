package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.VoteActivitiesEntity;
import com.example.demo.entity.VoteOptionsEntity;
import com.example.demo.entity.VoteRecordsEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.DeleteFailException;
import com.example.demo.exception.InsertFailException;
import com.example.demo.exception.UpdateFailException;
import com.example.demo.model.ReturnCodeAndDescEnum;
import com.example.demo.repository.VoteActivitiesRepository;
import com.example.demo.repository.VoteOptionsRepository;
import com.example.demo.repository.VoteRecordsRepository;
import com.example.demo.service.VOTE006Svc;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class VOTE006SvcImpl implements VOTE006Svc {

    private final VoteActivitiesRepository voteActivitiesRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    private final VoteRecordsRepository voteRecordsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Res<VOTE006Tranrs> vote006(Long activityId, Req<VOTE006Tranrq> requestBody) throws DataNotFoundException, DeleteFailException, UpdateFailException {

        log.info("[VOTE-006] 刪除投票計數 API 啟動");

        VoteRecordsEntity voteRecordsEntity = voteRecordsRepository.findByUserIdAndActivityId(requestBody.getTranrq().getUserId(), activityId).orElseThrow(() -> {
            log.error("[VOTE-006] 表格 vote_records 查無投票資料");
            return new DataNotFoundException();
        });

        VoteActivitiesEntity voteActivitiesEntity = voteActivitiesRepository.findById(activityId).orElseThrow(() -> {
            log.error("[VOTE-006] 表格 vote_activities 查無 activityId 為 {} 的資料", activityId);
            return new DataNotFoundException();
        });

        if (voteActivitiesEntity.getEndTime().isBefore(LocalDateTime.now())) {
            log.warn("[VOTE-006] 投票已結束");
            return null;
        }

        VoteOptionsEntity voteOptionsEntity = voteOptionsRepository.findByActivityIdAndOptionSymbol(activityId, voteRecordsEntity.getOptionSymbol()).orElseThrow(() -> {
            log.error("[VOTE-006] 表格 vote_options 查無 activityId 為 {} 的資料", activityId);
            return new DataNotFoundException();
        });

        voteOptionsEntity.setVoteCount(voteOptionsEntity.getVoteCount() - 1);

        try {
            voteOptionsRepository.save(voteOptionsEntity);
        } catch (Exception e) {
            log.error("[VOTE-006] 更新資料至 vote_options 表格有誤");
            throw new UpdateFailException();
        }

        try {
            voteRecordsRepository.delete(voteRecordsEntity);
        } catch (Exception e) {
            log.error("[VOTE-006] 刪除表格 vote_records 的資料有誤");
            throw new DeleteFailException();
        }

        log.info("[VOTE-006] 刪除投票計數 API 執行完成");

        return new Res<VOTE006Tranrs>(new ResMwHeader(ReturnCodeAndDescEnum.SUCCESS), new VOTE006Tranrs(activityId));
    }
}

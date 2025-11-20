package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.VoteActivitiesEntity;
import com.example.demo.entity.VoteRecordsEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.UpdateFailException;
import com.example.demo.model.ReturnCodeAndDescEnum;
import com.example.demo.repository.VoteActivitiesRepository;
import com.example.demo.repository.VoteOptionsRepository;
import com.example.demo.repository.VoteRecordsRepository;
import com.example.demo.service.VOTE008Svc;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class VOTE008SvcImpl implements VOTE008Svc {

    private final VoteActivitiesRepository voteActivitiesRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    private final VoteRecordsRepository voteRecordsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Res<VOTE008Tranrs> vote008(Long activityId, Req<VOTE008Tranrq> requestBody) throws DataNotFoundException, UpdateFailException {

        log.info("[VOTE-008] 修改投票選項 API 啟動");

        Long userId = requestBody.getTranrq().getUserId();
        String optionSymbol = requestBody.getTranrq().getOptionSymbol();

        VoteRecordsEntity voteRecordsEntity = voteRecordsRepository.findByUserIdAndActivityId(userId, activityId).orElseThrow(() -> {
            log.error("[VOTE-008] 表格 vote_records 查無投票資料");
            return new DataNotFoundException();
        });

        VoteActivitiesEntity voteActivitiesEntity = voteActivitiesRepository.findById(activityId).orElseThrow(() -> {
            log.error("[VOTE-008] 表格 vote_activities 查無 activityId 為 {} 的資料", activityId);
            return new DataNotFoundException();
        });

        if (voteActivitiesEntity.getEndTime().isBefore(LocalDateTime.now())) {
            log.warn("[VOTE-008] 投票已結束");
            return null;
        }

        voteOptionsRepository.findByActivityIdAndOptionSymbol(activityId, optionSymbol).orElseThrow(() -> {
            log.error("[VOTE-008] 表格 vote_options 查無 activityId 為 {}、選項為 {} 的資料", activityId, optionSymbol);
            return new DataNotFoundException();
        });

        try {
            voteRecordsEntity.setOptionSymbol(optionSymbol);
        } catch (Exception e) {
            log.error("[VOTE-008] 更新表格 vote_records 異常");
            throw new UpdateFailException();
        }

        log.info("[VOTE-008] 修改投票選項 API 執行完成");

        return new Res<VOTE008Tranrs>(new ResMwHeader(ReturnCodeAndDescEnum.SUCCESS), new VOTE008Tranrs(activityId));
    }
}

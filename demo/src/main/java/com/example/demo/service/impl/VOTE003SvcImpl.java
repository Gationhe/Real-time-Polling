package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.VoteActivitiesEntity;
import com.example.demo.entity.VoteOptionsEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.model.ReturnCodeAndDescEnum;
import com.example.demo.repository.VoteActivitiesRepository;
import com.example.demo.repository.VoteOptionsRepository;
import com.example.demo.service.VOTE003Svc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VOTE003SvcImpl implements VOTE003Svc {

    private final VoteActivitiesRepository voteActivitiesRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    @Override
    public Res<VOTE003Tranrs> vote003(Long activityId) throws DataNotFoundException {

        log.info("[VOTE-003] 查詢投票 API 啟動");

        VoteActivitiesEntity voteActivitiesEntity = voteActivitiesRepository.findById(activityId).orElseThrow(() -> {
            log.error("[VOTE-003] 查無 activityId 為 {} 的資料", activityId);
            return new DataNotFoundException();
        });

        List<VoteOptionsEntity> voteOptionsEntities = voteOptionsRepository.findByActivityId(activityId);

        if (voteOptionsEntities.isEmpty()) {
            log.error("[VOTE-003] 查無 activityId 為 {} 的資料", activityId);
            throw new DataNotFoundException();
        }

        VOTE003Tranrs vote003Tranrs = new VOTE003Tranrs();
        vote003Tranrs.setActivityId(activityId);
        vote003Tranrs.setTitle(voteActivitiesEntity.getTitle());
        vote003Tranrs.setDescription(voteActivitiesEntity.getDescription());
        vote003Tranrs.setStartTime(voteActivitiesEntity.getStartTime());
        vote003Tranrs.setEndTime(voteActivitiesEntity.getEndTime());

        List<VOTE003TranrsOption> options = new ArrayList<>();

        for (VoteOptionsEntity voteOptionsEntity : voteOptionsEntities) {
            VOTE003TranrsOption vote003TranrsOption = new VOTE003TranrsOption();
            vote003TranrsOption.setOptionSymbol(voteOptionsEntity.getOptionSymbol());
            vote003TranrsOption.setDescription(voteOptionsEntity.getDescription());
            options.add(vote003TranrsOption);
        }

        vote003Tranrs.setOptions(options);

        log.info("[VOTE-003] 查詢投票 API 執行完成");

        return new Res<VOTE003Tranrs>(new ResMwHeader(ReturnCodeAndDescEnum.SUCCESS), vote003Tranrs);
    }
}

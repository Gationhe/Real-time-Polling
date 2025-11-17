package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.VoteActivitiesEntity;
import com.example.demo.entity.VoteOptionsEntity;
import com.example.demo.exception.InsertFailException;
import com.example.demo.model.ReturnCodeAndDescEnum;
import com.example.demo.repository.VoteActivitiesRepository;
import com.example.demo.repository.VoteOptionsRepository;
import com.example.demo.service.VOTE001Svc;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VOTE001SvcImpl implements VOTE001Svc {

    private final VoteActivitiesRepository voteActivitiesRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Res<VOTE001Tranrs> vote001(Req<VOTE001Tranrq> requestBody) throws InsertFailException {

        log.info("[VOTE-001] 新增投票 API 啟動");

        VOTE001Tranrq vote001Tranrq = requestBody.getTranrq();

        VoteActivitiesEntity voteActivitiesEntity = new VoteActivitiesEntity();
        voteActivitiesEntity.setUserId(vote001Tranrq.getUserId());
        voteActivitiesEntity.setTitle(vote001Tranrq.getTitle());
        voteActivitiesEntity.setDescription(vote001Tranrq.getDescription());
        voteActivitiesEntity.setStartTime(vote001Tranrq.getStartTime());
        voteActivitiesEntity.setEndTime(vote001Tranrq.getEndTime());

        Long activityId;

        try {
            activityId = voteActivitiesRepository.save(voteActivitiesEntity).getId();
        } catch (Exception e) {
            log.error("[VOTE-001] 新增資料至 vote_activities 表格有誤");
            throw new InsertFailException();
        }

        List<VOTE001TranrqOption> vote001TranrqOptions = vote001Tranrq.getOptions();
        List<VoteOptionsEntity> voteOptionsEntities = new ArrayList<>();

        for (VOTE001TranrqOption vote001TranrqOption : vote001TranrqOptions) {
            VoteOptionsEntity voteOptionsEntity = new VoteOptionsEntity();
            voteOptionsEntity.setActivityId(activityId);
            voteOptionsEntity.setOptionSymbol(vote001TranrqOption.getOptionSymbol());
            voteOptionsEntity.setDescription(vote001TranrqOption.getDescription());
            voteOptionsEntities.add(voteOptionsEntity);
        }

        try {
            voteOptionsRepository.saveAll(voteOptionsEntities);
        } catch (Exception e) {
            log.error("[VOTE-001] 新增資料至 vote_options 表格有誤");
            throw new InsertFailException();
        }

        log.info("[VOTE-001] 新增投票 API 執行完成");

        return new Res<VOTE001Tranrs>(new ResMwHeader(ReturnCodeAndDescEnum.SUCCESS), new VOTE001Tranrs(activityId));
    }
}

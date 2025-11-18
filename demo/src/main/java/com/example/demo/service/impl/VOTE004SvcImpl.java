package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.VoteActivitiesEntity;
import com.example.demo.entity.VoteOptionsEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.UpdateFailException;
import com.example.demo.model.ReturnCodeAndDescEnum;
import com.example.demo.repository.VoteActivitiesRepository;
import com.example.demo.repository.VoteOptionsRepository;
import com.example.demo.service.VOTE004Svc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VOTE004SvcImpl implements VOTE004Svc {

    private final VoteActivitiesRepository voteActivitiesRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    @Override
    public Res<VOTE004Tranrs> vote004(Long activityId, Req<VOTE004Tranrq> requestBody) throws DataNotFoundException, UpdateFailException {

        log.info("[VOTE-004] 更新投票 API 啟動");

        VoteActivitiesEntity voteActivitiesEntity = voteActivitiesRepository.findById(activityId).orElseThrow(() -> {
            log.error("[VOTE-004] 查無 activityId 為 {} 的資料", activityId);
            return new DataNotFoundException();
        });

        VOTE004Tranrq vote004Tranrq = requestBody.getTranrq();

        if (vote004Tranrq.getTitle() != null) voteActivitiesEntity.setTitle(vote004Tranrq.getTitle());

        if (vote004Tranrq.getDescription() != null)
            voteActivitiesEntity.setDescription(vote004Tranrq.getDescription());

        if (vote004Tranrq.getStartTime() != null)
            voteActivitiesEntity.setStartTime(vote004Tranrq.getStartTime());

        if (vote004Tranrq.getEndTime() != null)
            voteActivitiesEntity.setEndTime(vote004Tranrq.getEndTime());

        try {
            voteActivitiesRepository.save(voteActivitiesEntity);
        } catch (Exception e) {
            log.error("[VOTE-004] 更新表格 vote_activities 異常");
            throw new UpdateFailException();
        }

        List<VoteOptionsEntity> voteOptionsEntities = voteOptionsRepository.findByActivityId(activityId);

        if (voteOptionsEntities.isEmpty()) {
            log.error("[VOTE-004] 查無 activityId 為 {} 的資料", activityId);
            throw new DataNotFoundException();
        }

        List<VOTE004TranrqOption> vote004TranrqOptions = vote004Tranrq.getOptions();

        if (voteOptionsEntities.size() != vote004TranrqOptions.size()) {
            log.warn("[VOTE-004] 欲更新的投票活動選項數量需與資料庫一致");
            return null;
        }

        int i = 0;

        for (VOTE004TranrqOption vote004TranrqOption : vote004TranrqOptions) {
            VoteOptionsEntity voteOptionsEntity = voteOptionsEntities.get(i);
            voteOptionsEntity.setOptionSymbol(vote004TranrqOption.getOptionSymbol());
            voteOptionsEntity.setDescription(vote004TranrqOption.getDescription());
            i++;
        }

        try {
            voteOptionsRepository.saveAll(voteOptionsEntities);
        } catch (Exception e) {
            log.error("[VOTE-004] 更新表格 vote_options 異常");
            throw new UpdateFailException();
        }

        log.info("[VOTE-004] 更新投票 API 執行完成");

        return new Res<VOTE004Tranrs>(new ResMwHeader(ReturnCodeAndDescEnum.SUCCESS), new VOTE004Tranrs(activityId));
    }
}

package com.example.demo.service.impl;

import com.example.demo.dto.Res;
import com.example.demo.dto.ResMwHeader;
import com.example.demo.dto.VOTE002Tranrs;
import com.example.demo.entity.VoteActivitiesEntity;
import com.example.demo.entity.VoteOptionsEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.DeleteFailException;
import com.example.demo.model.ReturnCodeAndDescEnum;
import com.example.demo.repository.VoteActivitiesRepository;
import com.example.demo.repository.VoteOptionsRepository;
import com.example.demo.service.VOTE002Svc;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VOTE002SvcImpl implements VOTE002Svc {

    private final VoteActivitiesRepository voteActivitiesRepository;

    private final VoteOptionsRepository voteOptionsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Res<VOTE002Tranrs> vote002(Long activityId) throws DataNotFoundException, DeleteFailException {

        log.info("[VOTE-002] 刪除投票 API 啟動");

        VoteActivitiesEntity voteActivitiesEntity = voteActivitiesRepository.findById(activityId).orElseThrow(() -> {
            log.error("[VOTE-002] 查無 activityId 為 {} 的資料", activityId);
            return new DataNotFoundException();
        });

        if (LocalDateTime.now().isAfter(voteActivitiesEntity.getEndTime())) {
            log.warn("[VOTE-002] 僅提供尚未結束的投票活動刪除");
            return null;
        }

        try {
            voteActivitiesRepository.delete(voteActivitiesEntity);
        } catch (Exception e) {
            log.error("[VOTE-002] 刪除表格 vote_activities 的資料有誤");
            throw new DeleteFailException();
        }

        List<VoteOptionsEntity> voteOptionsEntities = voteOptionsRepository.findByActivityId(activityId);

        if (voteOptionsEntities.isEmpty()) {
            log.error("[VOTE-002] 查無 activityId 為 {} 的資料", activityId);
            throw new DataNotFoundException();
        }

        try {
            voteOptionsRepository.deleteAll(voteOptionsEntities);
        } catch (Exception e) {
            log.error("[VOTE-002] 刪除表格 vote_options 的資料有誤");
            throw new DeleteFailException();
        }

        log.info("[VOTE-002] 刪除投票 API 執行完成");

        return new Res<VOTE002Tranrs>(new ResMwHeader(ReturnCodeAndDescEnum.SUCCESS), new VOTE002Tranrs(activityId));
    }
}

package com.example.demo.service.impl;

import com.example.demo.dto.Res;
import com.example.demo.dto.ResMwHeader;
import com.example.demo.dto.VOTE007Tranrs;
import com.example.demo.dto.VOTE007TranrsActivity;
import com.example.demo.entity.VoteRecordsEntity;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.model.ReturnCodeAndDescEnum;
import com.example.demo.repository.VoteRecordsRepository;
import com.example.demo.service.VOTE007Svc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VOTE007SvcImpl implements VOTE007Svc {

    private final VoteRecordsRepository voteRecordsRepository;

    @Override
    public Res<VOTE007Tranrs> vote007(Long userId) throws DataNotFoundException {

        log.info("[VOTE-007] 刪除投票計數 API 啟動");

        List<VoteRecordsEntity> voteRecordsEntities = voteRecordsRepository.findByUserId(userId);

        if (voteRecordsEntities.isEmpty()) {
            log.error("[VOTE-007] 表格 vote_records 查無 userId 為 {} 的資料", userId);
            throw new DataNotFoundException();
        }

        List<VOTE007TranrsActivity> votedActivities = new ArrayList<>();

        for (VoteRecordsEntity voteRecordsEntity : voteRecordsEntities) {
            votedActivities.add(new VOTE007TranrsActivity(voteRecordsEntity.getActivityId(), voteRecordsEntity.getOptionSymbol()));
        }

        log.info("[VOTE-007] 刪除投票計數 API 執行完成");

        return new Res<VOTE007Tranrs>(new ResMwHeader(ReturnCodeAndDescEnum.SUCCESS), new VOTE007Tranrs(userId, votedActivities));
    }
}

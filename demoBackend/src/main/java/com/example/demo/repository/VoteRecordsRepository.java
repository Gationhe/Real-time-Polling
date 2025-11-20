package com.example.demo.repository;

import com.example.demo.entity.VoteRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRecordsRepository extends JpaRepository<VoteRecordsEntity, Long> {

    Boolean existsByUserIdAndActivityId(Long userId, Long activityId);

    Optional<VoteRecordsEntity> findByUserIdAndActivityId(Long userId, Long activityId);

    List<VoteRecordsEntity> findByUserId(Long userId);

}

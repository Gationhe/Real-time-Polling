package com.example.demo.repository;

import com.example.demo.entity.VoteOptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteOptionsRepository extends JpaRepository<VoteOptionsEntity, Long> {

    List<VoteOptionsEntity> findByActivityId(Long activityId);

    Optional<VoteOptionsEntity> findByActivityIdAndOptionSymbol(Long activityId, String optionSymbol);

}

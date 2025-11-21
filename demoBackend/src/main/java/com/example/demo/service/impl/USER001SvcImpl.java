package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.UsersEntity;
import com.example.demo.exception.InsertFailException;
import com.example.demo.model.ReturnCodeAndDescEnum;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.USER001Svc;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class USER001SvcImpl implements USER001Svc {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Res<USER001Tranrs> user001(Req<USER001Tranrq> requestBody) throws InsertFailException {

        log.info("[USER-001] 註冊 API 啟動");

        USER001Tranrq user001Tranrq = requestBody.getTranrq();

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setName(user001Tranrq.getName());
        usersEntity.setEmail(user001Tranrq.getEmail());
        usersEntity.setPassword(passwordEncoder.encode(user001Tranrq.getPassword()));
        usersEntity.setRole(user001Tranrq.getRole());

        Long userId;

        try {
            userId = usersRepository.save(usersEntity).getId();
        } catch (Exception e) {
            log.error("[USER-001] 註冊資訊儲存異常");
            throw new InsertFailException();
        }

        log.info("[USER-001] 註冊 API 執行完成");

        return new Res<USER001Tranrs>(new ResMwHeader(ReturnCodeAndDescEnum.SUCCESS), new USER001Tranrs(userId));
    }
}

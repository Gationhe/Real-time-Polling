package com.example.demo.service;

import com.example.demo.dto.Req;
import com.example.demo.dto.Res;
import com.example.demo.dto.USER001Tranrq;
import com.example.demo.dto.USER001Tranrs;
import com.example.demo.exception.InsertFailException;

public interface USER001Svc {
    Res<USER001Tranrs> user001(Req<USER001Tranrq> requestBody) throws InsertFailException;
}

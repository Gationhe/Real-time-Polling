package com.example.demo.service;

import com.example.demo.dto.Req;
import com.example.demo.dto.Res;
import com.example.demo.dto.VOTE001Tranrq;
import com.example.demo.dto.VOTE001Tranrs;
import com.example.demo.exception.InsertFailException;

public interface VOTE001Svc {
    Res<VOTE001Tranrs> vote001(Req<VOTE001Tranrq> requestBody) throws InsertFailException;
}

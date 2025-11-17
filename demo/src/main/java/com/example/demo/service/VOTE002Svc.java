package com.example.demo.service;

import com.example.demo.dto.Req;
import com.example.demo.dto.Res;
import com.example.demo.dto.VOTE002Tranrq;
import com.example.demo.dto.VOTE002Tranrs;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.DeleteFailException;

public interface VOTE002Svc {
    Res<VOTE002Tranrs> vote002(Req<VOTE002Tranrq> requestBody) throws DataNotFoundException, DeleteFailException;
}

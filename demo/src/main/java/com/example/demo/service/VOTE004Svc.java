package com.example.demo.service;

import com.example.demo.dto.Req;
import com.example.demo.dto.Res;
import com.example.demo.dto.VOTE004Tranrq;
import com.example.demo.dto.VOTE004Tranrs;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.UpdateFailException;

public interface VOTE004Svc {
    Res<VOTE004Tranrs> vote004(Long activityId, Req<VOTE004Tranrq> requestBody) throws DataNotFoundException, UpdateFailException;
}

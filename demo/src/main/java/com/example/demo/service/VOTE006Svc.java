package com.example.demo.service;

import com.example.demo.dto.Req;
import com.example.demo.dto.Res;
import com.example.demo.dto.VOTE006Tranrq;
import com.example.demo.dto.VOTE006Tranrs;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.DeleteFailException;
import com.example.demo.exception.UpdateFailException;

public interface VOTE006Svc {
    Res<VOTE006Tranrs> vote006(Long activityId, Req<VOTE006Tranrq> requestBody) throws DataNotFoundException, DeleteFailException, UpdateFailException;
}
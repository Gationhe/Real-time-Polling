package com.example.demo.service;

import com.example.demo.dto.Req;
import com.example.demo.dto.Res;
import com.example.demo.dto.VOTE008Tranrq;
import com.example.demo.dto.VOTE008Tranrs;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.UpdateFailException;

public interface VOTE008Svc {
    Res<VOTE008Tranrs> vote008(Long activityId, Req<VOTE008Tranrq> requestBody) throws DataNotFoundException, UpdateFailException;
}

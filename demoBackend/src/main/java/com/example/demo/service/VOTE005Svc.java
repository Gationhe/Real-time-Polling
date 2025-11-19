package com.example.demo.service;

import com.example.demo.dto.Req;
import com.example.demo.dto.Res;
import com.example.demo.dto.VOTE005Tranrq;
import com.example.demo.dto.VOTE005Tranrs;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.InsertFailException;
import com.example.demo.exception.UpdateFailException;

public interface VOTE005Svc {
    Res<VOTE005Tranrs> vote005(Long activityId, Req<VOTE005Tranrq> requestBody) throws DataNotFoundException, InsertFailException, UpdateFailException;
}

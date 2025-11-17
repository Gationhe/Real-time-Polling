package com.example.demo.service;

import com.example.demo.dto.Res;
import com.example.demo.dto.VOTE003Tranrs;
import com.example.demo.exception.DataNotFoundException;

public interface VOTE003Svc {
    Res<VOTE003Tranrs> vote003(Long activityId) throws DataNotFoundException;
}

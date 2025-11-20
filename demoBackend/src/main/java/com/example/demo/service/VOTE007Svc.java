package com.example.demo.service;

import com.example.demo.dto.Res;
import com.example.demo.dto.VOTE007Tranrs;
import com.example.demo.exception.DataNotFoundException;

public interface VOTE007Svc {
    Res<VOTE007Tranrs> vote007(Long userId) throws DataNotFoundException;
}

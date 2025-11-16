package com.example.demo.controller;

import com.example.demo.controller.advice.BaseController;
import com.example.demo.dto.Req;
import com.example.demo.dto.Res;
import com.example.demo.dto.VOTE001Tranrq;
import com.example.demo.dto.VOTE001Tranrs;
import com.example.demo.exception.InsertFailException;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.service.VOTE001Svc;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/vote")
public class VoteController extends BaseController {

    private final VOTE001Svc vote001Svc;

    @PostMapping(value = "/create")
    public Res<VOTE001Tranrs> vote001(Req<VOTE001Tranrq> requestBody, Errors errors) throws InsertFailException, InvalidInputException {
        handleValidForDto(errors);
        return vote001Svc.vote001(requestBody);
    }
}

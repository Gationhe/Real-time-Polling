package com.example.demo.controller;

import com.example.demo.controller.advice.BaseController;
import com.example.demo.dto.*;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.DeleteFailException;
import com.example.demo.exception.InsertFailException;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.service.VOTE001Svc;
import com.example.demo.service.VOTE002Svc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/vote")
public class VoteController extends BaseController {

    private final VOTE001Svc vote001Svc;

    private final VOTE002Svc vote002Svc;

    @PostMapping(value = "/create")
    public Res<VOTE001Tranrs> vote001(@Valid @RequestBody Req<VOTE001Tranrq> requestBody, Errors errors) throws InsertFailException, InvalidInputException {
        handleValidForDto(errors);
        return vote001Svc.vote001(requestBody);
    }

    @DeleteMapping(value = "/delete")
    public Res<VOTE002Tranrs> vote002(@Valid @RequestBody Req<VOTE002Tranrq> requestBody, Errors errors) throws DataNotFoundException, DeleteFailException, InvalidInputException {
        handleValidForDto(errors);
        return vote002Svc.vote002(requestBody);
    }
}

package com.example.demo.controller;

import com.example.demo.controller.advice.BaseController;
import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.service.*;
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

    private final VOTE003Svc vote003Svc;

    private final VOTE004Svc vote004Svc;

    private final VOTE005Svc vote005Svc;

    private final VOTE006Svc vote006Svc;

//    private final VOTE007Svc vote007Svc;

    @PostMapping(value = "/create")
    public Res<VOTE001Tranrs> vote001(@Valid @RequestBody Req<VOTE001Tranrq> requestBody, Errors errors) throws InsertFailException, InvalidInputException {
        handleValidForDto(errors);
        return vote001Svc.vote001(requestBody);
    }

    @DeleteMapping(value = "/delete/{activityId}")
    public Res<VOTE002Tranrs> vote002(@PathVariable Long activityId) throws DataNotFoundException, DeleteFailException {
        return vote002Svc.vote002(activityId);
    }

    @GetMapping(value = "/query/{activityId}")
    public Res<VOTE003Tranrs> vote003(@PathVariable Long activityId) throws DataNotFoundException {
        return vote003Svc.vote003(activityId);
    }

    @PatchMapping(value = "/update/{activityId}")
    public Res<VOTE004Tranrs> vote004(@PathVariable Long activityId, @RequestBody Req<VOTE004Tranrq> requestBody) throws DataNotFoundException, UpdateFailException {
        return vote004Svc.vote004(activityId, requestBody);
    }

    @PostMapping(value = "/{activityId}/create")
    public Res<VOTE005Tranrs> vote005(@PathVariable Long activityId, @Valid @RequestBody Req<VOTE005Tranrq> requestBody, Errors errors) throws DataNotFoundException, InsertFailException, UpdateFailException, InvalidInputException {
        handleValidForDto(errors);
        return vote005Svc.vote005(activityId, requestBody);
    }

    @DeleteMapping(value = "/{activityId}/delete")
    public Res<VOTE006Tranrs> vote006(@PathVariable Long activityId, @Valid @RequestBody Req<VOTE006Tranrq> requestBody, Errors errors) throws DataNotFoundException, DeleteFailException, UpdateFailException, InvalidInputException {
        handleValidForDto(errors);
        return vote006Svc.vote006(activityId, requestBody);
    }

//    @GetMapping(value = "/{activityId}/query")
//    public Res<VOTE007Tranrs> vote007(@PathVariable Long userId) throws DataNotFoundException {
//        return vote007Svc.vote007(activityId, userId);
//    }
}

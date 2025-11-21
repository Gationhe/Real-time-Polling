package com.example.demo.controller;

import com.example.demo.controller.advice.BaseController;
import com.example.demo.dto.Req;
import com.example.demo.dto.Res;
import com.example.demo.dto.USER001Tranrq;
import com.example.demo.dto.USER001Tranrs;
import com.example.demo.exception.InsertFailException;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.service.USER001Svc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/user")
public class UserController extends BaseController {

    private final USER001Svc user001Svc;

    @PostMapping(value = "/register")
    public Res<USER001Tranrs> user001(@Valid @RequestBody Req<USER001Tranrq> requestBody, Errors errors) throws InsertFailException, InvalidInputException {
        handleValidForDto(errors);
        return user001Svc.user001(requestBody);
    }

}

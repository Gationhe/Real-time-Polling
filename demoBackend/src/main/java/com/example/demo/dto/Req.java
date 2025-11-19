package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Req<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("MWHEADER")
    @Valid
    private ReqMwHeader mwHeader;

    @JsonProperty("TRANRQ")
    @Valid
    private T tranrq;
}
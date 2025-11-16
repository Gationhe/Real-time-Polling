package com.example.demo.dto;

import com.example.demo.model.ReturnCodeAndDescEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResMwHeader implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("RETURNCODE")
    private String returnCode;

    @JsonProperty("RETURNDESC")
    private String returnDesc;

    public ResMwHeader(ReturnCodeAndDescEnum returnCodeAndDescEnum) {
        this.returnCode = returnCodeAndDescEnum.getCode();
        this.returnDesc = returnCodeAndDescEnum.getDesc();
    }
}
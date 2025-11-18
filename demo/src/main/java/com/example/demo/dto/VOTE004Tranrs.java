package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VOTE004Tranrs implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("activityId")
    private Long activityId;

}

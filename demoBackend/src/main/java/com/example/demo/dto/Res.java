package com.example.demo.dto;

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
public class Res<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("MWHEADER")
    private ResMwHeader mwHeader;

    @JsonProperty("TRANRS")
    private T tranrs;
}

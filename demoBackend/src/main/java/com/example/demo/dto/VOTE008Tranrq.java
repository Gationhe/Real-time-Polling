package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VOTE008Tranrq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("userId")
    @NotNull(message = "userId is non-null")
    private Long userId;

    @JsonProperty("optionSymbol")
    @NotBlank(message = "optionSymbol is non-empty")
    private String optionSymbol;

}

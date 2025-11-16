package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VOTE001TranrqOption implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("optionSymbol")
    @NotBlank(message = "optionSymbol is non-empty")
    private String optionSymbol;

    @JsonProperty("description")
    @NotBlank(message = "description is non-empty")
    private String description;

}

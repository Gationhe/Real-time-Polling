package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VOTE001Tranrq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("userId")
    @NotNull(message = "userId is non-null")
    private Long userId;

    @JsonProperty("title")
    @NotBlank(message = "title is non-empty")
    private String title;

    @JsonProperty("description")
    @NotBlank(message = "description is non-empty")
    private String description;

    @JsonProperty("startTime")
    @NotNull(message = "startTime is non-null")
    private LocalDateTime startTime;

    @JsonProperty("endTime")
    @NotNull(message = "endTime is non-null")
    private LocalDateTime endTime;

    @Valid
    @JsonProperty("options")
    private List<VOTE001TranrqOption> options;

}

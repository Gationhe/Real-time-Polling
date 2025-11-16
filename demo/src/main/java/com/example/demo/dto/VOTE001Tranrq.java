package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "userId is non-empty")
    private Long userId;

    @JsonProperty("title")
    @NotBlank(message = "title is non-empty")
    private String title;

    @JsonProperty("description")
    @NotBlank(message = "description is non-empty")
    private String description;

    @JsonProperty("startTime")
    @NotBlank(message = "startTime is non-empty")
    private LocalDateTime startTime;

    @JsonProperty("endTime")
    @NotBlank(message = "endTime is non-empty")
    private LocalDateTime endTime;

    @Valid
    @JsonProperty("options")
    private List<VOTE001TranrqOption> options;

}

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
public class ReqMwHeader implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "MSGID is non-empty")
    @JsonProperty("MSGID")
    private String msgID;
}

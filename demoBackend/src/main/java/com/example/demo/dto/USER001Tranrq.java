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
public class USER001Tranrq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    @NotBlank(message = "name is non-empty")
    private String name;

    @JsonProperty("email")
    @NotBlank(message = "email is non-empty")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "password is non-empty")
    private String password;

    @JsonProperty("role")
    @NotBlank(message = "role is non-empty")
    private String role;
}

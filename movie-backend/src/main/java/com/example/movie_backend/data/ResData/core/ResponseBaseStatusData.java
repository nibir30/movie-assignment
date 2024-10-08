package com.example.movie_backend.data.ResData.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ResponseBaseStatusData {
    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;
}

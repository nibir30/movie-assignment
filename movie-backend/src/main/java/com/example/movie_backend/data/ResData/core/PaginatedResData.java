package com.example.movie_backend.data.ResData.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResData<T> extends ResponseBaseStatusData implements Serializable {
    @JsonProperty("data")
    private PaginationBaseData<T> data;

    @JsonProperty("request")
    private RequestBaseData request;

}

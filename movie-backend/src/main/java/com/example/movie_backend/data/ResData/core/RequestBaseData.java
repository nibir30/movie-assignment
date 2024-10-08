package com.example.movie_backend.data.ResData.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestBaseData implements Serializable {
    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("sort_by")
    private String sortBy;

    @JsonProperty("sort_type")
    private String sortType;

    @JsonProperty("search")
    private String search;

}

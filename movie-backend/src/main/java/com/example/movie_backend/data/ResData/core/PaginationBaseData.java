package com.example.movie_backend.data.ResData.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationBaseData<T> implements Serializable {
    @JsonProperty("path")
    private String path;

    @JsonProperty("total")
    private Long total;

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("from")
    private Integer from;

    @JsonProperty("to")
    private Integer to;

    @JsonProperty("last_page")
    private Integer lastPage;

    @JsonProperty("data")
    private T data;

}

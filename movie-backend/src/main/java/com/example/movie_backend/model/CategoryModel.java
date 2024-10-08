package com.example.movie_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "category")
public class CategoryModel {
    @Id
    private Long categoryId;
    private String name;

    @Column(columnDefinition = "BLOB")
    private String description;

    @JsonIgnore
    private LocalDateTime insertTime;

    @JsonIgnore
    private LocalDateTime updateTime;
}

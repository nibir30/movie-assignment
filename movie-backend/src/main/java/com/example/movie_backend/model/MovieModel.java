package com.example.movie_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "movie")
public class MovieModel {
    @Id
    private Long movieId;
    private String title;

    @ManyToMany(targetEntity = CategoryModel.class, cascade = {CascadeType.MERGE})
    private Set<CategoryModel> categories = new HashSet<>();

    @ManyToMany(targetEntity = CastModel.class, cascade = {CascadeType.MERGE})
    private Set<CastModel> casts = new HashSet<>();

    private LocalDate releaseDate;
    private String budget;
    private String imagePath;

    @Column(columnDefinition = "BLOB")
    private String description;

    @JsonIgnore
    private LocalDateTime insertTime;

    @JsonIgnore
    private LocalDateTime updateTime;
}

package com.example.movie_backend.data.ReqData;

import com.example.movie_backend.model.CastModel;
import com.example.movie_backend.model.CategoryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveMovieReqData {
    private Long movieId;
    private String title;

    private Set<Long> categoryIds = new HashSet<>();

    private Set<Long> castIds = new HashSet<>();

    private LocalDate releaseDate;
    private String budget;
    private String imagePath;

    private String description;
}

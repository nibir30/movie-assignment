package com.example.movie_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cast")
public class CastModel {
    @Id
    private Long castId;

    private String fullName;
    private String imagePath;
    private Integer age;

    @JsonIgnore
    private LocalDateTime insertTime;
    @JsonIgnore
    private LocalDateTime updateTime;
}

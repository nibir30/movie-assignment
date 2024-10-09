package com.example.movie_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "cast")
public class CastModel {
    @Id
    private Long castId;

    private String fullName;
    private String imagePath;
    
    @Column(name = "dob", columnDefinition = "DATE")
    private LocalDate dob;

    @JsonIgnore
    private LocalDateTime insertTime;
    @JsonIgnore
    private LocalDateTime updateTime;
}

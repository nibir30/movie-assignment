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
@Entity(name = "user")
public class UserModel {
    @Id
    private Long userId;

    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String fullName;
    private String gender;
    private LocalDate dob;
    @OneToMany(targetEntity = CastModel.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    Set<MovieModel> favoriteMovies = new HashSet<>();

    @JsonIgnore
    private LocalDateTime insertTime;
    @JsonIgnore
    private LocalDateTime updateTime;
}

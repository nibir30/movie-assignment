package com.example.movie_backend.repository;

import com.example.movie_backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);

    Boolean existsByEmail(String email);
}

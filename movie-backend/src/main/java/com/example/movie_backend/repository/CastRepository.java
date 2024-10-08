package com.example.movie_backend.repository;

import com.example.movie_backend.model.CastModel;
import com.example.movie_backend.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CastRepository extends JpaRepository<CastModel, Long> {
    List<CastModel> findByCastIdIn(Collection<Long> castIds);

    List<CastModel> findByFullNameContainingIgnoreCase(String search);
}

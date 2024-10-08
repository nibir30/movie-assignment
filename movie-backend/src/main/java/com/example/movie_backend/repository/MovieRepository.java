package com.example.movie_backend.repository;

import com.example.movie_backend.model.MovieModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel, Long> {
    List<MovieModel> findByTitleContainingIgnoreCase(String title);

    @Query("""
            SELECT m FROM com.example.movie_backend.model.MovieModel m 
            JOIN m.categories c WHERE c.categoryId IN :categoryIds
            """)
    List<MovieModel> findByCategoryIdsIn(@Param("categoryIds") List<Long> categoryIds);

    @Query("""
            SELECT m FROM com.example.movie_backend.model.MovieModel m 
            JOIN m.casts c WHERE c.castId IN :castIds
            """)
    List<MovieModel> findByCastIdsIn(@Param("castIds") List<Long> castIds);

    @Query(value = """
                SELECT v FROM movie v
            """)
    Page<MovieModel> findAllPagedMovies(Pageable pageable);
}

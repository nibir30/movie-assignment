package com.example.movie_backend.service;

import com.example.movie_backend.data.ReqData.AddFavoriteMovieReqData;
import com.example.movie_backend.data.ReqData.SaveMovieReqData;
import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.MovieModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    ResponseBaseData<MovieModel> saveMovie(SaveMovieReqData saveMovieReqData);

    ResponseBaseData<?> addFavoriteMovie(AddFavoriteMovieReqData addFavoriteMovieReqData);

    ResponseBaseData<List<MovieModel>> getAllMovies();

    ResponseBaseData<List<MovieModel>> searchMovies(String searchQuery);
}

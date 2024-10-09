package com.example.movie_backend.controller;

import com.example.movie_backend.controller.common.UserApiUrlConstants;
import com.example.movie_backend.data.ReqData.AddFavoriteMovieReqData;
import com.example.movie_backend.data.ReqData.SaveMovieReqData;
import com.example.movie_backend.data.ResData.core.PaginatedResData;
import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.CastModel;
import com.example.movie_backend.model.MovieModel;
import com.example.movie_backend.service.CastService;
import com.example.movie_backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping(path = UserApiUrlConstants.ALL_MOVIES)
    @ResponseBody
    public ResponseBaseData<List<MovieModel>> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping(path = UserApiUrlConstants.SAVE_MOVIE)
    @ResponseBody
    public ResponseBaseData<MovieModel> saveMovie(@RequestBody SaveMovieReqData saveMovieReqData) {
        return movieService.saveMovie(saveMovieReqData);
    }

    @PostMapping(path = UserApiUrlConstants.SAVE_FAVORITE_MOVIE)
    @ResponseBody
    public ResponseBaseData<?> addFavoriteMovie(@RequestBody AddFavoriteMovieReqData addFavoriteMovieReqData) {
        return movieService.addFavoriteMovie(addFavoriteMovieReqData);
    }

    @GetMapping(path = UserApiUrlConstants.FAVORITE_MOVIES)
    @ResponseBody
    public ResponseBaseData<List<MovieModel>> searchMovies(
            @RequestParam String email, @RequestParam(required = false) String search) {
        return movieService.getFavoriteMovies(email, search);
    }

    @GetMapping(path = UserApiUrlConstants.SEARCH_MOVIES)
    @ResponseBody
    public ResponseBaseData<List<MovieModel>> searchMovies(@RequestParam String query) {
        return movieService.searchMovies(query);
    }

    @GetMapping(value = UserApiUrlConstants.PAGINATED_MOVIES)
    public PaginatedResData<?> getPaginatedMovies(
            @RequestParam(value = "per_page", required = false, defaultValue = "10") int size,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @Nullable @RequestParam(value = "sort_by", required = false) String sortBy,
            @Nullable @RequestParam(value = "sort_type", required = false) String sortType,
            @Nullable @RequestParam(value = "search", required = false) String search
    ) {
        return movieService.getPaginatedMovies(page, size, sortBy, sortType, search);
    }
}

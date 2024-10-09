package com.example.movie_backend.serviceImpl;

import com.example.movie_backend.data.ReqData.AddFavoriteMovieReqData;
import com.example.movie_backend.data.ReqData.SaveMovieReqData;
import com.example.movie_backend.data.ResData.core.PaginatedResData;
import com.example.movie_backend.data.ResData.core.RequestBaseData;
import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.CastModel;
import com.example.movie_backend.model.CategoryModel;
import com.example.movie_backend.model.MovieModel;
import com.example.movie_backend.model.UserModel;
import com.example.movie_backend.repository.CastRepository;
import com.example.movie_backend.repository.CategoryRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.service.MovieService;
import com.example.movie_backend.util.IdGenerator;
import com.example.movie_backend.util.PageUtils;
import com.example.movie_backend.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final CastRepository castRepository;
    private final CategoryRepository categoryRepository;

    public static Page<MovieModel> toPage(List<MovieModel> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        List<MovieModel> paginatedList = list.subList(start, end);
        return new PageImpl<>(paginatedList, pageable, list.size());
    }

    @Override
    public PaginatedResData<?> getPaginatedMovies(int page, int size, String sortBy,
                                                  String sortType, String search) {
        try {
            if (sortBy == null || sortBy.isEmpty()) {
                sortBy = "title";
                sortType = "asc";
            }
            if (sortType == null || sortType.isEmpty()) {
                sortType = "asc";
            }
            RequestBaseData request = RequestBaseData.builder()
                    .perPage(size)
                    .page(page)
                    .sortBy(sortBy)
                    .sortType(sortType)
                    .search(search)
                    .build();

            Pageable pageRequest = PageUtils.generatePageRequest((page - 1), size, sortBy, sortType);
            Page<MovieModel> pageResult;

            if (search == null || search.isEmpty()) {
                List<MovieModel> movieModelList = movieRepository.findAll();
                pageResult = toPage(movieModelList, pageRequest);
            } else {
                ResponseBaseData<List<MovieModel>> responseBaseData = searchMovies(search);
                if (responseBaseData.getSuccess()) {
                    List<MovieModel> movieModelList = responseBaseData.getData();
                    pageResult = toPage(movieModelList, pageRequest);
                } else {
                    return (PaginatedResData<?>) PaginatedResData.builder()
                            .success(false)          // Set status to false
                            .code(200)              // Example code for an error scenario
                            .message("AN ERROR OCCURRED")  // Example message
                            .build();
                }
            }


            return PageUtils.getPaginatedResData(page, size, request, pageRequest, pageResult);
        } catch (Exception e) {
            log.error("Error in getPaginatedMovies {}", e.getMessage());

            return (PaginatedResData<?>) PaginatedResData.builder()
                    .success(false)
                    .code(200)
                    .message("AN ERROR OCCURRED")
                    .build();
        }
    }

    @Override
    public ResponseBaseData<List<MovieModel>> searchMovies(String searchQuery) {
        try {
            List<MovieModel> movieModels = movieRepository.findByTitleContainingIgnoreCase(searchQuery);
            List<CastModel> castModels = castRepository.findByFullNameContainingIgnoreCase(searchQuery);
            List<CategoryModel> categoryModels = categoryRepository.findByNameContainingIgnoreCase(searchQuery);

            List<Long> categoryIds = categoryModels.stream().map(CategoryModel::getCategoryId).toList();
            List<Long> castIds = castModels.stream().map(CastModel::getCastId).toList();

            List<MovieModel> movieModels1 = movieRepository.findByCategoryIdsIn(categoryIds);
            List<MovieModel> movieModels2 = movieRepository.findByCastIdsIn(castIds);

            Set<MovieModel> combinedSet = new HashSet<>();
            combinedSet.addAll(movieModels);
            combinedSet.addAll(movieModels1);
            combinedSet.addAll(movieModels2);
            List<MovieModel> searchResult = new ArrayList<>(combinedSet.stream().toList());

            searchResult.sort(Comparator.comparing(MovieModel::getTitle));

            return ResponseUtils.dataSuccess("HERE ARE THE SEARCH RESULTS", searchResult);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("COULD NOT GET RESULT", e.getMessage());
        }

    }

    @Override
    public ResponseBaseData<MovieModel> saveMovie(SaveMovieReqData saveMovieReqData) {
        try {
            MovieModel movieModel = new MovieModel();

            if (saveMovieReqData.getMovieId() == null) {
                BeanUtils.copyProperties(saveMovieReqData, movieModel);
                movieModel.setMovieId(IdGenerator.generateId());
                movieModel.setInsertTime(LocalDateTime.now());
            } else {
                Optional<MovieModel> movieModelOptional = movieRepository.findById(saveMovieReqData.getMovieId());
                if (movieModelOptional.isPresent()) {
                    BeanUtils.copyProperties(saveMovieReqData, movieModel);
                } else {
                    return ResponseUtils.validationError("MOVIE DOES NOT EXIST");
                }
            }

            if (!saveMovieReqData.getCategoryIds().isEmpty()) {
                List<CategoryModel> categoryModels = categoryRepository.findAllById(saveMovieReqData.getCategoryIds());
                Set<CategoryModel> categoryModelSet = new HashSet<>(categoryModels);
                movieModel.setCategories(categoryModelSet);
            } else {
                movieModel.setCategories(new HashSet<>());
            }

            if (!saveMovieReqData.getCastIds().isEmpty()) {
                List<CastModel> castModels = castRepository.findAllById(saveMovieReqData.getCastIds());
                Set<CastModel> castModelSet = new HashSet<>(castModels);
                movieModel.setCasts(castModelSet);
            } else {
                movieModel.setCasts(new HashSet<>());
            }

            movieModel.setUpdateTime(LocalDateTime.now());

            MovieModel saveMovie = movieRepository.save(movieModel);
            return ResponseUtils.dataSuccess("MOVIE SAVED", saveMovie);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("COULD NOT SAVE MOVIE", e.getMessage());
        }
    }

    @Override
    public ResponseBaseData<?> addFavoriteMovie(AddFavoriteMovieReqData addFavoriteMovieReqData) {
        try {
            if (!userRepository.existsByEmail(addFavoriteMovieReqData.getEmail())) {
                return ResponseUtils.validationError("PLEASE LOG IN FIRST");
            }

            if (!movieRepository.existsById(addFavoriteMovieReqData.getMovieId())) {
                return ResponseUtils.validationError("PLEASE SELECT A VALID MOVIE");
            }
            UserModel user = userRepository.findByEmail(addFavoriteMovieReqData.getEmail());
            Optional<MovieModel> movieModel = movieRepository.findById(addFavoriteMovieReqData.getMovieId());

            if (addFavoriteMovieReqData.getAddFavorite()) {
                Set<MovieModel> movieModelSet = user.getFavoriteMovies();
                movieModelSet.add(movieModel.get());
                user.setFavoriteMovies(movieModelSet);
            } else {
                Set<MovieModel> movieModelSet = user.getFavoriteMovies();
                movieModelSet.remove(movieModel.get());
                user.setFavoriteMovies(movieModelSet);
            }

            userRepository.save(user);

            return ResponseUtils.success(addFavoriteMovieReqData.getAddFavorite() ?
                    "ADDED TO FAVORITE MOVIES" : "REMOVED FROM FAVORITE MOVIES");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("COULD NOT ADD TO FAVORITE MOVIES", e.getMessage());
        }
    }

    @Override
    public ResponseBaseData<List<MovieModel>> getAllMovies() {
        return ResponseUtils.dataSuccess("HERE ARE THE MOVIES", movieRepository.findAll());
    }
}

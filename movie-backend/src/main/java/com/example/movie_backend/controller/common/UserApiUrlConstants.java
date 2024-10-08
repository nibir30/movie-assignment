package com.example.movie_backend.controller.common;

public class UserApiUrlConstants {
    public final static String API_URI_PREFIX = "/api/v1";

    // user
    public final static String USER = API_URI_PREFIX + "/user";
    public final static String REGISTER = USER + "/register";
    public final static String LOGIN = USER + "/login";

    // movie
    public final static String MOVIE = API_URI_PREFIX + "/movie";
    public final static String SAVE_MOVIE = MOVIE + "/save";
    public final static String ALL_MOVIES = MOVIE + "/all";
    public final static String SEARCH_MOVIES = MOVIE + "/search";
    public final static String SAVE_FAVORITE_MOVIE = MOVIE + "/favorite/save";


    // cast
    public final static String CAST = API_URI_PREFIX + "/cast";
    public final static String SAVE_CAST = CAST + "/save";
    public final static String ALL_CASTS = CAST + "/all";

    // category
    public final static String CATEGORY = API_URI_PREFIX + "/category";
    public final static String SAVE_CATEGORY = CATEGORY + "/save";
    public final static String ALL_CATEGORIES = CATEGORY + "/all";
}

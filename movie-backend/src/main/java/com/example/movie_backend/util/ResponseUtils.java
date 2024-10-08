package com.example.movie_backend.util;

import com.example.movie_backend.data.ResData.core.ResponseBaseData;

import java.time.LocalDateTime;

public class ResponseUtils {
    public static <T> ResponseBaseData<T> validationError(String errorMessage) {
        return ResponseBaseData.<T>builder()
                .code(400)
                .success(false)
                .message(errorMessage)
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }

    public static <T> ResponseBaseData<T> success(String message) {
        return ResponseBaseData.<T>builder()
                .code(200)
                .success(true)
                .message(message)
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }

    public static <T> ResponseBaseData<T> exceptionError(String errorMessage, String exceptionDetails) {
        return ResponseBaseData.<T>builder()
                .code(400)
                .success(false)
                .message(errorMessage)
                .details(exceptionDetails)
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }

    public static <T> ResponseBaseData<T> dataSuccess(String message, T data) {
        return ResponseBaseData.<T>builder()
                .code(200)
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

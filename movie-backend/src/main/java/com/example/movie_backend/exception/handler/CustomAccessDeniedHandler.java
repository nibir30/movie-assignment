package com.example.movie_backend.exception.handler;


import com.example.movie_backend.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.movie_backend.exception.ErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.AccessDeniedException;

public class CustomAccessDeniedHandler {

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        if (!response.isCommitted()) {
            ErrorDetails customErrorData = new ErrorDetails("Access Denied",
                    99);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            OutputStream responseStream = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(responseStream, customErrorData);
            responseStream.flush();
        } else {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Access Denied");
        }
    }
}
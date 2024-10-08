package com.example.movie_backend.service;

import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseBaseData<UserModel> register(UserModel userModel);

    ResponseBaseData<UserModel> login(String email);
}

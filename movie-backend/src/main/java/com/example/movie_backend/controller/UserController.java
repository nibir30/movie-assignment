package com.example.movie_backend.controller;

import com.example.movie_backend.controller.common.UserApiUrlConstants;
import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.CastModel;
import com.example.movie_backend.model.UserModel;
import com.example.movie_backend.service.CastService;
import com.example.movie_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = UserApiUrlConstants.REGISTER)
    @ResponseBody
    public ResponseBaseData<UserModel> register(@RequestBody UserModel userModel) {
        return userService.register(userModel);
    }

    @PostMapping(path = UserApiUrlConstants.LOGIN)
    @ResponseBody
    public ResponseBaseData<UserModel> login(@RequestParam String email) {
        return userService.login(email);
    }
}

package com.example.movie_backend.serviceImpl;

import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.UserModel;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.service.UserService;
import com.example.movie_backend.util.IdGenerator;
import com.example.movie_backend.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseBaseData<UserModel> register(UserModel userModel) {
        try {
            if (userRepository.existsByEmail(userModel.getEmail())) {
                return ResponseUtils.validationError("EMAIL ALREADY EXISTS");
            }

            if (userModel.getUserId() == null) {
                userModel.setUserId(IdGenerator.generateId());
                userModel.setInsertTime(LocalDateTime.now());
            }
            UserModel user = userRepository.save(userModel);
            log.info("NEW USER REGISTERED: {}", user.getEmail());

            return ResponseUtils.dataSuccess("SUCCESSFULLY REGISTERED", user);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("COULD NOT REGISTER USER", e.getMessage());
        }

    }

    @Override
    public ResponseBaseData<UserModel> login(String email) {
        try {

            if (!userRepository.existsByEmail(email)) {
                return ResponseUtils.validationError("USER NOT FOUND");
            }
            UserModel user = userRepository.findByEmail(email);
            return ResponseUtils.dataSuccess("SUCCESSFULLY LOGIN", user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("COULD NOT LOGIN", e.getMessage());
        }
    }
}

package com.example.movie_backend.controller;

import com.example.movie_backend.controller.common.UserApiUrlConstants;
import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.CastModel;
import com.example.movie_backend.service.CastService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CastController {
    private final CastService castService;

    @GetMapping(path = UserApiUrlConstants.ALL_CASTS)
    @ResponseBody
    public ResponseBaseData<List<CastModel>> getAllCasts() {
        return castService.getAllCasts();
    }

    @PostMapping(path = UserApiUrlConstants.SAVE_CAST)
    @ResponseBody
    public ResponseBaseData<CastModel> saveCast(@RequestBody CastModel castModel) {
        return castService.saveCast(castModel);
    }
}

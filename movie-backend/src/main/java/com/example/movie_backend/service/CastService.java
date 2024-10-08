package com.example.movie_backend.service;

import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.CastModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CastService {
    ResponseBaseData<CastModel> saveCast(CastModel castModel);

    ResponseBaseData<List<CastModel>> getAllCasts();
}

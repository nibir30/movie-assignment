package com.example.movie_backend.serviceImpl;

import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.CastModel;
import com.example.movie_backend.repository.CastRepository;
import com.example.movie_backend.service.CastService;
import com.example.movie_backend.util.IdGenerator;
import com.example.movie_backend.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CastServiceImpl implements CastService {
    private final CastRepository castRepository;

    @Override
    public ResponseBaseData<CastModel> saveCast(CastModel castModel) {
        try {
            if (castModel.getCastId() == null) {
                castModel.setCastId(IdGenerator.generateId());
                castModel.setInsertTime(LocalDateTime.now());
            }

            castModel.setUpdateTime(LocalDateTime.now());

            CastModel savedCase = castRepository.save(castModel);
            return ResponseUtils.dataSuccess("SUCCESSFULLY SAVED CAST", savedCase);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("COULD NOT SAVE CAST", e.getMessage());
        }
    }

    @Override
    public ResponseBaseData<List<CastModel>> getAllCasts() {
        return ResponseUtils.dataSuccess("HERE ARE THE CASTS", castRepository.findAll());
    }

}

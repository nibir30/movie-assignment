package com.example.movie_backend.serviceImpl;

import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.CategoryModel;
import com.example.movie_backend.repository.CategoryRepository;
import com.example.movie_backend.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseBaseData<CategoryModel> saveCategory(CategoryModel categoryModel) {
        try {
            if (categoryModel.getCategoryId() == null) {
                categoryModel.setCategoryId(IdGenerator.generateId());
                categoryModel.setInsertTime(LocalDateTime.now());
            }

            categoryModel.setUpdateTime(LocalDateTime.now());

            CategoryModel savedCase = categoryRepository.save(categoryModel);
            return ResponseUtils.dataSuccess("SUCCESSFULLY SAVED CATEGORY", savedCase);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("COULD NOT SAVE CATEGORY", e.getMessage());
        }
    }

    @Override
    public ResponseBaseData<List<CategoryModel>> getAllCategories() {
        return ResponseUtils.dataSuccess("HERE ARE THE CATEGORIES", categoryRepository.findAll());
    }

}

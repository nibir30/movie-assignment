package com.example.movie_backend.service;

import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.CategoryModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    ResponseBaseData<CategoryModel> saveCategory(CategoryModel CategoryModel);

    ResponseBaseData<List<CategoryModel>> getAllCategories();
}

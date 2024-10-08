package com.example.movie_backend.controller;

import com.example.movie_backend.controller.common.UserApiUrlConstants;
import com.example.movie_backend.data.ResData.core.ResponseBaseData;
import com.example.movie_backend.model.CategoryModel;
import com.example.movie_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(path = UserApiUrlConstants.ALL_CATEGORIES)
    @ResponseBody
    public ResponseBaseData<List<CategoryModel>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping(path = UserApiUrlConstants.SAVE_CATEGORY)
    @ResponseBody
    public ResponseBaseData<CategoryModel> saveCategory(@RequestBody CategoryModel categoryModel) {
        return categoryService.saveCategory(categoryModel);
    }
}

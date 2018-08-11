package com.app.horizon.core.store.online.services;

import com.app.horizon.core.store.offline.entities.category.CategoryResponse;
import com.app.horizon.core.store.offline.entities.question.QuestionResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("categories/read.php")
    Observable<CategoryResponse> fetchCategories();

    @GET("questions/read_paging.php?")
    Observable<QuestionResponse> fetchQuestions(@Query("category_id") String categoryId);
}

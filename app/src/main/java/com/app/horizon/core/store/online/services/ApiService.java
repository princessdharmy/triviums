package com.app.horizon.core.store.online.services;

import com.app.horizon.core.store.offline.entities.category.CategoryResponse;
import com.app.horizon.core.store.online.question.QuestionResponse;


import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("categories/read.php")
    Single<Response<CategoryResponse>> fetchCategories();

    @GET("questions/read_paging.php")
    Single<Response<QuestionResponse>> fetchStages(@Query("category_id") String categoryId);

    @GET("questions/read_paging.php")
    Single<Response<QuestionResponse>> fetchQuestions(@Query("category_id") String categoryId,
                                                      @Query("page") String pageNumber);
}

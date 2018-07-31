package com.app.horizon.core.store.online.services;

import com.app.horizon.core.store.offline.entities.category.Category;
import com.app.horizon.core.store.offline.entities.question.Question;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("categories/read.php")
    Observable<List<Category>> fetchCategories();

    @GET("questions/read_paging.php")
    Observable<List<Question>> fetchQuestions(@Query("category_id") String categoryId);
}

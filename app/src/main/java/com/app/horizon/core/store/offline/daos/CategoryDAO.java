package com.app.horizon.core.store.offline.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.app.horizon.core.store.offline.entities.category.Category;

import java.util.List;

import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CategoryDAO {

    @Insert(onConflict = REPLACE)
    void insert(List<Category> category);

    @Query("SELECT * FROM category_table")
    Single<List<Category>> getAll();
}

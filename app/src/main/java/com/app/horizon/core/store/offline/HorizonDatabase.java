package com.app.horizon.core.store.offline;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.app.horizon.core.store.offline.daos.CategoryDAO;
import com.app.horizon.core.store.offline.entities.category.Category;


@Database(version = 2, entities = {Category.class})
public abstract class HorizonDatabase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();

}

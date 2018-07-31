package com.app.horizon.core.store.offline;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.app.horizon.core.store.offline.daos.CategoryDAO;
import com.app.horizon.core.store.offline.daos.QuestionDAO;
import com.app.horizon.core.store.offline.entities.category.Category;
import com.app.horizon.core.store.offline.entities.question.Question;


@Database(version = 1, entities = {Question.class, Category.class})
public abstract class HorizonDatabase extends RoomDatabase {

    abstract QuestionDAO questionDAO();
    abstract CategoryDAO categoryDAO();
}

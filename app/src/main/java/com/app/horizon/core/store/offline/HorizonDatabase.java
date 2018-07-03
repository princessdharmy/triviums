package com.app.horizon.core.store.offline;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.app.horizon.core.store.offline.daos.QuestionDAO;
import com.app.horizon.core.store.offline.entities.Question;

/**
 * Created by Ayokunle Paul on 7/3/18.
 */
@Database(version = 1, entities = Question.class)
public abstract class HorizonDatabase extends RoomDatabase {
    abstract QuestionDAO getQuestionDAO();
}

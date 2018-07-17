package com.app.horizon.core.store.offline;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.app.horizon.core.store.offline.daos.QuestionDAO;
import com.app.horizon.core.store.offline.entities.Question;


@Database(version = 1, entities = Question.class)
public abstract class HorizonDatabase extends RoomDatabase {
    abstract QuestionDAO getQuestionDAO();
}

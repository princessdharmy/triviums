package com.app.horizon.core.store.offline.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.app.horizon.core.store.offline.entities.question.Question;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface QuestionDAO {

    @Query("SELECT * FROM question_table")
    Flowable<Question> getAllQuestion(long id);

    @Insert
    void insertQuestion(List<Question> questions);

    @Update
    void updateQuestion(Question question);
}

package com.app.horizon.core.store.offline.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.app.horizon.core.store.offline.entities.Question;

import java.util.List;

import io.reactivex.Flowable;
import rx.Observable;

/**
 * Created by Ayokunle Paul on 7/3/18.
 */
@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM QuestionTable WHERE questionId = :id")
    Flowable<Question> getAllQuestion(long id);

    @Insert
    void insertQuestion(List<Question> questions);

    @Update
    void updateQuestion(Question question);
}

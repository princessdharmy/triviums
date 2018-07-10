package com.app.horizon.core.store.offline.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ayokunle Paul on 7/3/18.
 */
@Entity(tableName = "QuestionTable")
public class Question {
    @PrimaryKey(autoGenerate = true)
    public long questionId;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}

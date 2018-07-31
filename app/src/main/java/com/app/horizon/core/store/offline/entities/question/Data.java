
package com.app.horizon.core.store.offline.entities.question;

import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("id")
    private String mId;

    @SerializedName("category_id")
    private String mCategoryId;

    @SerializedName("correct_answer")
    private String mCorrectAnswer;

    @SerializedName("option_a")
    private String mOptionA;

    @SerializedName("option_b")
    private String mOptionB;

    @SerializedName("option_c")
    private String mOptionC;

    @SerializedName("option_d")
    private String mOptionD;

    @SerializedName("question")
    private String mQuestion;

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOptionA() {
        return mOptionA;
    }

    public void setOptionA(String optionA) {
        mOptionA = optionA;
    }

    public String getOptionB() {
        return mOptionB;
    }

    public void setOptionB(String optionB) {
        mOptionB = optionB;
    }

    public String getOptionC() {
        return mOptionC;
    }

    public void setOptionC(String optionC) {
        mOptionC = optionC;
    }

    public String getOptionD() {
        return mOptionD;
    }

    public void setOptionD(String optionD) {
        mOptionD = optionD;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

}

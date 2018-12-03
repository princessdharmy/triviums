
package com.app.horizon.core.store.online.question;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.annotations.SerializedName;


public class Question {

    @NonNull
    @SerializedName("id")
    private String mId;

    @NonNull
    @SerializedName("category_id")
    private String mCategoryId;

    @SerializedName("question")
    private String mQuestion;

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

    public Question(@NonNull String mId, @NonNull String mCategoryId, String mQuestion,
                    String mCorrectAnswer, String mOptionA, String mOptionB, String mOptionC,
                    String mOptionD) {
        this.mId = mId;
        this.mCategoryId = mCategoryId;
        this.mQuestion = mQuestion;
        this.mCorrectAnswer = mCorrectAnswer;
        this.mOptionA = mOptionA;
        this.mOptionB = mOptionB;
        this.mOptionC = mOptionC;
        this.mOptionD = mOptionD;
    }

    @BindingAdapter("textView")
    public static void loadQuestion(TextView view, String text){
        Spanned formattedText = Html.fromHtml(text);
        view.setText(formattedText);
    }


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


    @Override
    public String toString() {
        return "Question{" +
                "mId='" + mId + '\'' +
                ", mCategoryId='" + mCategoryId + '\'' +
                ", mQuestion='" + mQuestion + '\'' +
                ", mCorrectAnswer='" + mCorrectAnswer + '\'' +
                ", mOptionA='" + mOptionA + '\'' +
                ", mOptionB='" + mOptionB + '\'' +
                ", mOptionC='" + mOptionC + '\'' +
                ", mOptionD='" + mOptionD + '\'' +
                '}';
    }
}

package com.app.horizon.core.store.online.question;

public class QuestionResultsResponse {
    QuestionResponse questionResponse;
    String error;

    public QuestionResultsResponse() {
    }

    public QuestionResultsResponse(QuestionResponse questionResponse, String error) {
        this.questionResponse = questionResponse;
        this.error = error;
    }

    public QuestionResponse getQuestionResponse() {
        return questionResponse;
    }

    public void setQuestionResponse(QuestionResponse questionResponse) {
        this.questionResponse = questionResponse;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

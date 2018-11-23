package com.app.horizon.core.store.online.question;

import java.util.Map;

public class FirestoreResultResponse {

    Map<String, Object> data;
    String Error;

    public FirestoreResultResponse() {
    }

    public FirestoreResultResponse(Map<String, Object> data, String error) {
        this.data = data;
        Error = error;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}

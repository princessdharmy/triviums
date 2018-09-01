
package com.app.horizon.core.store.offline.entities.question;

import com.google.gson.annotations.SerializedName;

public class Pages {

    @SerializedName("first_page")
    private String mFirstPage;
    @SerializedName("last_page")
    private String mLastPage;
    @SerializedName("next_page")
    private String mNextPage;

    public String getFirstPage() {
        return mFirstPage;
    }

    public void setFirstPage(String firstPage) {
        mFirstPage = firstPage;
    }

    public String getLastPage() {
        return mLastPage;
    }

    public void setLastPage(String lastPage) {
        mLastPage = lastPage;
    }

    public String getNextPage() {
        return mNextPage;
    }

    public void setNextPage(String nextPage) {
        mNextPage = nextPage;
    }

}


package com.app.horizon.core.store.offline.entities.question;

import com.google.gson.annotations.SerializedName;


public class Paging {

    @SerializedName("current_page")
    private Long mCurrentPage;
    @SerializedName("pages")
    private Pages mPages;
    @SerializedName("total_pages")
    private Long mTotalPages;

    public Long getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(Long currentPage) {
        mCurrentPage = currentPage;
    }

    public Pages getPages() {
        return mPages;
    }

    public void setPages(Pages pages) {
        mPages = pages;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

}

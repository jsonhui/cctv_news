package com.cctvnews.www.model.mmodel;

/**
 * 作者：Json on 2016/7/14 16:56
 * 邮箱：320175912@qq.com
 */
public class SearchData {

    private String title;
    private String count;

    public SearchData(String title, String count) {
        this.title = title;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SearchData{" +
                "title='" + title + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}

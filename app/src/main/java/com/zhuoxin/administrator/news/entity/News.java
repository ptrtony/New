package com.zhuoxin.administrator.news.entity;

/**
 * Created by Administrator on 2017/1/9.
 * 手动操作写的数据
 */

public class News {
    String uniqueke;
    String title;
    String date;
    String category;
    String url;
    String thumbnail_pic_s;

    public News(String uniqueke, String title, String date, String category, String url, String thumbnail_pic_s) {
        this.uniqueke = uniqueke;
        this.title = title;
        this.date = date;
        this.category = category;
        this.url = url;
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getUniqueke() {
        return uniqueke;
    }

    public void setUniqueke(String uniqueke) {
        this.uniqueke = uniqueke;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }
}

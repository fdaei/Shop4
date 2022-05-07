package com.example.shop4.model;

public class Category {

    private String id , title  , link_img;

    public Category() {
    }

    public Category(String id , String title, String link_img) {
        this.id = id;
        this.title = title;
        this.link_img = link_img;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

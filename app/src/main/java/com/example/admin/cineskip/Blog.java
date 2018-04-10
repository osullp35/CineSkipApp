package com.example.admin.cineskip;


/**
 * Created by Admin on 28/03/2018.
 */

public class Blog {

    private String title;
    private String image;

    public Blog(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Blog(){

    }
}

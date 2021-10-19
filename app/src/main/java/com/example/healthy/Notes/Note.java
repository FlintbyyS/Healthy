package com.example.healthy.Notes;

import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String discription;
    private int id = 0;

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

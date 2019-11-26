package com.android.yapp.scenetalker;

import android.graphics.Bitmap;

public class PostInfo {
    String content;
    Bitmap image;


    public PostInfo(String content,Bitmap image) {
        this.content = content;
        this.image = image;
    }

    public PostInfo(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}

package com.wordpress.priyankvex.paintapp.Model;

public class ItemObjectAndroidGridView {

    private String content;
    private String imageResource;

    public ItemObjectAndroidGridView(String content, String imageResource) {
        this.content = content;
        this.imageResource = imageResource;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }
}

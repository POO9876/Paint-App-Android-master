package com.wordpress.priyankvex.paintapp.Model;

/**
 * Created by PongPloy2016 on 21/1/2560.
 */

public class ItemObject {

    private String name;
    private int Number ;
    private int photo;

    public ItemObject(String name, int photo, int number) {
        this.name = name;
        this.photo = photo;
        Number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return Number;
    }

//    public void setNumber(int number) {
//        Number = number;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}

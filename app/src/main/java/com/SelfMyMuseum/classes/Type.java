package com.SelfMyMuseum.classes;

/**
 * Created by hasee on 2019/12/10.
 */

public class Type {
    private String name;
    private int imageId;


    public Type(String title, int imageId){
        this.name=title;
        this.imageId=imageId;

    }

    public String getName(){
        return name;
    }

    public int getImageid(){
        return imageId;
    }

}

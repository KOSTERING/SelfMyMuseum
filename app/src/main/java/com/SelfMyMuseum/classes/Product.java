package com.SelfMyMuseum.classes;

/**
 * Created by hasee on 2019/12/10.
 */

public class Product {
    private String title;
    private int imageId;
    private String author;
    private String size;
    private String Describe;
    private String searchtype;

    public Product(String title, int imageId, String author, String size, String describe, String searchtype){
        this.title=title;
        this.imageId=imageId;
        this.author=author;
        this.size=size;
        this.Describe=describe;
        this.searchtype=searchtype;
    }

    public String getTitle(){
        return title;
    }

    public int getImageid(){
        return imageId;
    }

    public String getDescribe(){
        return Describe;
    }

    public String getSize(){
        return size;
    }

    public String getAuthor(){
        return author;
    }

    public String getSearchtype(){
        return searchtype;
    }
}

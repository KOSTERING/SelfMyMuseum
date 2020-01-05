package com.SelfMyMuseum.classes;

/**
 * Created by hasee on 2019/12/10.
 */

public class Exhibitshort {
    private String title;
    private int imageId;
    private String shortDescribe;
    private String starttime;
    private String endtime;

    public Exhibitshort(String title,int imageId,String shortDescribe,String starttime,String endtime){
        this.title=title;
        this.imageId=imageId;
        this.shortDescribe=shortDescribe;
        this.starttime=starttime;
        this.endtime=endtime;
    }

    public String getTitle(){
        return title;
    }

    public int getImageid(){
        return imageId;
    }

    public String getShortDescribe(){
        return shortDescribe;
    }

    public String getStarttime(){
        return starttime;
    }

    public String getEndtime(){
        return endtime;
    }
}

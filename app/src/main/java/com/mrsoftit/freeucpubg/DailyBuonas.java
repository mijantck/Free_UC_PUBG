package com.mrsoftit.freeucpubg;

public class DailyBuonas {


    private  int count;
    private int date;
    private String id;
    private  boolean firstTime;



    public DailyBuonas(){}

    public DailyBuonas(int count, int date, String id, boolean firstTime) {
        this.count = count;
        this.date = date;
        this.id = id;
        this.firstTime = firstTime;
    }

    public int getCount() {
        return count;
    }

    public int getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public boolean isFirstTime() {
        return firstTime;
    }
}

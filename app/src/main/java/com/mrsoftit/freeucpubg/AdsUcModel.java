package com.mrsoftit.freeucpubg;

public class AdsUcModel {

    private double ucAmount;
    private int count;
    private int date;
    private boolean firstTime;
    private String ucid;

    public AdsUcModel(){

    }

    public AdsUcModel(double ucAmount, int count, int date, boolean firstTime, String ucid) {
        this.ucAmount = ucAmount;
        this.count = count;
        this.date = date;
        this.firstTime = firstTime;
        this.ucid = ucid;
    }

    public AdsUcModel(double ucAmount,String id) {
        this.ucAmount = ucAmount;
        this.ucid = id;
    }

    public AdsUcModel(double ucAmount, int count, boolean firstTime,String ucid) {
        this.ucAmount = ucAmount;
        this.count = count;
        this.firstTime = firstTime;
        this.ucid = ucid;
    }

    public AdsUcModel(double ucAmount, int date,String ucid) {
        this.ucAmount = ucAmount;
        this.date = date;
        this.ucid = ucid;
    }


    public double getUcAmount() {
        return ucAmount;
    }

    public int getCount() {
        return count;
    }

    public int getDate() {
        return date;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public String getUcid() {
        return ucid;
    }
}

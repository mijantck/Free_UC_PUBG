package com.mrsoftit.freeucpubg;

public class OrderListModle {
    private String status;
    private String customarName;
    private String customarEmgail;
    private String ucAmu;
    private String ucTaka;
    private String id;
    private int date;
    private String nickName;
    private String bkasTransectionId;
    private String userID;
    private String ducID;


    public OrderListModle(){}

    public OrderListModle(String status, String customarName, String customarEmgail, String ucAmu, String ucTaka, String id, int date, String nickName, String bkasTransectionId, String userID, String ducID) {
        this.status = status;
        this.customarName = customarName;
        this.customarEmgail = customarEmgail;
        this.ucAmu = ucAmu;
        this.ucTaka = ucTaka;
        this.id = id;
        this.date = date;
        this.nickName = nickName;
        this.bkasTransectionId = bkasTransectionId;
        this.userID = userID;
        this.ducID = ducID;
    }

    public String getStatus() {
        return status;
    }

    public String getCustomarName() {
        return customarName;
    }

    public String getCustomarEmgail() {
        return customarEmgail;
    }

    public String getUcAmu() {
        return ucAmu;
    }

    public String getUcTaka() {
        return ucTaka;
    }

    public String getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public String getNickName() {
        return nickName;
    }

    public String getBkasTransectionId() {
        return bkasTransectionId;
    }

    public String getUserID() {
        return userID;
    }

    public String getDucID() {
        return ducID;
    }
}

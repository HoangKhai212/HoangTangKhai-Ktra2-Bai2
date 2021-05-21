package com.example.hoangtangkhai_ktra2_bai2.model;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String name;
    private String date;
    private String nganh;
    private int kichhoat;

    public Course() {
    }

    public Course(int id, String name, String date, String nganh, int kichhoat) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.nganh = nganh;
        this.kichhoat = kichhoat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public int getKichhoat() {
        return kichhoat;
    }

    public void setKichhoat(int kichhoat) {
        this.kichhoat = kichhoat;
    }
}

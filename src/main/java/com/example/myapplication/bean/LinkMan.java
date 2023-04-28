package com.example.myapplication.bean;

public class LinkMan {
    private int id;
    private String name;
    private String number;
    private String address;
    private String vchat;
    private String photoId;

    public LinkMan() {}
    public LinkMan(String name, String number, String address, String vchat, String photoId) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.vchat = vchat;
        this.photoId = photoId;
    }


    @Override
    public String toString() {
        return "LinkMan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", vchat='" + vchat + '\'' +
                ", photoId=" + photoId +
                '}';
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVchat() {
        return vchat;
    }

    public void setVchat(String vchat) {
        this.vchat = vchat;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public LinkMan(int id, String name, String number, String address, String vchat, String photoId) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address = address;
        this.vchat = vchat;
        this.photoId = photoId;
    }
}

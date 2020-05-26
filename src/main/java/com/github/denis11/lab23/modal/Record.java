package com.github.denis11.lab23.modal;

public class Record {
    private String name;
    private String mail;
    private String url;
    private int id = -1;

    public Record(String name, String mail, String url) {
        this.name = name;
        this.url = url;
        this.mail = mail;
    }

    public Record(String name, String mail, String url, int id) {
        this.name = name;
        this.url = url;
        this.mail = mail;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }
}

package com.example.cp17312_nhom6_duan1.dto;

public class DTO_TimeWork {
    private int id;
    private String session;

    public DTO_TimeWork() {
    }

    public DTO_TimeWork(int id, String session) {
        this.id = id;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}

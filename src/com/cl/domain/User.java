package com.cl.domain;

public class User {

    private String name;
    private String password;
    private int id;
    private int grade;
    private String email;
    public String getName() {
        return name;
    }

    public User() {

    }

    public User(int id, String name, String email, int grade, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

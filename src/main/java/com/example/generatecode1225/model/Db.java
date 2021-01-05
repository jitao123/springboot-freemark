package com.example.generatecode1225.model;

/**
 * @description:
 * @author: AT
 * @Date: 2020/12/25 2:01 下午
 */
public class Db {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String username;
    private String password;
    private String url;
}

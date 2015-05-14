package ru.devit.DB;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by user on 23.04.2015.
 */
public class DB_UserData {
    private int id = -1;
    private String email;
    @JsonIgnore
    private String pwd;
    private String name;
    private String reg_date;
    private String last_logon;
    private int is_online;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getLast_logon() {
        return last_logon;
    }

    public void setLast_logon(String last_logon) {
        this.last_logon = last_logon;
    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}

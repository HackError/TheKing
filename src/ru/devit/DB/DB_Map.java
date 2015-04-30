package ru.devit.DB;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by user on 23.04.2015.
 */
public class DB_Map {
    @JsonIgnore
    private Integer id = 0;
    private Integer x = 0;
    private Integer y = 0;
    private Integer owner = 0;
    private Integer start_position = 0;
    private Integer ground_type = 0;
    private Integer server = 0;
    private Integer non_user_owner = 0;
    private String style_class = "";
    @JsonIgnore
    private Integer passability = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getStart_position() {
        return start_position;
    }

    public void setStart_position(Integer start_position) {
        this.start_position = start_position;
    }

    public Integer getGround_type() {
        return ground_type;
    }

    public void setGround_type(Integer ground_type) {
        this.ground_type = ground_type;
    }

    public Integer getServer() {
        return server;
    }

    public void setServer(Integer server) {
        this.server = server;
    }

    public Integer getNon_user_owner() {
        return non_user_owner;
    }

    public void setNon_user_owner(Integer non_user_owner) {
        this.non_user_owner = non_user_owner;
    }

    public String getStyle_class() {
        return style_class;
    }

    public void setStyle_class(String style_class) {
        this.style_class = style_class;
    }

    public Integer getPassability() {
        return passability;
    }

    public void setPassability(Integer passability) {
        this.passability = passability;
    }

    public String toString()
    {
        return "map id=" + this.getId() + " position(x,y): " + this.getX() + ", " + this.getY();
    }
}

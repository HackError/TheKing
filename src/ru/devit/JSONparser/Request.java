package ru.devit.JSONparser;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * Created by user on 24.04.2015.
 */
public class Request {

    @JsonProperty("type")
    private String type = "";
    @JsonProperty("data")
    private HashMap data = new HashMap<String, Object>();

    public Request(){}

    public Request ( String t, HashMap d) {
        this.type = t;
        this.data = d;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }
}

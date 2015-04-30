package ru.devit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import ru.devit.DB.DB_UserData;
import ru.devit.DB.DB_UserPeasantWork;
import ru.devit.DB.DB_UserResources;

import java.util.Formatter;

/**
 * Created by user on 23.04.2015.
 */
public class User {

    private DB_UserData userData = new DB_UserData();
    private DB_UserResources userResources = new DB_UserResources();
    private DB_UserPeasantWork userPeasantWork = new DB_UserPeasantWork();
    @JsonIgnore
    private ChannelHandlerContext ctx = null;
    private Integer hashCode = 0;
    private int authorize = 0;

    public Integer getHashCode() {
        return hashCode;
    }

    public void setHashCode(Integer hashCode) {
        this.hashCode = hashCode;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        this.setHashCode(ctx.hashCode());
    }

    public DB_UserData getUserData() {
        return userData;
    }

    public void setUserData(DB_UserData userData) {
        this.userData = userData;
    }

    public DB_UserResources getUserResources() {
        return userResources;
    }

    public void setUserResources(DB_UserResources userResources) {
        this.userResources = userResources;
    }

    public int getAuthorize() {
        return authorize;
    }

    public void setAuthorize(int authorize) {
        this.authorize = authorize;
    }

    public DB_UserPeasantWork getUserPeasantWork() {
        return userPeasantWork;
    }

    public void setUserPeasantWork(DB_UserPeasantWork userPeasantWork) {
        this.userPeasantWork = userPeasantWork;
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }


    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb);
        f.format("User ID: %s, name: %s", this.getUserData().getId(), this.getUserData().getName());
        return f.toString();
    }
}

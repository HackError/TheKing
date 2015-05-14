package ru.devit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import ru.devit.DB.DB_UserData;
import ru.devit.DB.DB_UserPeasantWork;
import ru.devit.DB.DB_UserResources;
import ru.devit.events.Event;

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
    @JsonIgnore
    private Integer hashCode = 0;
    private boolean authorize = false;
    private Event event = null;

    /**
     * КОНСТРУКТОР
     * @param ud
     * @param ur
     * @param upw
     */
    public User( DB_UserData ud, DB_UserResources ur, DB_UserPeasantWork upw )
    {
        userData = ud;
        userResources = ur;
        userPeasantWork = upw;
    }

    /**
     * ЗАПРО ХЭШ КОДА СОЕДИНЕНИЯ
     * @return
     */
    public Integer getHashCode() {
        return hashCode;
    }

    /**
     * УСТАНОВКА ХЭШ КОДА
     * @param hashCode
     */
    public void setHashCode(Integer hashCode) {
        this.hashCode = hashCode;
    }

    /**
     * ЗАПРОС ПОЛЬЗОВАТЕЛЬСКОГО СОЕДИНЕНИЯ
     * @return
     */
    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    /**
     * УСТАНОВКА ПОЛЬЗОВАТЕЛЮ СОЕДИНЕНИЯ
     * @param ctx
     */
    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        this.setHashCode(ctx.hashCode());
    }

    /**
     * ЗАПРОС ПОЛЬЗОВАТЕЛЬСКИХ ДАННЫХ
     * @return
     */
    public DB_UserData getUserData() {
        return userData;
    }

    /**
     * ЗАПРОС ПОЛЬЗОВАТЕЛЬСКИХ РЕСУРСОВ
     * @return
     */
    public DB_UserResources getUserResources() {
        return userResources;
    }

    /**
     * ЗАПРОС ПОЛЬЗОВАТЕЛЬСКИХ НАСТРОЕК ПО РАСПРЕДЕЛЕНИЮ КРЕСТЬЯН
     * @return
     */
    public DB_UserPeasantWork getUserPeasantWork() {
        return userPeasantWork;
    }

    /**
     * ЗАПРОС АВТОРИЗАЦИИ ПОЛЬЗОВАТЕЛЯ
     * @return
     */
    public boolean getAuthorize() {
        return authorize;
    }

    /**
     * УСТАНОВКА ФЛАГА АВТОРИЗАЦИИ
     * @param authorize
     */
    public void setAuthorize(boolean authorize) {
        this.authorize = authorize;
        if ( authorize == true )
            userData.setIs_online(1);
        else
            userData.setIs_online(0);
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



    /*
    public void setUserData(DB_UserData userData) {
        this.userData = userData;
    }

    public void setUserResources(DB_UserResources userResources) {
        this.userResources = userResources;
    }

    public void setUserPeasantWork(DB_UserPeasantWork userPeasantWork) {
        this.userPeasantWork = userPeasantWork;
    }
    */

}

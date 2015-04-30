package ru.devit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import ru.devit.DB.*;
import ru.devit.JSONparser.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 23.04.2015.
 */
public class Users {
    private static Map<Integer, User> users = new HashMap<Integer, User>();

    /**
     * загрузка всех пользователей из БД
     */
    public static void getAllUsersFromDB()
    {
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<DB_UserData> usersData = userDAO.selectAllUsers();
        for (DB_UserData ud: usersData){
            DB_UserResources ur = userDAO.getUserResources(ud.getId());
            DB_UserPeasantWork upw = userDAO.getUserPW(ud.getId());
            User user = new User();
            user.setUserData(ud);
            user.setUserResources(ur);
            user.setUserPeasantWork(upw);
            addUser(user);
            System.out.printf("User %s loaded", user.getUserData().getName());
            System.out.println();
        }
    }
    /**
     * Добавление нового пользователя в карту пользователей
     * @param user
     */
    public static void addUser( User user )
    {
        users.put(user.getUserData().getId(), user);
    }

    /**
     * Берем пользователя по его ID
     * @param id
     * @return
     */
    public static User getUserForId(Integer id)
    {
        return users.get(id);
    }

    /**
     * Берем пользователя по хэшу соединения
     * @param hashCode
     * @return
     */
    public static User getUser(int hashCode)
    {
        for ( Map.Entry<Integer, User> entry: users.entrySet() ) {
            if (entry.getValue().getHashCode() == hashCode) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Берем соединение пользователя по хэшкоду
     * @param hashCode
     * @return
     */
    public static ChannelHandlerContext getCtx(int hashCode)
    {
        for ( Map.Entry<Integer, User> entry: users.entrySet() ) {
            if (entry.getValue().getHashCode() == hashCode)
                return entry.getValue().getCtx();
        }
        return null;
    }

    /**
     * Отключение пользователя
     * @param hashCode
     */
    public static void setUserOffline (int hashCode) {
        User user = getUser(hashCode);
        if ( user != null ) {
            user.setAuthorize(0);
            user.setHashCode(0);
            user.getUserData().setIs_online(0);
            //user.setCtx(null);
            System.out.println(user + " is offline");
        }

    }
}

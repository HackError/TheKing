package ru.devit.DB;

import ru.devit.Server;
import ru.devit.User;
import ru.devit.gameObjects.ResourceCalculator;

import java.util.Map;

/**
 * Created by user on 14.05.2015.
 */
public class SaveToDB {

    public static boolean saveUsers()
    {
        //сохранение пользователей
        Map<Integer, User> users = Server.users.getUsers();
        for ( Integer key : users.keySet() ) {
            User user = users.get(key);
            UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            userDAO.updateUser(user);
        }
        return true;
    }

    public static boolean saveMap()
    {
        return true;
    }

}

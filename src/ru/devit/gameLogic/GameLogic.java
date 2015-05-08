package ru.devit.gameLogic;

import ru.devit.Server;
import ru.devit.User;
import ru.devit.Users;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by user on 08.05.2015.
 */
public class GameLogic {



    public static void turn()
    {
        /**
         * ДАТА
         */
        Calendar date = Server.getGameConfig().getDate();
        /**
         * ПОЛЬЗОВАТЕЛИ
         */
        Map<Integer, User> users = Server.getUsers().getUsers();
        for ( Integer key : users.keySet() ) {
            User user = users.get(key);
            System.out.println(user.getUserData().getName());
        }
    }

}

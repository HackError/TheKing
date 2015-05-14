package ru.devit.gameLogic;

import ru.devit.DB.DB_UserResources;
import ru.devit.DB.SaveToDB;
import ru.devit.Server;
import ru.devit.User;
import ru.devit.gameObjects.ResourceCalculator;
import ru.devit.gameObjects.Resources;
import ru.devit.map.GameMap;

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
        date.add(Calendar.DATE, 1);
        Server.getGameConfig().setDate(date);
        /**
         * ОБРАБОТКА ПОЛЬЗОВАТЕЛЕЙ
         */
        Map<Integer, User> users = Server.getUsers().getUsers();
        for ( Integer key : users.keySet() ) {
            User user = users.get(key);

            ResourceCalculator.calc(user);

            System.out.println(user.getUserData().getName());
            System.out.println(user.getUserResources());
        }
    }

}

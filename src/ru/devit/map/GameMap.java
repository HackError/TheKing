package ru.devit.map;

import ru.devit.DB.DB_Map;
import ru.devit.DB.MapDAO;
import ru.devit.DB.MyBatisConnectionFactory;
import ru.devit.Server;
import ru.devit.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 27.04.2015.
 */
public class GameMap {

    private HashMap<Integer, DB_Map> map = new HashMap<Integer, DB_Map>();
    private static int mapWidth = 0;

    public GameMap(){
        /*загрузка карты*/
        MapDAO mapDAO = new MapDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<DB_Map> listMap = mapDAO.selectAll();

        setMapWidth(listMap);

        for (DB_Map m : listMap) {
            int node = GameMap.node(m.getX(), m.getY());
            map.put(node, m);
        }

        System.out.println("Maps loaded");
    }

    public HashMap getMap()
    {
        return map;
    }

    public DB_Map getMap( int node )
    {
        return map.get( node );
    }

    private static void setMapWidth ( List<DB_Map> listMap )
    {
        for (DB_Map m : listMap) {
            if ( m.getX() > mapWidth )
                mapWidth = m.getX();
        }
        mapWidth++;
    }

    public static Integer getMapWidth()
    {
        return mapWidth;
    }


    /**
     * возвращает ноду
     * @param x
     * @param y
     * @return
     */
    public static int node(int x, int y)
    {
        return y * mapWidth + x;
    }

    /**
     * возвращает координаты по ноде
     * @param node
     * @return
     */
    public static int[] coord(int node)
    {
        int x = node % mapWidth;
        int y = ( node - x ) / mapWidth;
        return new int[]{x, y};
    }


    /**
     * ЗАХВАТ ЗЕМЛИ ПОЛЬЗОВАТЕЛЕМ
     * @param userID
     * @param node
     */
    public void userTakeGround( int userID, int node )
    {
        DB_Map db_map = getMap(node);
        db_map.setOwner(userID);
        MapDAO mapDAO = new MapDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        mapDAO.updateMap(db_map);
    }

}

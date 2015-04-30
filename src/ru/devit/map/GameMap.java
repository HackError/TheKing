package ru.devit.map;

import ru.devit.DB.DB_Map;
import ru.devit.DB.MapDAO;
import ru.devit.DB.MyBatisConnectionFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 27.04.2015.
 */
public class GameMap {

    private HashMap map = new HashMap<Integer, DB_Map>();
    private static Integer mapWidth = 0;

    public GameMap(){
        /*загрузка карты*/
        MapDAO mapDAO = new MapDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<DB_Map> listMap = mapDAO.selectAll();
        getMapWidth(listMap);
        for (DB_Map m : listMap) {
            int node = GameMap.node(m.getX(), m.getY());
            map.put(node, m);
        }

        System.out.println("Карты загружены");
    }

    public HashMap getMap()
    {
        return map;
    }

    private static Integer getMapWidth (List<DB_Map> listMap)
    {
        if (mapWidth > 0)
            return mapWidth;

        for (DB_Map m : listMap) {
            if ( m.getX() > mapWidth )
                mapWidth = m.getX();
        }
        mapWidth++;

        return mapWidth;
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


}

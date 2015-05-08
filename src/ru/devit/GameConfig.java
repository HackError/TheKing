package ru.devit;

import ru.devit.DB.ConfigDAO;
import ru.devit.DB.MyBatisConnectionFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by user on 08.05.2015.
 */
public class GameConfig {

    private HashMap<String, HashMap> config = new HashMap();

    public Object getConfigParam(String key) {
        return config.get(key).get("value");
    }

    public GameConfig() {
        ConfigDAO configDAO = new ConfigDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        config = configDAO.selectAll();
        System.out.println("Configuration loaded");
    }

    public Calendar getDate()
    {
        String date = (String)getConfigParam("GAME_DATE");
        System.out.println(date);
        String[] ds = date.split("\\-");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar(Integer.parseInt(ds[0]), Integer.parseInt(ds[1])-1, Integer.parseInt(ds[2]));
        System.out.println(sdf.format(calendar.getTime()));
        return calendar;
    }

    public void setDate( Calendar date )
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        config.put("GAME_DATE", sdf.format(date.getTime()));
    }
}

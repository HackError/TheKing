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
        String[] ds = date.split("\\-");
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar(Integer.parseInt(ds[0]), Integer.parseInt(ds[1])-1, Integer.parseInt(ds[2]));
        return calendar;
    }

    public void setDate( Calendar date )
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        config.get("GAME_DATE").put("value", sdf.format(date.getTime()));
        System.out.println(sdf.format(date.getTime()));
    }

    public float getGrainRegeneration ()
    {
        return Float.parseFloat( (String)getConfigParam("GRAIN_REGENERATION") );
    }

    public float getWoodRegeneration ()
    {
        return Float.parseFloat( (String)getConfigParam("WOOD_REGENERATION") );
    }

    public float getGoldRegeneration ()
    {
        return Float.parseFloat( (String)getConfigParam("GOLD_REGENERATION") );
    }

    public float getGrainEatingPeasant ()
    {
        return Float.parseFloat( (String)getConfigParam("GRAIN_EATING_PEASANT") );
    }

    public float getGrainEatingSoldier ()
    {
        return Float.parseFloat( (String)getConfigParam("GRAIN_EATING_SOLDIER") );
    }

    public float getGoldPaySoldier ()
    {
        return Float.parseFloat( (String)getConfigParam("GOLD_PAY_SOLDIER") );
    }

    public float getPeasantGrowth ()
    {
        return Float.parseFloat( (String)getConfigParam("PEASANT_GROWTH") );
    }

    public float getPeasantOnGround ()
    {
        return Float.parseFloat( (String)getConfigParam("PEASANT_ON_GROUND") );
    }

    public float getPeasantDieOnHunger ()
    {
        return Float.parseFloat( (String)getConfigParam("PEASANT_DIE_ON_HUNGER") );
    }
}

package ru.devit.gameObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 29.04.2015.
 */
public class Resources {

    HashMap<String,Resource> resources = new HashMap<String, Resource>();

    public Resources()
    {
        initResources();
    }
    public Resources(String res, float count)
    {
        initResources();
        getResource(res).setCount(count);
    }
    public Resources(String res, float count, String res2, float count2)
    {
        initResources();
        getResource(res).setCount(count);
        getResource(res2).setCount(count2);
    }
    public Resources(String res, float count, String res2, float count2, String res3, float count3)
    {
        initResources();
        getResource(res).setCount(count);
        getResource(res2).setCount(count2);
        getResource(res3).setCount(count3);
    }

    private void initResources()
    {
        setResource(new Resource("дерево", "wood"));
        setResource(new Resource("золото", "gold"));
        setResource(new Resource("зерно", "grain"));
        setResource(new Resource("крестьяне", "peasant"));
        setResource(new Resource("солдаты", "soldiers"));
        setResource(new Resource("деньги", "money"));
    }

    public void setResource(Resource res)
    {
        resources.put(res.getKey(), res);
    }
    public Resource getResource(String key)
    {
        return resources.get(key);
    }

    public HashMap<String, Resource> getAllResources()
    {
        return resources;
    }

    public boolean getNeedInput()
    {
        boolean ret = false;
        for (Map.Entry<String, Resource> entry : resources.entrySet() ) {
            if ( entry.getValue().getCount() == -1 )
                ret = true;
        }
        return ret;
    }

    public void setWood(Float wood) {
        resources.get("wood").setCount(wood);
    }
    public void setGold(Float gold) {
        resources.get("gold").setCount(gold);
    }
    public void setGrain(Float grain) {
        resources.get("grain").setCount(grain);
    }
    public void setPeasant(Float peasant) {
        resources.get("peasant").setCount(peasant);
    }
    public void setSoldiers(Float soldiers) {
        resources.get("soldiers").setCount(soldiers);
    }
    public void setMoney(Float money) {
        resources.get("money").setCount(money);
    }
    public Float getWood(){
        return resources.get("wood").getCount();
    }
    public Float getGold(){
        return resources.get("gold").getCount();
    }
    public Float getGrain(){
        return resources.get("grain").getCount();
    }
    public Float getPeasant(){
        return resources.get("peasant").getCount();
    }
    public Float getSoldiers(){
        return resources.get("soldiers").getCount();
    }
    public Float getMoney(){
        return resources.get("money").getCount();
    }

    public class Resource {

        private String name = "";
        private String key = "";
        private float count = 0;

        public Resource(String name, String key)
        {
            setName(name);
            setKey(key);
        }
        public Resource(String name, String key, float count)
        {
            setName(name);
            setKey(key);
            setCount(count);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public float getCount() {
            return count;
        }

        public void setCount(float count) {
            this.count = count;
        }

        public void setCount(int count) {
            this.count = (float)count;
        }
    }

}

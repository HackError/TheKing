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
        setResource(new Resource("дерево", "wood"));
        setResource(new Resource("золото", "gold"));
        setResource(new Resource("зерно", "grain"));
        setResource(new Resource("крестьяне", "peasant"));
        setResource(new Resource("солдаты", "soldiers"));
    }
    public Resources(String res, float count)
    {
        setResource(new Resource("дерево", "wood"));
        setResource(new Resource("золото", "gold"));
        setResource(new Resource("зерно", "grain"));
        setResource(new Resource("крестьяне", "peasant"));
        setResource(new Resource("солдаты", "soldiers"));
        getResource(res).setCount(count);
    }
    public Resources(String res, float count, String res2, float count2)
    {
        setResource(new Resource("дерево", "wood"));
        setResource(new Resource("золото", "gold"));
        setResource(new Resource("зерно", "grain"));
        setResource(new Resource("крестьяне", "peasant"));
        setResource(new Resource("солдаты", "soldiers"));
        getResource(res).setCount(count);
        getResource(res2).setCount(count2);
    }
    public Resources(String res, float count, String res2, float count2, String res3, float count3)
    {
        setResource(new Resource("дерево", "wood"));
        setResource(new Resource("золото", "gold"));
        setResource(new Resource("зерно", "grain"));
        setResource(new Resource("крестьяне", "peasant"));
        setResource(new Resource("солдаты", "soldiers"));
        getResource(res).setCount(count);
        getResource(res2).setCount(count2);
        getResource(res3).setCount(count3);
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

package ru.devit.DB;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by georgys on 28.04.2015.
 */
public class ConfigDAO {
    private SqlSessionFactory sqlSessionFactory = null;
    public ConfigDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, HashMap> selectAll(){
        HashMap<String, HashMap> hm = new HashMap<String,HashMap>();
        List<HashMap> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("config.selectAll");
        } finally {
            session.close();
        }

        for ( HashMap _hm : list ) {
            hm.put((String) _hm.get("key"), _hm);
        }

        return hm;
    }
}

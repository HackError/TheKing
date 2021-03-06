package ru.devit.DB;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by user on 27.04.2015.
 */
public class MapDAO {
    private SqlSessionFactory sqlSessionFactory = null;
    public MapDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<DB_Map> selectAll(){
        List<DB_Map> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("DBMap.selectAll");

        } finally {
            session.close();
        }
        return list;
    }

    public void updateMap(DB_Map db_map){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("DBMap.update", db_map);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update map("+db_map.getId()+") --> updated");
    }



}

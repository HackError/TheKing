package ru.devit.DB;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ru.devit.User;

import java.util.List;

/**
 * Created by user on 23.04.2015.
 */
public class UserDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public UserDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Returns the list of all Person instances from the database.
     * @return the list of all Person instances from the database.
     */
    @SuppressWarnings("unchecked")
    public List<DB_UserData> selectAllUsers(){
        List<DB_UserData> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("UserData.selectAll");
        } finally {
            session.close();
        }
        return list;

    }

    public DB_UserData selectUserByLP(DB_UserData u)
    {
        DB_UserData user = new DB_UserData();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            user = session.selectOne("UserData.selectByLP", u);
        } finally {
            session.close();
        }
        return user;
    }
    /**
     *
     * @param id
     * @return
     */
    public DB_UserData selectUserById(int id){
        DB_UserData user = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            user = session.selectOne("UserData.selectById", id);

        } finally {
            session.close();
        }
        System.out.println("selectById("+id+") --> "+user);
        return user;
    }
    /**
     * Insert an instance of Person into the database.
     * @param user the instance to be persisted.
     */
    public int insertUser(DB_UserData user){
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("UserData.insert", user);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert("+user+") --> "+user.getId());
        return id;
    }
    /**
     * Update an instance of Person into the database.
     * @param user the instance to be persisted.
     */
    public void updateUser(DB_UserData user){
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("UserData.update", user);

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update("+user+") --> updated");
    }

    /**
     * Delete an instance of Person from the database.
     * @param id value of the instance to be deleted.
     */
    public void deleteUser(int id){

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Users.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete("+id+")");

    }

    /**
     * загрузка пользовательских ресурсов
     * @param uid
     * @return
     */
    public DB_UserResources getUserResources (int uid)
    {
        DB_UserResources ur = new DB_UserResources();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ur = session.selectOne("userResources.selectByUserId", uid);
        } finally {
            session.close();
        }
        return ur;
    }

    /**
     * загрузка распределения крестьян по работам
     * @param uid
     * @return
     */
    public DB_UserPeasantWork getUserPW (int uid)
    {
        DB_UserPeasantWork upw = new DB_UserPeasantWork();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            upw = session.selectOne("userPW.selectByUserId", uid);
        } finally {
            session.close();
        }
        return upw;
    }
}

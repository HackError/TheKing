package ru.devit.authorization;

import io.netty.channel.ChannelHandlerContext;
import ru.devit.DB.DB_UserData;
import ru.devit.DB.MyBatisConnectionFactory;
import ru.devit.DB.UserDAO;
import ru.devit.JSONparser.Request;
import ru.devit.User;
import ru.devit.Users;

import java.util.HashMap;

/**
 * Created by user on 24.04.2015.
 */
public class Authorization {

    public static Request Autorization(Request request, ChannelHandlerContext ctx)
    {
        HashMap data = new HashMap();
        DB_UserData ud = new DB_UserData();
        ud.setEmail((String) request.getData().get("login"));
        ud.setPwd((String) request.getData().get("pwd"));
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        ud = userDAO.selectUserByLP(ud);

        if (ud != null && ud.getId() > 0) {
            User user = Users.getUserForId(ud.getId());
            user.setCtx(ctx);
            user.setAuthorize(1);
            user.getUserData().setIs_online(1);
            System.out.printf("%s is authorize", user);
            data.put("auth", 1);
            data.put("user", user);
        } else {
            System.out.printf("User login: %s is NOT authorize. Wrong LP.", request.getData().get("login"));
            data.put("auth", 0);
        }
        System.out.println();
        Request ret = new Request("authorization", data);
        return ret;
    }

}

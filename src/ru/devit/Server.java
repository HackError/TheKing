package ru.devit;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import ru.devit.DB.ConfigDAO;
import ru.devit.DB.MyBatisConnectionFactory;
import ru.devit.JSONparser.Converter;
import ru.devit.JSONparser.Request;
import ru.devit.map.GameMap;
import ru.devit.network.WebSocketServer;
import java.util.HashMap;

/**
 * Created by georgys on 25.04.2015.
 */
public class Server {
    private static HashMap<String, HashMap> config = new HashMap();
    private static GameMap gameMap = null;

    public static Object getConfig(String key) {
        return config.get(key).get("value");
    }
    public static void setConfig() {
        ConfigDAO configDAO = new ConfigDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        Server.config = configDAO.selectAll();
        System.out.println("Configuration loaded");
    }

    public static void init() {
        //загрузка конфига
        setConfig();
        //загрузка игровой карты
        gameMap = new GameMap();
        //загрузка пользователей
        Users.getAllUsersFromDB();

        //открываем сокет
        try {
            WebSocketServer.main();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void sendInitToClient(ChannelHandlerContext ctx) throws JsonProcessingException {
        System.out.println(ctx.hashCode());
        HashMap hm = new HashMap<String, Object>();
        /*отправка карты клиенту*/
        hm.put("map", gameMap.getMap());
        hm.put("mapWidth", GameMap.getMapWidth());
        Request request = new Request("initMain", hm);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(Converter.toJSON(request)));
    }
}

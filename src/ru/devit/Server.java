package ru.devit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import ru.devit.JSONparser.Converter;
import ru.devit.JSONparser.Request;
import ru.devit.map.GameMap;
import ru.devit.network.WebSocketServer;
import java.util.HashMap;


/**
 * Created by georgys on 25.04.2015.
 */
public class Server {
    private static HashMap config = new HashMap();

    private static GameMap gameMap = null;

    public static HashMap getConfig() {
        return config;
    }
    public static void setConfig(HashMap config) {
        Server.config = config;
    }

    public static void init() {
        gameMap = new GameMap();
        Users.getAllUsersFromDB();

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

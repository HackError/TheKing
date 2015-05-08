package ru.devit;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import ru.devit.DB.ConfigDAO;
import ru.devit.DB.MyBatisConnectionFactory;
import ru.devit.JSONparser.Converter;
import ru.devit.JSONparser.Request;
import ru.devit.gameLogic.TimerGameTurn;
import ru.devit.map.GameMap;
import ru.devit.network.WebSocketServer;
import java.util.HashMap;

/**
 * Created by georgys on 25.04.2015.
 */
public class Server {

    private static GameConfig gameConfig = new GameConfig();
    private static GameMap gameMap = new GameMap();
    private static Users users = new Users();

    public static void init() {

        Thread gameLogic = new Thread(new Runnable() {
            @Override
            public void run() {
                TimerGameTurn.start();
            }
        });

        gameLogic.start();

        //открываем сокет
        try {
            WebSocketServer.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendInitToClient(ChannelHandlerContext ctx) throws JsonProcessingException {
        HashMap hm = new HashMap<String, Object>();
        /*отправка карты клиенту*/
        hm.put("map", gameMap.getMap());
        hm.put("mapWidth", GameMap.getMapWidth());
        Request request = new Request("initMain", hm);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(Converter.toJSON(request)));
    }

    public static GameConfig getGameConfig() {
        return gameConfig;
    }

    public static GameMap getGameMap() {
        return gameMap;
    }

    public static Users getUsers() {
        return users;
    }
}

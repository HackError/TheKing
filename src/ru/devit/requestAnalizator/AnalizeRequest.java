package ru.devit.requestAnalizator;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import ru.devit.JSONparser.Converter;
import ru.devit.JSONparser.Request;
import ru.devit.Server;
import ru.devit.Users;
import ru.devit.authorization.Authorization;

import java.util.HashMap;

/**
 * Created by user on 24.04.2015.
 */
public class AnalizeRequest {
    /**
     * Анализ запросов клиента
     * @param request
     * @param ctx
     */
    public static void AnalizeRequest( Request request, ChannelHandlerContext ctx ) throws JsonProcessingException {
        Request ret = null;

        if ( request.getType().equals("init") ) {
            Server.sendInitToClient(ctx);
        }
        if ( request.getType().equals("authorization") ) {
            ret = Authorization.Autorization(request, ctx);
            ctx.channel().write(new TextWebSocketFrame(Converter.toJSON(ret)));
        }
    }
}

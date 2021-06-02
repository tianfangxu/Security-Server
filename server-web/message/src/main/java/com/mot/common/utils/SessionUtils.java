package com.mot.common.utils;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianfx
 * @date 2021/6/1 1:58 下午
 */
public class SessionUtils {

    static Map<String, ChannelHandlerContext> session = new HashMap();

    public static Map<String, ChannelHandlerContext> getSessions(){
        return session;
    }
}

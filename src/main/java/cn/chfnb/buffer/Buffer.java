package cn.chfnb.buffer;

import cn.chfnb.constants.Constant;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 线程共享缓冲区
 */
public class Buffer {
    /**
     * 在线用户数
     */
    public static int onlineCnt = 0;
    public synchronized static void add(){
        ++onlineCnt;
    }
    public static Map<Long, Socket>socketMap = new HashMap<Long, Socket> (Constant.MAX_CLIENT);
}

package cn.chfnb.util;

import cn.chfnb.constants.Constant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {
    /**
     * 通过newFixedThreadPool创建线程池
     * @param maxCntOfThread 最大线程数
     * @return
     */
    public static ExecutorService createThreadPool(int maxCntOfThread){
        return Executors.newFixedThreadPool (maxCntOfThread);
    }
}

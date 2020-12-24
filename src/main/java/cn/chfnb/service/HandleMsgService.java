package cn.chfnb.service;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 消息处理服务
 *
 * @author caihaofeng
 * @date 2020/12/24
 */
public class HandleMsgService implements Runnable {
    private BufferedReader bufferedReader;
    public HandleMsgService(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }
    /**
     * 接受消息
     * @return 消息
     */
    private void receiveMsg(){
        while (true){
            try {
                System.out.println(bufferedReader.readLine ());
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
    @Override
    public void run() {
        receiveMsg ();
    }
}

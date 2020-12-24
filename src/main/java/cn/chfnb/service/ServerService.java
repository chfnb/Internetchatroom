package cn.chfnb.service;

import cn.chfnb.buffer.Buffer;
import cn.chfnb.pojo.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ServerService implements Runnable {
    private Socket socket;
    /**
     * 读
     */
    private BufferedReader bufferedReader;
    /**
     * 写
     */
    private PrintWriter writer;
    private User user;
    public ServerService(User user,Socket socket){
        this.socket = socket;
        try {
            this.bufferedReader = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
            this.writer = new PrintWriter (socket.getOutputStream (),true);
            this.user = user;
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
    /**
     * 广播发送
     * @param msg
     */
    private void send(String msg){
        Map<Long,Socket> socketMap = Buffer.socketMap;
        for (Long key:socketMap.keySet ()) {
            try {
                PrintWriter printWriter = new PrintWriter (socketMap.get (key).getOutputStream (),true);
                printWriter.println (msg);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }

    /**
     * 给用户反馈
     * @param msg
     */
    private void fedback(String msg){
        writer.println (msg);
    }

    /**
     * 监听用户发过来的信息
     * @return
     */
    private String receive(){
        try {
            return bufferedReader.readLine ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return null;
    }

    @Override
    public void run() {
        fedback ("用户"+user.getUserId ()+"上线!");
        while (true){
            String msg = receive ();
            if ( msg !=null ){
                send (user.getUserId ()+"："+msg);
            }

        }

    }

}

package cn.chfnb.service;

import cn.chfnb.buffer.Buffer;
import cn.chfnb.constants.Constant;
import cn.chfnb.pojo.User;
import cn.chfnb.util.ThreadPoolUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * 监听socket连接
 */
public class ServerListener implements Runnable {
    private  ServerSocket serverSocket;
    private ExecutorService es;
    public ServerListener(){
        es = ThreadPoolUtil.createThreadPool (Constant.MAX_CLIENT);
    }
    private void startUpServer(){
        try {
            serverSocket = new ServerSocket (Constant.TCPPort);
            System.out.println("server socket create successfuly!");
            while (true){
                System.out.println("waiting connect...");
                createServiceThread(new  User (System.currentTimeMillis ()),serverSocket.accept ());
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    /**
     * 创建服务线程
     * @param socket 客户端的socket连接
     */
    private void createServiceThread(User user, Socket socket){
        Buffer.add ();
        if ( Buffer.onlineCnt >Constant.MAX_CLIENT){
            fedback (socket);
            return;
        }
        Buffer.socketMap.put (user.getUserId (),socket);
        es.submit (new ServerService (user,socket));
    }

    /**
     * 连接失败给用户的反馈
     * @param socket
     * @return
     */
    private void fedback(Socket socket){
        try {
            PrintWriter writer = new PrintWriter (socket.getOutputStream (),true);
            writer.println ("不好意思，当前用户数已满");
            socket.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    /*private void getClientMessage(){
        try {
            // 当reader无内容时会形成堵塞
            String line = reader.readLine ();
            while (line!=null&&line.length ()>0){
                System.out.println(line);
                line = reader.readLine ();
            }
        }catch (IOException ex){
            ex.printStackTrace ();
        }
        close ();

    }*/
    /*private void close(){
        try {
            if ( reader!=null ){
                reader.close ();
            }
            if ( socket!=null ){
                socket.close ();
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }*/

    @Override
    public void run() {
        startUpServer();
    }
}

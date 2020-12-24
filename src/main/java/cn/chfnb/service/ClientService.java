package cn.chfnb.service;

import cn.chfnb.util.ThreadPoolUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class ClientService implements Runnable{
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader bufferedReader;
    private String url;
    private int port;

    public ClientService(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public void connect(){
        try {
            socket = new Socket (url,port);
            writer = new PrintWriter (socket.getOutputStream (),true);
            bufferedReader = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
            //消息处理线程
            ThreadPoolUtil.createThreadPool (1).execute (new HandleMsgService (bufferedReader));
            Scanner scanner = new Scanner (System.in);
            while (true){
                String msg = scanner.nextLine ();
                sendMessage (msg);
                if ( msg.equals ("bye") ){
                    close ();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }

    /**
     * 发送消息
     * @param message 消息
     */
    private void sendMessage(String message){
        writer.println (message);
    }
    private void close(){
        try {
            if ( bufferedReader!=null ){
                bufferedReader.close ();
            }
            if ( writer!=null){
                writer.close ();
            }
            if ( socket!=null ){
                socket.close ();
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }

    @Override
    public void run() {
        connect ();
    }
}

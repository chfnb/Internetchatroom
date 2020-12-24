package cn.chfnb.util;

import cn.chfnb.enumeration.Port;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPReceiveThread implements Runnable{
    public void receiveMsg()  {
        //1、DatagramSocket(int port)：创建数据报套接字并将其绑定到本地主机上的指定端口。
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket (Port.UDPPORT.getCode ());
            while(true){
                //2、创建一个数据包，用来接收数据
                byte[] bytes = new byte[1024];
                DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
                //3、接收数据
                ds.receive(dp);
                //4、解析数据，显示出来
                InetAddress address = dp.getAddress();
                //获取对方的ip
                String ip = address.getHostAddress();
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println(ip + ":" + s);
            }
            //接收端应该一直开着等着接收数据，不需要关闭
            //ds.close();
        } catch (SocketException e) {
            e.printStackTrace ();
        } catch (IOException e){
            e.printStackTrace ();
        }

    }
    @Override
    public void run() {
        receiveMsg();
    }
}

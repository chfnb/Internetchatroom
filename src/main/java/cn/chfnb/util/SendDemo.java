package cn.chfnb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendDemo {
    public static void main(String[] args) throws IOException {
        //1、创建发送端Socket对象
        DatagramSocket ds = new DatagramSocket();
        //实现键盘输入
        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
        String line = null;
        //通过while实现多次输入发送
        while((line=br.readLine()) != null){
            //当输入"end"即关闭连接
            if("end".equals(line)){
                break;
            }

            //2、创建数据，并把数据打包。将要发送的数据、其长度、远程主机的 IP 地址和远程主机的端口号。
            byte[] bytes = line.getBytes();
            //创建数据

            InetAddress address = InetAddress.getByName("danni");
            //IP地址对象
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, 10086);

            //3、通过Socket对象的发送方法发送数据包
            ds.send(dp);
        }

        //4、释放资源
        ds.close();
    }
}

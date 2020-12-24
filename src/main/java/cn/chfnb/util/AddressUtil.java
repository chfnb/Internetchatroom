package cn.chfnb.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddressUtil {
    public static void main(String[]args){
        try{
            InetAddress inetAddress = InetAddress.getLocalHost ();
            String localName = inetAddress.getHostName ();
            String localIp = inetAddress.getHostAddress ();
            System.out.println("主机名："+localName);
            System.out.println("Ip地址："+localIp);
        }catch (UnknownHostException e){
            e.printStackTrace ();
        }

    }

}

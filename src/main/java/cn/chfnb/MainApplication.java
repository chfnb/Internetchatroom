package cn.chfnb;

import cn.chfnb.constants.Constant;
import cn.chfnb.service.ClientService;
import cn.chfnb.service.ServerListener;
import cn.chfnb.util.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

public class MainApplication {
    public static void main(String[]args){
        ExecutorService es = ThreadPoolUtil.createThreadPool (2);
        //es.submit (new ServerListener ());
        es.submit (new ClientService ("127.0.0.1", Constant.TCPPort));
    }
}

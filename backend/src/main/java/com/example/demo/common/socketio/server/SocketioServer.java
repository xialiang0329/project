package com.example.demo.common.socketio.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.demo.common.code.StatusCodeEnum;
import com.example.demo.common.socketio.pojo.WebSocketioMessage;
import com.example.demo.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SocketioServer {

    private final Logger logger = LoggerFactory.getLogger(SocketioServer.class);

    // 用来存已连接的客户端
    private static ConcurrentHashMap<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * Spring IoC容器创建之后，在加载SocketIOServiceImpl Bean之后启动
     * @throws Exception
     */
    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    /**
     * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     * @throws Exception
     */
    @PreDestroy
    private void autoStop() throws Exception  {
        stop();
    }

    public void start() {
        // 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            User user = getParamsByClient(client);
            if (null == clientMap.get(user.getPkid()) && user != null ) {
                clientMap.put(user.getPkid(), client);
                logger.info("有新客户端连接UID:{},userName:{}", user.getPkid(), user.getName());
                // 给客户端发送一条信息 发送ClientReceive事件 需要客户端绑定此事件即可接收到消息
                /*用户 user.getName() 上线*/
                WebSocketioMessage socketBody = new WebSocketioMessage();
                socketBody.setTo(StatusCodeEnum.socketBodyType.to_broadcast.getKey());//广播
                socketBody.setMessageType(StatusCodeEnum.socketBodyType.message_text.getKey());//文本
                socketBody.setFrom(StatusCodeEnum.socketBodyType.from_system.getKey());
                socketBody.setFromPkid(user.getPkid());
                socketBody.setMessage(user.getName() + "已上线！");
                //广播
                Iterator<Map.Entry<String, SocketIOClient>> iterator = clientMap.entrySet().iterator();
                while (iterator.hasNext())
                {
                    Map.Entry<String, SocketIOClient> next = iterator.next();
                    next.getValue().sendEvent("ClientReceive", socketBody);
                }
            }
        });
        /*// 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            User user = getParamsByClient(client);
            if (user != null) {
                clientMap.remove(user);
                client.disconnect();
            }
            logger.info("一条客户端({})连接中断",user.getName());
        });*/

        // 处理自定义的事件，与连接监听类似
        socketIOServer.addEventListener("ServerReceive",WebSocketioMessage.class, (client, data, ackSender) -> {
            WebSocketioMessage message = data;
            User user = getParamsByClient(client);
            if (user != null) {
                logger.info("接收到uid:{},userName:{}发来的消息:{}",user.getPkid(),user.getName(),message);
                client.sendEvent("ClientReceive",message);
                //todo 信息入库


            }
        });
        socketIOServer.start();
        logger.info("socket.io初始化服务完成");
    }

    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
        logger.info("socket.io服务已关闭");
    }

    /**
     * 此方法为获取client连接中的参数
     * @param client
     * @return
     */
    private User getParamsByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> list = params.get("loginUserInfo");
        if (list != null && list.size() > 0) {
            User user = JSON.parseObject(list.get(0), User.class);
            return user;
        }
        return null;
    }
}

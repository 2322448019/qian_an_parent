package liao.lian.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import entity.Result;
import liao.lian.chat.client.MesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;

@Service
public class MessageEventhandler {

    @Autowired
    private SocketIOServer server;

    @Autowired
    private MesClient mesClient;


    private Result result;

    @OnConnect
    public void connection(SocketIOClient client) {
        System.out.println("上线了");
    }

    @OnDisconnect
    public void disconnect(SocketIOClient client) {
        System.out.println("断开连接");
    }

    @OnEvent(value = "customEmit")
    public void customEmit(SocketIOClient client) {
        System.out.println("连接失败");
    }

    @OnEvent(value = "join")
    public void join(SocketIOClient client, Map map) {
        client.sendEvent("joined", map);
    }

    @OnEvent(value = "leave")
    public void leave(SocketIOClient client, Map map) {
        client.sendEvent("leaved", map);
    }

    @OnEvent(value = "mes")
    public void mes(SocketIOClient client, Map map) {
        Result result = mesClient.saveMessage(map);
        List<Map> mapList = (List<Map>) result.getData();
        for (Map resultMap : mapList) {
            UUID target = UUID.randomUUID();
            server.getClient(target).sendEvent("mes",resultMap);
        }
    }

    @OnEvent(value = "getHistoryMessages")
    public void getHistoryMessages(SocketIOClient client, Map map) {
        System.out.println("获取历史消息");
        Result result = mesClient.loadMoreMessages(map);
        System.out.println(result.getData());
        client.sendEvent("getHistoryMessages", result.getData());
    }

    @OnEvent(value = "agreeValidate")
    public void agreeValidate(SocketIOClient client, Map map) {
        if (map.get("state").equals("friend")) {
            Result result = mesClient.addFriend(map);
            this.result = result;
        } else if (map.get("state").equals("group")) {
            Result result = mesClient.addGroupUsers(map);
            this.result = result;
        }
        client.sendEvent("takeValidate", result.getData());
        client.sendEvent("ValidateSuccess", "ok");
    }

    @OnEvent(value = "refuseValidate")
    public void refuseValidate(SocketIOClient client, Map map) {
        mesClient.setMessageStatus(map);
    }

    @OnEvent(value = "setReadStatus")
    public void setReadStatus(SocketIOClient client, Map map) {
        mesClient.setReadStatus(map);
    }

    @OnEvent(value = "sendValidate")
    public void sendValidate(SocketIOClient client, Map map) {
        Result result = mesClient.saveMessage(map);
    }

    @OnEvent(value = "getSystemMessages")
    public void getSystemMessages(SocketIOClient client, Map map) {
        client.sendEvent("getSystemMessages", mesClient.getSystemMessages(map));

    }

    @OnEvent(value = "takeValidate")
    public void takeValidate(SocketIOClient client) {
        client.sendEvent("takeValidate", result.getData());
    }

    // spring Ioc 容器销毁前停止服务
    @PreDestroy
    public void stop() {
        if (server != null) {
            server.stop();
        }
    }
}

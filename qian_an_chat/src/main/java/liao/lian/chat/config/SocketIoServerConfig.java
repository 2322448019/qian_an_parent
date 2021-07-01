package liao.lian.chat.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIoServerConfig {

    @Value("${socketio.port}")
    private int port;
    @Value("${socketio.upgradeTimeout}")
    private int upgradeTimeout;
    @Value("${socketio.pingInterval}")
    private int pingInterval;
    @Value("${socketio.pingTimeout}")
    private int pingTimeout;

    /**
     *  注册一个SocketIoServer bean，
     *  初始化好一切参数，在注入的时候可以不用再初始化
     * @return
     */
    @Bean
    public SocketIOServer socketIoServer(){
        /*
         * 创建Socket，并设置监听端口
         */
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        // 设置主机名，默认是0.0.0.0
        //  config.setHostname("localhost");
        // 设置监听端口
        config.setPort(port);
        // 协议升级超时时间（毫秒），默认10000。HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(upgradeTimeout);
        // Ping消息间隔（毫秒），默认25000。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(pingInterval);
        // Ping消息超时时间（毫秒），默认60000，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(pingTimeout);
        // sessionId是否随机
        config.setRandomSession(true);

        final SocketIOServer server = new SocketIOServer(config);

        return server;
    }

    /**
     * 注入OnConnect，OnDisconnect，OnEvent注解。 不写的话Spring无法扫描OnConnect，OnDisconnect等注解
     * @param socketServer
     * @return
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

}

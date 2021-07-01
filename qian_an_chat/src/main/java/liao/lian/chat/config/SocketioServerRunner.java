package liao.lian.chat.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SocketioServerRunner implements CommandLineRunner {

    private final SocketIOServer server;

    public SocketioServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception {
        // spring服务启动后会紧接着启动run方法，即会吊起来socket服务。
        System.out.println("ServerRunner 开始启动啦...");
        server.start();
    }
}

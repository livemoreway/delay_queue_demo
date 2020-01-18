package conn;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConUtils {
    private static final String HOST = "39.106.79.245";
    private static final int PORT = 5672;
    private static final String USER_NAME = "guest";
    private static final String PASS_WORD = "guest";
    private static final String VIRTUAL_HOST = "shark.product";

    public static final Connection getConn() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(PASS_WORD);
        connectionFactory.setVirtualHost(VIRTUAL_HOST);
        return connectionFactory.newConnection();
    }
}

package pre;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import conn.ConUtils;

import java.util.HashMap;
import java.util.Map;

public class Prepare {
    public final static String exchange0= "dead_Exchange";
    public final static String exchange1 = "exchange.1";

    public final static String dead_routing_key1 = "dead_letter_1";
    public final static String dead_routing_key2 = "dead_letter_2";
    public final static String dead_routing_key3 = "dead_letter_3";

    public final static String queue01 = "old_Queue01";
    public final static String queue02 = "old_Queue02";
    public final static String queue03 = "old_Queue03";
    public final static String queue1 = "queue.1";
    public final static String queue2 = "queue.2";
    public final static String queue3 = "queue.3";

    public final static String routing_key1="routingKey1";
    public final static String routing_key2="routingKey2";
    public final static String routing_key3="routingKey3";

    public static void main(String[] args) throws Exception{
        Connection conn = ConUtils.getConn();
        Channel channel = conn.createChannel();

        //声明exchange
        channel.exchangeDeclare(exchange0,"direct",true);
        channel.exchangeDeclare(exchange1,"direct",true);
//        channel.exchangeDeclare(exchange2,"direct",true);
//        channel.exchangeDeclare(exchange3,"direct",true);
        //声明queue

        Map<String,Object> properties = new HashMap<>(8);
        properties.put("x-message-ttl",1000);
        properties.put("x-dead-letter-exchange",exchange0);
        properties.put("x-dead-letter-routing-key",dead_routing_key1);
        channel.queueDeclare(queue01,true,false,false,properties);
        properties.put("x-message-ttl",2000);
        properties.put("x-dead-letter-routing-key",dead_routing_key2);
        channel.queueDeclare(queue02,true,false,false,properties);
        properties.put("x-message-ttl",3000);
        properties.put("x-dead-letter-routing-key",dead_routing_key3);
        channel.queueDeclare(queue03,true,false,false,properties);

        //死信队列
        channel.queueDeclare(queue1,true,false,false,null);
        channel.queueDeclare(queue2,true,false,false,null);
        channel.queueDeclare(queue3,true,false,false,null);

        channel.queueBind(queue1,exchange0,dead_routing_key1);
        channel.queueBind(queue2,exchange0,dead_routing_key2);
        channel.queueBind(queue3,exchange0,dead_routing_key3);


        channel.queueBind(queue01,exchange1,routing_key1);
        channel.queueBind(queue02,exchange1,routing_key2);
        channel.queueBind(queue03,exchange1,routing_key3);

        channel.close();
        conn.close();
    }
}

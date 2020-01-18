package prod;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;
import conn.ConUtils;
import pre.Prepare;

import java.io.IOException;

public class Producer {

    public static void main(String[] args) throws Exception{
        Connection conn = ConUtils.getConn();
        Channel channel = conn.createChannel();
        byte[] bytes = "1秒过期的消息".getBytes();
        channel.basicPublish(Prepare.exchange1,Prepare.routing_key1,true,null,bytes);
        byte[] bytes2 = "2秒过期的消息".getBytes();
        channel.basicPublish(Prepare.exchange1,Prepare.routing_key2,true,null,bytes2);
        byte[] bytes3 = "3秒过期的消息".getBytes();
        channel.basicPublish(Prepare.exchange1,Prepare.routing_key3,true,null,bytes3);


        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(exchange+"-"+routingKey+":"+body);
            }
        });
        channel.close();
        conn.close();
    }
}

package con;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import conn.ConUtils;
import pre.Prepare;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws Exception{
        Connection conn = ConUtils.getConn();
        Channel channel = conn.createChannel();
        channel.basicConsume(Prepare.queue1,true, new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                byte[] body = message.getBody();
                System.out.println(Prepare.queue1+" 处理消息："+new String(body));
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });

        channel.basicConsume(Prepare.queue2,true, new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                byte[] body = message.getBody();
                System.out.println(Prepare.queue2+" 处理消息："+new String(body));
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });

        channel.basicConsume(Prepare.queue3,true, new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                byte[] body = message.getBody();
                System.out.println(Prepare.queue3+" 处理消息："+new String(body));
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });
    }
}

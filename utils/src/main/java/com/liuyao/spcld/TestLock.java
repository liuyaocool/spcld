package com.liuyao.spcld;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestLock {

    public static void main(String[] args) throws InterruptedException {
        HttpClientByNetty client = new HttpClientByNetty("localhost", 8101);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                JSONObject data = new JSONObject();
                data.put("id", finalI);
                client.jsonPost("/user/postMap", data);
                System.out.println(finalI + " → 结果：\n" + new String(client.getResult()));
            }, "thread-" + finalI).start();
        }
    }

}

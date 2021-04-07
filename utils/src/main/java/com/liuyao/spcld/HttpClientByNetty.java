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

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class HttpClientByNetty {

    public static void main(String[] args) throws InterruptedException {
        HttpClientByNetty client = new HttpClientByNetty("localhost", 8101);

        JSONObject data = new JSONObject();
        client.consurrentJsonPost(10, "/user/postMap", data, (tname, res) -> {
            System.out.println(tname + " → 结果：\n" + new String(client.getResult()));
        });
    }

    private static final NioEventLoopGroup GROUP = new NioEventLoopGroup(1);
    private static final Bootstrap BS = new Bootstrap();

    private CompletableFuture<byte[]> res = new CompletableFuture<byte[]>();
    private Channel clientChannel;
    private String ip;
    private int port;

    public HttpClientByNetty(String ip, int port) throws InterruptedException {
        this.ip = ip;
        this.port = port;

        Bootstrap client = BS.group(GROUP)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new HttpClientCodec())
                                .addLast(new HttpObjectAggregator(1024 * 512))
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        //3，接收   预埋的回调，根据netty对socket io 事件的响应
                                        //客户端的msg是啥：完整的http-response
                                        FullHttpResponse response = (FullHttpResponse) msg;
                                        System.out.println(response.toString());

                                        ByteBuf resContent = response.content();
                                        byte[] data =  new byte[resContent.readableBytes()];
                                        resContent.readBytes(data);

                                        res.complete(data);
                                    }
                                });
                    }
                });
        // 连接
        ChannelFuture syncFuture = client.connect(this.ip, this.port).sync();
        this.clientChannel = syncFuture.channel();
    }

    public void jsonPost(String uri, JSON content) {
        request(HttpMethod.POST, uri, content.toJSONString(),
                new HeaderEntry(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
//                ,new HeaderEntry(HttpHeaderNames.ACCEPT_ENCODING, "uft-8")
//                ,new HeaderEntry(HttpHeaderNames.ACCEPT_CHARSET, "uft-8")
        );
    }

    public void request(HttpMethod method, String uri, Object content, HeaderEntry... headers) {

        try {
            // 发送
            byte[] data = SerDerUtil.ser(content);
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0,
                    method, uri,
                    Unpooled.copiedBuffer(data)
            );

            if (null != headers) {
                for (HeaderEntry header : headers) {
                    request.headers().set(header.getKey(), header.getValue());
                }
            }
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, data.length);
            clientChannel.writeAndFlush(request).sync();//作为client 向server端发送：http  request
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public byte[] getResult(){
        try {
            return this.res.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void consurrentJsonPost(int num, String uri, JSONObject data, BiConsumer<String, byte[]> call) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                jsonPost(uri, data);
                call.accept("thread-" + finalI, getResult());
            }, "thread-" + finalI).start();
        }
    }

    interface NettyEntry<K, V>{
        K getKey();
        void setKey(K k);
        V getValue();
        void setValue(V v);
    }

    public static class HeaderEntry implements NettyEntry<AsciiString, Object> {

        private AsciiString key;
        private Object value;

        public HeaderEntry(AsciiString key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AsciiString getKey() {
            return key;
        }

        public void setKey(AsciiString key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

}

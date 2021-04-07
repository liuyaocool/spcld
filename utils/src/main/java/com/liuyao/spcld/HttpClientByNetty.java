package com.liuyao.spcld;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class HttpClientByNetty {

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


    private static final Cache<Channel, CompletableFuture<byte[]>> RESULTS = CacheBuilder.newBuilder()
//                .maximumSize(2)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<Channel, CompletableFuture<byte[]>>() {
                @Override
                public void onRemoval(RemovalNotification<Channel, CompletableFuture<byte[]>> notification) {
                    notification.getKey().close();
                }
            })
            .build();

    private static final NioEventLoopGroup GROUP = new NioEventLoopGroup(4);
    private static final Bootstrap BS = new Bootstrap();
    static Bootstrap client = BS.group(GROUP)
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
//                                    System.out.println(response.toString());

                                    ByteBuf resContent = response.content();
                                    byte[] data =  new byte[resContent.readableBytes()];
                                    resContent.readBytes(data);

                                    Channel ch = ctx.channel();
                                    CompletableFuture<byte[]> res = RESULTS.getIfPresent(ch);
                                    if (null != res) {
                                        res.complete(data);
                                    }
                                }
                            });
                }
            });

    private ThreadLocal<Channel> tl = new ThreadLocal();
    private String ip;
    private int port;

    public HttpClientByNetty(String ip, int port) {
        this.ip = ip;
        this.port = port;
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
            // 连接
            ChannelFuture syncFuture = client.connect(this.ip, this.port).sync();
            Channel ch = syncFuture.channel();
            tl.set(ch);
            RESULTS.put(ch, new CompletableFuture<>());
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
            ch.writeAndFlush(request).sync();//作为client 向server端发送：http  request
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public byte[] getResult(){
        try {
            final Channel ch = tl.get();
            byte[] res = RESULTS.getIfPresent(ch).get();
            RESULTS.invalidate(ch);
            ch.close();
            return res;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
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

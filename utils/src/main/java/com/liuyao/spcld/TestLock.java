package com.liuyao.spcld;

import com.alibaba.fastjson.JSONObject;
import com.liuyao.spcld.netty.HttpClient;

public class TestLock {

    public static void main(String[] args) throws InterruptedException {
        HttpClient client = new HttpClient("localhost", 8101);

        String uri = "/user/postMap";

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                JSONObject data = new JSONObject();
                data.put("id", finalI);
                client.jsonPost(uri, data);
                final byte[] result = client.getResult();
                System.out.println(finalI + " → 结果：\n" + new String(result));
            }, "thread-" + finalI).start();
        }
    }

}

package com.liuyao.spcld.gatewayzull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 容错 降级
 */
@Component
public class MyFallback implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*";
    }

    // 访问 http://localhost:7100/zuul-area-alive 出错
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            private final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return badRequest;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return badRequest.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return "bad request";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                // 通用返回值 结构 {code: 200, msg: "aaa"}
                Map map = new HashMap();
                map.put("code", 302);
                map.put("msg", "bad req");
                byte[] res = JSON.toJSONString(map).getBytes();
                return new ByteArrayInputStream(res);
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}

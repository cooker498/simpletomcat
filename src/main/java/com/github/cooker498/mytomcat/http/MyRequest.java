package com.github.cooker498.mytomcat.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author daijw@inspur.com
 * @version 1.0.0
 * @ClassName MyRequest.java
 * @Description 通过输入流，对HTTP协议进行解析，拿到了HTTP请求头的方法以及URL
 * @createTime 2020年01月14日 18:28:00
 */
public class MyRequest {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private String url;
    private String method;

    public MyRequest(InputStream inputStream) throws IOException {

        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 4;
        if((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }
//          HTTP请求协议
//          GET /web/user/query.json?name=zhangsan HTTP/1.1
//          Accept: */*
//          Accept-Encoding: gzip, deflate
//          User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134
//          Host: localhost:18888
//          Connection: Keep-Alive
        String httpHead = httpRequest.split("\n")[0];
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];
        System.out.println(this);

    }
}

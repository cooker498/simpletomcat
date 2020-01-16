package com.github.cooker498.mytomcat;

import com.github.cooker498.mytomcat.http.MyRequest;
import com.github.cooker498.mytomcat.http.MyResponse;
import com.github.cooker498.mytomcat.http.MyServlet;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author daijw@inspur.com
 * @version 1.0.0
 * @ClassName MyTomcat.java
 * @Description TODO
 * @createTime 2020年01月14日 18:54:00
 */
public class MyTomcat {

    private int port = 8080;

    private Map<String,String> urlServletMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(),servletMapping.getClazz());
        }
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String clazz = urlServletMap.get(myRequest.getUrl());

        //反射
        try {
            if (clazz == null) {
                myResponse.write("404");
                return;
            }
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);

            MyServlet myServlet = myServletClass.newInstance();
            myServlet.service(myRequest, myResponse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        //初始化URL与对应处理的servlet的关系
        initServletMapping();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("MyTomcat is start...");

            while(true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);

                //请求分发
                dispatch(myRequest, myResponse);

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }


}

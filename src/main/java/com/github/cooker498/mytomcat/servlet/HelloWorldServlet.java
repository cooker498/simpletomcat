package com.github.cooker498.mytomcat.servlet;

import com.github.cooker498.mytomcat.http.MyRequest;
import com.github.cooker498.mytomcat.http.MyResponse;
import com.github.cooker498.mytomcat.http.MyServlet;

import java.io.IOException;

/**
 * @author daijw@inspur.com
 * @version 1.0.0
 * @ClassName HelloWorldServlet.java
 * @Description TODO
 * @createTime 2020年01月14日 19:13:00
 */
public class HelloWorldServlet extends MyServlet {

    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get world...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post world...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

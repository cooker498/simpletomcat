package com.github.cooker498.mytomcat.http;

import com.github.cooker498.mytomcat.http.MyRequest;
import com.github.cooker498.mytomcat.http.MyResponse;

/**
 * @author daijw@inspur.com
 * @version 1.0.0
 * @ClassName MyServlet.java
 * @Description Tomcat需要提供API, 这里提供了Servlet常见的doGet/doPost/service
 * @createTime 2020年01月14日 18:45:00
 */
public abstract  class MyServlet {

    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);

    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);

    public void service(MyRequest myRequest, MyResponse myResponse) {

        if (myRequest.getMethod().equalsIgnoreCase("POST")) {
            doPost(myRequest, myResponse);
        } else if (myRequest.getMethod().equalsIgnoreCase("GET")) {
            doGet(myRequest, myResponse);
        }
    }

}

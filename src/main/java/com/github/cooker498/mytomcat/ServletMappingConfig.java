package com.github.cooker498.mytomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daijw@inspur.com
 * @version 1.0.0
 * @ClassName ServletMappingConfig.java
 * @Description TODO
 * @createTime 2020年01月14日 18:52:00
 */
public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("findGirl", "/girl", "com.github.cooker498.mytomcat.servlet.FindGirlWorldServlet"));
        servletMappingList.add(new ServletMapping("helloWorld", "/world", "com.github.cooker498.mytomcat.servlet.HelloWorldServlet"));

    }


}

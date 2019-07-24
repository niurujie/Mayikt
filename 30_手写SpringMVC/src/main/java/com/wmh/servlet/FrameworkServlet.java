package com.wmh.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: create by wangmh
 * @projectName: Mayikt
 * @packageName: com.wmh.servlet
 * @description:
 * @date: 2019/7/24
 **/
public class FrameworkServlet extends  HttpServletBean{
    @Override
    protected void initServletBean() {
        onRefresh();
    }

    protected void onRefresh() {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        doService( req,  resp);
    }

    protected void doService(HttpServletRequest req, HttpServletResponse resp) {

    }
}

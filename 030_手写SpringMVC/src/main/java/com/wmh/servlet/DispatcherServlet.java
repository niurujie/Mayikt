package com.wmh.servlet;

import com.wmh.handler.HandlerExecutionChain;
import com.wmh.handler.HandlerMethod;
import com.wmh.handler.RequestMappingInfoHandlerMapping;
import com.wmh.view.ModelAndView;

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
public class DispatcherServlet extends FrameworkServlet {
    RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

    public DispatcherServlet() {
        requestMappingInfoHandlerMapping = new RequestMappingInfoHandlerMapping();
    }

    @Override
    protected void onRefresh() {
        initStrategies();
    }

    private void initStrategies() {
        initHandlerMappings();
    }

    private void initHandlerMappings() {
        System.out.println("<<<<初始化initHandlerMappings>>>>");
        requestMappingInfoHandlerMapping.registryMapping();
    }

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) {
        //1.获取Url请求
        String requestURI = req.getRequestURI();
        //2.根据url请求获取具体的handler
        HandlerExecutionChain handler = getHandler(requestURI);
        try {
            if (handler == null) {
                noHandlerFound(req, resp);
                return;
            }
            //3.执行handler方法
            ModelAndView mv = handler.handler();
            //4.渲染页面
            render(mv, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 根据modelAndView渲染页面
     * @param mv
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void render(ModelAndView mv, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewName = mv.getViewName();
        req.getRequestDispatcher("/WEB-INF/view/" + viewName + ".jsp").forward(req, resp);
    }


    private void noHandlerFound(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        resp.getWriter().print("没有查找到该请求");
    }


    /***
     * 根据url在HandlerMapping中获取具体的类，然后获取集具体点的Handler
     * @param url
     * @return
     */
    public HandlerExecutionChain getHandler(String url) {
        System.out.println(url);
        HandlerMethod handler = requestMappingInfoHandlerMapping.getHandler(url);
        if (handler == null){
            return null;
        }

        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain(handler);
        return handlerExecutionChain;
    }
}

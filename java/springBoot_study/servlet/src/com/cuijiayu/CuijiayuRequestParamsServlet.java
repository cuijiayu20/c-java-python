package com.cuijiayu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CuijiayuRequestParamsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
//        resp.setContentType("test/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        String name = req.getParameter("username");
//        name = new String(name.getBytes("iso8859-1"),"utf-8");
        String password = req.getParameter("password");
        String[] hobbys = req.getParameterValues("hobby");
        PrintWriter outs = resp.getWriter();

        System.out.println("用户："+name);
        System.out.println("密码："+password);
        outs.println("用户："+name);
        outs.println("密码："+password);
        System.out.println("爱好：");
        for (int i=0;i<hobbys.length;i++){
            System.out.println(hobbys[i]+",");
            outs.println(hobbys[i]+",");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

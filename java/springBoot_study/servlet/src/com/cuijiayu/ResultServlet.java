package com.cuijiayu;

import org.apache.tomcat.util.buf.UEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultServlet extends HttpServlet {
    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=utf-8");
//        resp.setCharacterEncoding("utf-8");



        String com = (String) req.getAttribute("company");

        PrintWriter out=resp.getWriter();
        if (com != null){
            out.println("作业："+com+"<br>");
        }



    }
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        doPost(req,resp);
    }

}

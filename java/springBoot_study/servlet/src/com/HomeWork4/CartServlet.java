package com.HomeWork4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        List<Clothe> cart = null;
        boolean purFlag = true;
        HttpSession session = req.getSession(false);
        if(session == null){
            purFlag=false;
        }else{
            cart=(List) session.getAttribute("cart");
            if (cart == null){
                purFlag=false;
            }
        }
        if(!purFlag){
            out.write("对不起！您未购买商品！<br />");

        }else{
            out.write("您购买的图书有：<br />");
            double price=0;
            for (Clothe clothe:cart){
                out.write(clothe.getName()+"<br />");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

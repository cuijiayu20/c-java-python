package com.HomeWork4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class ListClothesServlet extends HttpServlet {
    private static final long SerialVersionUID=1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Collection<Clothe> clothes=ClotheDB.getAll();
        out.write("本站的衣服有：<br />");
        for(Clothe clothe:clothes){
            String url="/servletText_war_exploded/PurchaseServlet?id="+clothe.getId();
            out.write(clothe.getName()+"<a href='"+url+"'>点击购买</a><br />");//小引号什么意思？
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

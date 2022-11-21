package com.HomeWork4;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseServlet extends HttpServlet {
    private static final long SerialVersionUID=1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id==null){
            String url="/servletText_war_exploded/ListClothesServlet";
            resp.sendRedirect(url);
            return;
        }
        Clothe clothe = ClotheDB.getClothe(id);
        HttpSession session=req.getSession();
        List<Clothe> cart=(List) session.getAttribute("cart");
        if (cart==null){
            cart=new ArrayList<Clothe>();
            session.setAttribute("cart",cart);
        }
        cart.add(clothe);
        Cookie cookie=new Cookie("JSESSIONID",session.getId());
        cookie.setMaxAge(60*30);
        cookie.setPath("/servletText_war_exploded");
        resp.addCookie(cookie);
        String url="/servletText_war_exploded/CartServlet";
        resp.sendRedirect(url);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

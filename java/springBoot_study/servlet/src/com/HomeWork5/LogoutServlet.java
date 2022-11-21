package com.HomeWork5;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
public class LogoutServlet extends HttpServlet {
public void doGet(HttpServletRequest request, 
                      HttpServletResponse response)
		throws ServletException, IOException {
     // 将Session对象中的User对象移除
		request.getSession().removeAttribute("user");
		response.sendRedirect("/servletText_war_exploded/IndexServlet");
	}
	public void doPost(HttpServletRequest request, 
       HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}
}

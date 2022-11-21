package com.cuijiayu;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CuijiayuTestServlet extends GenericServlet {


    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("I am cuijiayu and my id is 2020020203");
        PrintWriter printWriter=response.getWriter();
        printWriter.write("cuijiayu is in class network 1,software school,LNTU!");
    }
}

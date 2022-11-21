import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletDemo1 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("崔家瑜 2020020203");
    }
    @Override
    protected void doPost (HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        doGet(req, resp);
    }
}
package postgraduatems.web.servlets.postgraduate;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dotoan on 5/18/14.
 */
public class Frame extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String pid = httpServletRequest.getParameter("pid");
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/frame.jsp");
        dispatcher.include(httpServletRequest, httpServletResponse);
    }


}

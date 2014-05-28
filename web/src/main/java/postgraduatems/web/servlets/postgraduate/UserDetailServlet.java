package postgraduatems.web.servlets.postgraduate;

import postgraduatems.persistence.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dotoan on 5/18/14.
 */
public class UserDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        httpServletRequest.setAttribute("user", user);

        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/user_detail.jsp");

        dispatcher.forward(httpServletRequest, httpServletResponse);

    }
}

package postgraduatems.web.servlets.postgraduate;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.User;
import postgraduatems.postgraduate.services.PostgraduateBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by dotoan on 5/18/14.
 */
public class UserPostgraduateDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Postgraduate postgraduate = null;
        try {
            InitialContext ctx = new InitialContext();
            String postgraduateBOJNDI = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(postgraduateBOJNDI);
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            int userId = user.getId();
            postgraduate = postgraduateBO.findPostgraduateByUserId(userId);
            httpServletRequest.setAttribute("postgraduate", postgraduate);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/postgraduate_detail.jsp");

            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

package postgraduatems.web.servlets.postgraduate;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.User;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.seminar.services.SeminarBO;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by dotoan on 5/2/14.
 */
public class SeminarListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + SeminarBO.class.getName();
            SeminarBO seminarBO = (SeminarBO) ctx.lookup(jndi);
            List<Seminar> seminars = seminarBO.findAllSeminars();
            String jndi1 = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndi1);
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            Postgraduate postgraduate = postgraduateBO.findPostgraduateByUserId(user.getId());
            httpServletRequest.setAttribute("postgraduate", postgraduate);
            httpServletRequest.setAttribute("seminars", seminars);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/newest_seminar.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

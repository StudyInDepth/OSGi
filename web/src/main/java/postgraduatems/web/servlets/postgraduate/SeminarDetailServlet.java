package postgraduatems.web.servlets.postgraduate;

import postgraduatems.seminar.services.SeminarBO;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.User;
import postgraduatems.postgraduate.services.PostgraduateBO;

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
public class SeminarDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String sidParam = httpServletRequest.getParameter("sid");
        int sid = Integer.parseInt(sidParam);
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + SeminarBO.class.getName();
            SeminarBO seminarBO = (SeminarBO) ctx.lookup(jndi);
            Seminar seminar = seminarBO.findSeminarById(sid);

            String jndi1 = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndi1);
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            Postgraduate postgraduate = postgraduateBO.findPostgraduateByUserId(user.getId());

            httpServletRequest.setAttribute("postgraduate", postgraduate);
            httpServletRequest.setAttribute("seminar", seminar);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/seminar_detail.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

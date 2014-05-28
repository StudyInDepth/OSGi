package postgraduatems.web.servlets.postgraduate;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.SeminarSubCommittee;
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

/**
 * Created by dotoan on 5/21/14.
 */
public class SeminarSubCommitteeDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + SeminarBO.class.getName();
            SeminarBO seminarBO = (SeminarBO) ctx.lookup(jndi);

            String jndi1 = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndi1);
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            Postgraduate postgraduate = postgraduateBO.findPostgraduateByUserId(user.getId());

            SeminarSubCommittee committee = postgraduate.getSeminarSubCommittee();


            httpServletRequest.setAttribute("committee", committee);


            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher
                    ("/view/postgraduate/seminar_subcommittee_detail.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

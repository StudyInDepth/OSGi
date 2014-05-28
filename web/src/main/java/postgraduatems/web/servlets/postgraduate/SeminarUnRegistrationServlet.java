package postgraduatems.web.servlets.postgraduate;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Seminar;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.postgraduate.services.SeminarRegistrationBO;
import postgraduatems.seminar.services.SeminarBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dotoan on 5/3/14.
 */
public class SeminarUnRegistrationServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String pidParam = httpServletRequest.getParameter("pid");
        String sidParam = httpServletRequest.getParameter("sid");
        int pid = Integer.parseInt(pidParam);
        int sid = Integer.parseInt(sidParam);

        try {
            InitialContext context = new InitialContext();
            String jndiSeminarRegistrationBO = "osgi:service/" + SeminarRegistrationBO.class.getName();
            SeminarRegistrationBO seminarRegistrationBO = (SeminarRegistrationBO) context.lookup(jndiSeminarRegistrationBO);
            seminarRegistrationBO.unregister(pid, sid);

            String jndiPostgraduateBO  = "osgi:service/" + PostgraduateBO.class.getName();
            String jndiSeminarBO  = "osgi:service/" + SeminarBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) context.lookup(jndiPostgraduateBO);
            SeminarBO seminarBO = (SeminarBO) context.lookup(jndiSeminarBO);
            Postgraduate postgraduate = postgraduateBO.findPostgraduateById(pid);
            Seminar seminar = seminarBO.findSeminarById(sid);
            httpServletRequest.setAttribute("postgraduate", postgraduate);
            httpServletRequest.setAttribute("seminar", seminar);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/seminar_detail.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        }  catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

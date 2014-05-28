package postgraduatems.web.servlets.dept;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.thesis.services.ThesisBO;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dotoan on 4/29/14.
 */
public class ThesisDescriptionVerificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String pidParam = httpServletRequest.getParameter("pid");
        String tidParam = httpServletRequest.getParameter("tid");
        int pid = Integer.parseInt(pidParam);
        int tid = Integer.parseInt(tidParam);
        Postgraduate postgraduate = null;
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndi);

            String jndi1 = "osgi:service/" + ThesisBO.class.getName();
            ThesisBO thesisBO = (ThesisBO) ctx.lookup(jndi1);
          //  thesisBO.verifyDescription(tid);
            postgraduate = postgraduateBO.findPostgraduateById(pid);
            httpServletRequest.setAttribute("postgraduate", postgraduate);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/dept/thesis_verified.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

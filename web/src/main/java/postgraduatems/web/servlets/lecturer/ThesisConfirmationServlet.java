package postgraduatems.web.servlets.lecturer;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.User;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.thesis.services.ThesisBO;

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
 * Created by dotoan on 4/27/14.
 */
public class ThesisConfirmationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String pidParam = httpServletRequest.getParameter("pid");
        String tidParam = httpServletRequest.getParameter("tid");
        int pid = Integer.parseInt(pidParam);
        int  tid = -1;
        if (tidParam != null) {
            tid = Integer.parseInt(tidParam);
        }
        Postgraduate postgraduate = null;
        try {
            InitialContext ctx = new InitialContext();
            String jndiPostgraduateBO = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndiPostgraduateBO);
            String jndiThesisBO = "osgi:service/" + ThesisBO.class.getName();
            ThesisBO thesisBO = (ThesisBO) ctx.lookup(jndiThesisBO);
            if (tid != -1) {
                thesisBO.confirm(tid);
            }
            postgraduate = postgraduateBO.findPostgraduateById(pid);
            System.out.println("pid " + pid);
            System.out.println(postgraduate);
            httpServletRequest.setAttribute("postgraduate", postgraduate);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/lecturer/confirm_thesis.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
       doGet(httpServletRequest, httpServletResponse);
    }
}

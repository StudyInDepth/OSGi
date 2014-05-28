package postgraduatems.web.servlets.faculty;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Thesis;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.thesis.services.ThesisBO;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dotoan on 5/22/14.
 */
public class ThesisDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            String pidParam = httpServletRequest.getParameter("pid");
            int pid = Integer.parseInt(pidParam);
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndi);


            String jndi1 = "osgi:service/" + LecturerBO.class.getName();

            LecturerBO lecturerBO = (LecturerBO) ctx.lookup(jndi1);
            List<Lecturer> lecturers = lecturerBO.getLecturerList();
            Postgraduate postgraduate = postgraduateBO.findPostgraduateById(pid);
            httpServletRequest.setAttribute("postgraduate", postgraduate);
            httpServletRequest.setAttribute("lecturers", lecturers);

            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/thesis_detail.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}

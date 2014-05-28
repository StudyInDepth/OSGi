package postgraduatems.web.servlets.lecturer;

import postgraduatems.lecturer.services.SeminarSubCommitteeLecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.SeminarSubCommittee;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dotoan on 5/22/14.
 */
public class SeminarSubCommitteeDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + SeminarSubCommitteeLecturerBO.class.getName();
            SeminarSubCommitteeLecturerBO bo = (SeminarSubCommitteeLecturerBO) ctx.lookup(jndi);
            HttpSession session = httpServletRequest.getSession();

            Lecturer lecturer =  (Lecturer) session.getAttribute("lecturer");

            SeminarSubCommittee committee = bo.findLecturerSeminarSubCommittee(lecturer.getId());

            if (committee != null) {
                if (committee.getPresident().getId() == lecturer.getId()) {
                httpServletRequest.setAttribute("president", true);
                }


                if (committee.getVicePresident().getId() == lecturer.getId()) {
                    httpServletRequest.setAttribute("vice_president", true);
                }


                if (committee.getSecretary().getId() == lecturer.getId()) {
                    httpServletRequest.setAttribute("secretary", true);
                }
            }

            System.out.println(committee + " committee");

            httpServletRequest.setAttribute("committee", committee);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher
                    ("/view/lecturer/lecturer_seminar_subcommittee_detail.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);

        } catch (Exception e) {
            //PrintWriter out = httpServletResponse.getWriter();
            //  out.write("false");
             e.printStackTrace();
        }
    }
}

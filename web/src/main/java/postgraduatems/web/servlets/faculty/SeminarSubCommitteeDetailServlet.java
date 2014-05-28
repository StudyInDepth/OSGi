package postgraduatems.web.servlets.faculty;

import postgraduatems.persistence.entities.SeminarSubCommittee;
import postgraduatems.seminar.services.SeminarSubCommitteeBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dotoan on 5/22/14.
 */
public class SeminarSubCommitteeDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String ssidParam = httpServletRequest.getParameter("ssid");
        int ssid = Integer.parseInt(ssidParam);
        try {
            InitialContext context = new InitialContext();
            String jndiSeminarSubCommitteeBO = "osgi:service/" + SeminarSubCommitteeBO.class.getName();
            SeminarSubCommitteeBO seminarSubCommitteeBO = (SeminarSubCommitteeBO) context.lookup(jndiSeminarSubCommitteeBO);
            SeminarSubCommittee committee = seminarSubCommitteeBO.findSeminarSubCommitteeById(ssid);
            httpServletRequest.setAttribute("committee", committee);

            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher
                    ("/view/faculty/seminar_subcommittee_detail.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

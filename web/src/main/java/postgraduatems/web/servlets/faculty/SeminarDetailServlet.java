package postgraduatems.web.servlets.faculty;

import postgraduatems.persistence.entities.Seminar;
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
 * Created by dotoan on 5/22/14.
 */
public class SeminarDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String sidParam = httpServletRequest.getParameter("sid");
        int sid = Integer.parseInt(sidParam);
        try {
            InitialContext context = new InitialContext();
            String jndiSeminarBO = "osgi:service/" + SeminarBO.class.getName();
            SeminarBO seminarBO = (SeminarBO) context.lookup(jndiSeminarBO);
            Seminar seminar = seminarBO.findSeminarById(sid);
            httpServletRequest.setAttribute("seminar", seminar);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/seminar_detail.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

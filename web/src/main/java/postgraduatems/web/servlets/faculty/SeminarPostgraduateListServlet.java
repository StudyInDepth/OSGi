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
import java.util.List;

/**
 * Created by dotoan on 5/3/14.
 */
public class SeminarPostgraduateListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String older = httpServletRequest.getParameter("older");
        try {
            InitialContext context = new InitialContext();
            String jndiSeminarBO = "osgi:service/" + SeminarBO.class.getName();
            SeminarBO seminarBO = (SeminarBO) context.lookup(jndiSeminarBO);

            if (older == null) {
                Seminar seminar = seminarBO.findNewestSeminar();
                httpServletRequest.setAttribute("seminar", seminar);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/newest_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            } else {
                List<Seminar> seminars = seminarBO.findAllSeminars();

                if (seminars.size() > 1) {
                    httpServletRequest.setAttribute("seminars", seminars);
                    RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/seminar_list_all.jsp");
                    dispatcher.forward(httpServletRequest, httpServletResponse);
                } else {
                    Seminar seminar = seminarBO.findNewestSeminar();
                    httpServletRequest.setAttribute("seminar", seminar);
                    httpServletRequest.setAttribute("one", true);
                    RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/newest_seminar.jsp");
                    dispatcher.forward(httpServletRequest, httpServletResponse);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

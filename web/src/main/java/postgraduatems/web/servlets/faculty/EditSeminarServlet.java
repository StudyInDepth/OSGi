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
import java.sql.Date;

/**
 * Created by dotoan on 5/17/14.
 */
public class EditSeminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String idParam = httpServletRequest.getParameter("sid");
        int sid = Integer.parseInt(idParam);
        try {
            InitialContext context = new InitialContext();
            String jndiSeminarBO = "osgi:service/" + SeminarBO.class.getName();
            SeminarBO seminarBO = (SeminarBO) context.lookup(jndiSeminarBO);
            Seminar seminar = seminarBO.findSeminarById(sid);
            httpServletRequest.setAttribute("seminar", seminar);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/edit_seminar.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String title = httpServletRequest.getParameter("title");
        String des = httpServletRequest.getParameter("des");
        String sidParam = httpServletRequest.getParameter("sid");
        title = title.trim();
        des = des.trim();
        String pattern = "\\s+";
        String scheduledDateString = httpServletRequest.getParameter("scheduled_date");
        String startRegDateString = httpServletRequest.getParameter("start_reg_date");
        String dueRegDateString = httpServletRequest.getParameter("due_reg_date");
        System.out.println(scheduledDateString + "|" + startRegDateString + "|" + dueRegDateString);
        if (scheduledDateString.equals("")
                || startRegDateString.equals("") || dueRegDateString.equals("")
                || title.matches(pattern) || des.matches(pattern)) {
            System.out.println("ok");
            httpServletRequest.setAttribute("field_error", true);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/open_seminar.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);

        } else if (!scheduledDateString.equals("") && !startRegDateString.equals("") && !dueRegDateString.equals("")) {

            Date scheduledDate = Date.valueOf(scheduledDateString);
            Date startRegDate = Date.valueOf(startRegDateString);
            Date dueRegDate = Date.valueOf(dueRegDateString);
            try {
                InitialContext context = new InitialContext();
                String jndiSeminarBO = "osgi:service/" + SeminarBO.class.getName();
                SeminarBO seminarBO = (SeminarBO) context.lookup(jndiSeminarBO);
                Seminar seminar = seminarBO.update(Integer.parseInt(sidParam), title, des, scheduledDate, startRegDate, dueRegDate);
                httpServletRequest.setAttribute("seminar", seminar);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/edited_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

    }
}

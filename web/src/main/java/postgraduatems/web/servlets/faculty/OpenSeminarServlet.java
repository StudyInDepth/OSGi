package postgraduatems.web.servlets.faculty;

import postgraduatems.seminar.services.SeminarBO;
import postgraduatems.persistence.entities.Seminar;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by dotoan on 4/30/14.
 */
public class OpenSeminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String title = httpServletRequest.getParameter("title");
        String des = httpServletRequest.getParameter("des");
        title = title.trim();
        des = des.trim();
        String pattern = "\\s+";
        System.out.println(title + des);

        String scheduledDateString = httpServletRequest.getParameter("scheduled_date").trim();
        String startRegDateString = httpServletRequest.getParameter("start_reg_date").trim();
        String dueRegDateString = httpServletRequest.getParameter("due_reg_date").trim();
        System.out.println(scheduledDateString + "|" + startRegDateString + "|" + dueRegDateString);
        if (scheduledDateString.equals("")
                || startRegDateString.equals("") || dueRegDateString.equals("")
                || title.matches(pattern) || des.matches(pattern) || title.equals("") || des.equals("")) {
            System.out.println("ok");
            httpServletRequest.setAttribute("field_error", true);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/open_seminar.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);

        } else if (!scheduledDateString.equals("") && !startRegDateString.equals("")
                && !dueRegDateString.equals("")
                && !des.equals("") && !title.equals("")) {

            System.out.println("here");

            Date scheduledDate = Date.valueOf(scheduledDateString);
            Date startRegDate = Date.valueOf(startRegDateString);
            Date dueRegDate = Date.valueOf(dueRegDateString);

            java.util.Date currentUtilDate = new java.util.Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date currentSqlDate = java.sql.Date.valueOf(format.format(currentUtilDate));

            if (dueRegDate.compareTo(startRegDate) <= 0 ) {
                httpServletRequest.setAttribute("start_due_date_error", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/open_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            }



            if (scheduledDate.compareTo(startRegDate) <= 0) {
                httpServletRequest.setAttribute("start_scheduled_date_error", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/open_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            }

            if (scheduledDate.compareTo(dueRegDate) <= 0) {
                httpServletRequest.setAttribute("due_scheduled_date_error", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/open_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            }

            if (scheduledDate.compareTo(currentSqlDate) < 0) {
                httpServletRequest.setAttribute("current_scheduled_date_error", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/open_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            }

            if (startRegDate.compareTo(currentSqlDate) < 0) {
                httpServletRequest.setAttribute("start_current_date_error", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/open_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            }

            try {
                InitialContext context = new InitialContext();
                String jndiSeminarBO = "osgi:service/" + SeminarBO.class.getName();
                SeminarBO seminarBO = (SeminarBO) context.lookup(jndiSeminarBO);
                Seminar seminar = seminarBO.openSeminar(title, des, scheduledDate, startRegDate, dueRegDate);
                httpServletRequest.setAttribute("seminar", seminar);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/display_new_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }


}

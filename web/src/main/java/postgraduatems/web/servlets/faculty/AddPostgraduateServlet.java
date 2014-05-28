package postgraduatems.web.servlets.faculty;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.postgraduate.services.EditPostgraduateBO;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

/**
 * Created by dotoan on 5/8/14.
 */
public class AddPostgraduateServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/add_postgraduate.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String name = httpServletRequest.getParameter("name").trim();
        String email = httpServletRequest.getParameter("email").trim();
        String address = httpServletRequest.getParameter("name").trim();
        String phone = httpServletRequest.getParameter("name").trim();
        String year = httpServletRequest.getParameter("year").trim();
        String date = httpServletRequest.getParameter("date");

        if (name.equals("") || email.equals("") || address.equals("") || phone.equals("")
               || year.equals("") || date.equals("")) {
            httpServletRequest.setAttribute("field_error", true);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/add_postgraduate.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } else {
            try {
                int yearNumber = Integer.parseInt(year);
                Date dateOfBirth = Date.valueOf(date);
                try {
                    InitialContext ctx = new InitialContext();
                    String jndi = "osgi:service/" + EditPostgraduateBO.class.getName();
                    EditPostgraduateBO bo = (EditPostgraduateBO) ctx.lookup(jndi);
                    Postgraduate postgraduate = bo.add(name, email,address, phone, yearNumber, dateOfBirth);
                    httpServletRequest.setAttribute("postgraduate", postgraduate);
                    RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/display_new_postgraduate.jsp");
                    dispatcher.forward(httpServletRequest, httpServletResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                httpServletRequest.setAttribute("field_year_error", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/add_postgraduate.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            }

        }
    }
}

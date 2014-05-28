package postgraduatems.web.servlets.lecturer;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dotoan on 5/22/14.
 */
public class UserLecturerDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + LecturerBO.class.getName();
            LecturerBO bo = (LecturerBO) ctx.lookup(jndi);
            HttpSession session = httpServletRequest.getSession();

            Lecturer lecturer =  (Lecturer) session.getAttribute("lecturer");
            lecturer = bo.findLecturerById(lecturer.getId());
            httpServletRequest.setAttribute("lecturer", lecturer);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher
                    ("/view/lecturer/user_lecturer_detail.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            //PrintWriter out = httpServletResponse.getWriter();
          //  out.write("false");
           // e.printStackTrace();
        }
    }
}

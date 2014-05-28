package postgraduatems.web.servlets.dept;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Created by dotoan on 3/23/14.
 */

public class LecturerListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + LecturerBO.class.getName();
            LecturerBO bo = (LecturerBO) ctx.lookup(jndi);

            String keyword = httpServletRequest.getParameter("keyword");
            List<Lecturer> lecturerList = null;
            if ((keyword == null) || (keyword.equals(""))) {
                lecturerList = bo.getLecturerList();
            } else {
                lecturerList = bo.getLecturerList(keyword);
            }

            httpServletRequest.setAttribute("lecturerList", lecturerList);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/dept/list_lecturers.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}

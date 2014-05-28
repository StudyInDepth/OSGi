package postgraduatems.web.servlets.lecturer;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by dotoan on 3/31/14.
 */
public class LecturerDetailServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + LecturerBO.class.getName();
            LecturerBO bo = (LecturerBO) ctx.lookup(jndi);
            String idParam = httpServletRequest.getParameter("id");
            int id  = Integer.parseInt(idParam);
            Lecturer lecturer =  bo.findLecturerById(id);
            httpServletRequest.setAttribute("lecturer", lecturer);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/lecturer/lecturer_detail.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            PrintWriter out = httpServletResponse.getWriter();
            out.write("false");
            //e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }

}

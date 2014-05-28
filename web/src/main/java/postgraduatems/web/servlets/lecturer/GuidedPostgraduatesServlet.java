package postgraduatems.web.servlets.lecturer;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.User;
import postgraduatems.postgraduate.services.PostgraduateBO;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by dotoan on 4/27/14.
 */
public class GuidedPostgraduatesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            String jndiLecturerBO = "osgi:service/" + LecturerBO.class.getName();
            String jndiPostgraduateBO = "osgi:service/" + PostgraduateBO.class.getName();
            LecturerBO lecturerBO = (LecturerBO) ctx.lookup(jndiLecturerBO);
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndiPostgraduateBO);
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            int userId = user.getId();
            Lecturer lecturer = lecturerBO.findLecturerByUserId(userId);
            List<Postgraduate> postgraduates = postgraduateBO.getPostgraduateListByLecturerId(lecturer.getId());

            httpServletRequest.setAttribute("postgraduates", postgraduates);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/lecturer/guided_postgraduates.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

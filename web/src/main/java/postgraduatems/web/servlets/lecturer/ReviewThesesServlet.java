package postgraduatems.web.servlets.lecturer;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.Thesis;
import postgraduatems.persistence.entities.User;
import postgraduatems.seminar.services.SeminarBO;
import postgraduatems.thesis.services.ThesisBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by dotoan on 5/22/14.
 */
public class ReviewThesesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {

            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");

            InitialContext context = new InitialContext();
            String jndiThesisBO = "osgi:service/" + ThesisBO.class.getName();

            String jndiLecturerBO = "osgi:service/" + LecturerBO.class.getName();

            ThesisBO thesisBO = (ThesisBO) context.lookup(jndiThesisBO);

            LecturerBO lecturerBO = (LecturerBO) context.lookup(jndiLecturerBO);
            Lecturer lecturer = lecturerBO.findLecturerByUserId(user.getId());
            List<Thesis> theses = thesisBO.getReviewersById(lecturer.getId());
            System.out.println(theses + " theses");
            httpServletRequest.setAttribute("theses", theses);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/lecturer/review_theses.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

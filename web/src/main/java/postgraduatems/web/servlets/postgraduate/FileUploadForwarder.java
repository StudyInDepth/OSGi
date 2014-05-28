package postgraduatems.web.servlets.postgraduate;

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

/**
 * Created by dotoan on 4/21/14.
 */
public class FileUploadForwarder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO bo = (PostgraduateBO) ctx.lookup(jndi);
            Postgraduate postgraduate = bo.findPostgraduateByUserId(user.getId());
            httpServletRequest.setAttribute("postgraduate", postgraduate);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/thesis_description_upload.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/thesis_description_upload.jsp");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}

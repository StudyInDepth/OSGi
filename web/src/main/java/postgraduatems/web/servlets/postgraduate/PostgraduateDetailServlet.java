package postgraduatems.web.servlets.postgraduate;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.postgraduate.services.PostgraduateBO;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dotoan on 3/30/14.
 */
public class PostgraduateDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String idParam = httpServletRequest.getParameter("id");
        int id = Integer.parseInt(idParam);

        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO bo = (PostgraduateBO) ctx.lookup(jndi);
            Postgraduate postgraduate = bo.findPostgraduateById(id);

            if (postgraduate != null) {
                httpServletRequest.setAttribute("postgraduate", postgraduate);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/postgraduate_detail.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            } else {

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}

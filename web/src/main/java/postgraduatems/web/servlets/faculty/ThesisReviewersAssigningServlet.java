package postgraduatems.web.servlets.faculty;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.seminar.services.SeminarBO;
import postgraduatems.thesis.services.ThesisBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dotoan on 5/22/14.
 */
public class ThesisReviewersAssigningServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String tidParam = httpServletRequest.getParameter("tid");
        String pidParam = httpServletRequest.getParameter("pid");
        int tid = Integer.parseInt(tidParam);
        int pid = Integer.parseInt(pidParam);
        String[] lidsParam = httpServletRequest.getParameterValues("lids");
        int lids[] = null;
        if (lidsParam != null) {
            lids = new int[lidsParam.length];
            for (int i = 0; i < lidsParam.length; i++) {
                lids[i] = Integer.parseInt(lidsParam[i]);
            }
        }
        if (lids != null) {
            try {
                InitialContext context = new InitialContext();
                String jndi = "osgi:service/" + ThesisBO.class.getName();
                String jndiPO = "osgi:service/" + PostgraduateBO.class.getName();
                ThesisBO thesisBO = (ThesisBO) context.lookup(jndi);
                PostgraduateBO postgraduateBO = (PostgraduateBO) context.lookup(jndiPO);
                thesisBO.setReviewers(tid, lids);
                Postgraduate postgraduate = postgraduateBO.findPostgraduateById(pid);

                httpServletRequest.setAttribute("postgraduate", postgraduate);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/postgraduate_detail.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        } else {

        }
    }
}

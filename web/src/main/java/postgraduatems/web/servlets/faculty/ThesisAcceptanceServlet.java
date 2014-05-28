package postgraduatems.web.servlets.faculty;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.ThesisState;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.postgraduate.services.EditPostgraduateBO;
import postgraduatems.thesis.services.ThesisBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dotoan on 4/20/14.
 */
public class ThesisAcceptanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + PostgraduateBO.class.getName();
            String thesisJNDI = "osgi:service/" + ThesisBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndi);
            ThesisBO thesisBO = (ThesisBO) ctx.lookup(thesisJNDI);
            List<Integer> years = postgraduateBO.getYears();
            List<String> states = thesisBO.getStates();

            httpServletRequest.setAttribute("states", states);
            httpServletRequest.setAttribute("years", years);

            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/accept_thesis.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String year = httpServletRequest.getParameter("year").trim();
        String keyword = httpServletRequest.getParameter("keyword").trim();

        PostgraduateBO postgraduateBO = null;
        List<Postgraduate> postgraduates = null;
        ThesisBO thesisBO = null;
        String[] thesisIds = httpServletRequest.getParameterValues("thesis_ids");
        System.out.println(Arrays.toString(thesisIds));
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + PostgraduateBO.class.getName();
            String thesisJNDI = "osgi:service/" + ThesisBO.class.getName();
            postgraduateBO = (PostgraduateBO) ctx.lookup(jndi);
            thesisBO = (ThesisBO) ctx.lookup(thesisJNDI);

        } catch (NamingException e) {
            e.printStackTrace();
        }

        int[] ids = null;
        if (thesisIds != null && thesisIds.length > 0) {
            ids = new int[thesisIds.length];
            int i = 0;
            for (String thesisId : thesisIds) {
                ids[i++] = Integer.parseInt(thesisId);
            }
        }

        if (ids != null) {
            thesisBO.accept(ids);
        }
        if (keyword.equals("") && year.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateListByState(ThesisState.BEING_PREPARED);
        } else if (keyword.equals("") && !year.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateListByStateAndYear(ThesisState.BEING_PREPARED, Integer.parseInt(year));
        } else if (!keyword.equals("") && year.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateListByKeywordAndState(keyword, ThesisState.BEING_PREPARED);
        } else {
            postgraduates = postgraduateBO.getPostgraduateListByKeywordAndYearAndState(keyword, Integer.parseInt(year), ThesisState.BEING_PREPARED);
        }


        System.out.println(postgraduates);
        httpServletRequest.setAttribute("postgraduates", postgraduates);

        doGet(httpServletRequest, httpServletResponse);
    }
}

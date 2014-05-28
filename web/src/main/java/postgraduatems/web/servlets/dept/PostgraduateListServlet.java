package postgraduatems.web.servlets.dept;

import postgraduatems.persistence.entities.Major;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.thesis.services.ThesisBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by dotoan on 3/23/14.
 */

public class PostgraduateListServlet extends HttpServlet {
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
            List<Major> majors = postgraduateBO.geMajors();
            httpServletRequest.setAttribute("majors", majors);
            httpServletRequest.setAttribute("states", states);
            httpServletRequest.setAttribute("years", years);

            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/dept/list_postgraduates.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String year = httpServletRequest.getParameter("year").trim();
        String keyword = httpServletRequest.getParameter("keyword").trim();
        String state = httpServletRequest.getParameter("state").trim();
        String major = httpServletRequest.getParameter("major").trim();
        System.out.println("|" + year + "|" + keyword + "|" + state + "|");
        PostgraduateBO postgraduateBO = null;
        List<Postgraduate> postgraduates = null;
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + PostgraduateBO.class.getName();
            String thesisJNDI = "osgi:service/" + ThesisBO.class.getName();
            postgraduateBO = (PostgraduateBO) ctx.lookup(jndi);

        } catch (NamingException e) {
            e.printStackTrace();
        }


        if (keyword.equals("") && year.equals("") && state.equals("") && major.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateList();
            System.out.println("1");
        } else if (keyword.equals("") && year.equals("") && !state.equals("") && major.equals("")){
            postgraduates = postgraduateBO.getPostgraduateListByState(state);
            System.out.println("2");
        } else if (keyword.equals("") && !year.equals("") && state.equals("") && major.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateListByYear(Integer.parseInt(year));
            System.out.println("3");
        } else if (!keyword.equals("") && year.equals("") && state.equals("") && major.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateListByKeyword(keyword);
            System.out.println("4");
        } else if (keyword.equals("") && !year.equals("") && !state.equals("") && major.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateListByStateAndYear(state, Integer.parseInt(year));
            System.out.println("5");
        } else if (!keyword.equals("") && !year.equals("") && state.equals("") && major.equals("")){
            postgraduates = postgraduateBO.getPostgraduateListByKeywordAndYear(keyword, Integer.parseInt(year));
            System.out.println("6");
        } else if (!keyword.equals("") && year.equals("") && !state.equals("") && major.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateListByKeywordAndState(keyword, state);
            System.out.println("7");
        } else if (!keyword.equals("") && !year.equals("") && !state.equals("") && major.equals("")){
            postgraduates = postgraduateBO.getPostgraduateListByKeywordAndYearAndState(keyword,
                    Integer.parseInt(year), state);
            System.out.println("8");
        } else  if (keyword.equals("") && year.equals("") && state.equals("") && !major.equals("")){
            postgraduates = postgraduateBO.getPostgraduateListByMajor(Integer.parseInt(major));
            System.out.println("9");
        } else if (keyword.equals("") && year.equals("") && !state.equals("") && !major.equals("")){
            postgraduates = postgraduateBO.getPostgraduateListByMajorAndState(Integer.parseInt(major), state);
            System.out.println("10");
        } else if (keyword.equals("") && !year.equals("") && state.equals("") && !major.equals("")){
            postgraduates = postgraduateBO.getPostgraduateListByMajorAndYear(Integer.parseInt(major),
                    Integer.parseInt(year));
            System.out.println("11");
        } else if (!keyword.equals("") && year.equals("") && state.equals("") && !major.equals("")){
            postgraduates = postgraduateBO.getPostgraduateListByMajorAndKeyword(Integer.parseInt(major),keyword);
            System.out.println("12");
        } else if (keyword.equals("") && !year.equals("") && !state.equals("") && !major.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateListByMajorAndYearAndState(Integer.parseInt(major),
                    Integer.parseInt(year), state);
            System.out.println("13");
        } else  if (!keyword.equals("") && !year.equals("") && state.equals("") && !major.equals("")){
            postgraduates = postgraduateBO.getPostgraduateListByMajorAndKeywordAndYear(Integer.parseInt(major), keyword,
                    Integer.parseInt(year));
            System.out.println("14");
        } else if (!keyword.equals("") && year.equals("") && !state.equals("") && !major.equals("")) {
            postgraduates = postgraduateBO.getPostgraduateListByMajorAndKeywordAndState(Integer.parseInt(major), keyword, state);
            System.out.println("15");
        } else {
            postgraduates = postgraduateBO.getPostgraduateListByMajorAndYearAndStateAndKeyword(Integer.parseInt(major),
                    Integer.parseInt(year),state, keyword);
            System.out.println("16");
        }

        System.out.println(postgraduates);
        httpServletRequest.setAttribute("postgraduates", postgraduates);

        doGet(httpServletRequest, httpServletResponse);
    }
}

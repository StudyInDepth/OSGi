package postgraduatems.web.servlets.dept;

import postgraduatems.department.services.DepartmentStaffBO;
import postgraduatems.persistence.entities.DepartmentStaff;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Thesis;
import postgraduatems.persistence.entities.User;
import postgraduatems.postgraduate.services.PostgraduateBO;
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
 * Created by dotoan on 5/14/14.
 */
public class ThesisCommendationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        PostgraduateBO postgraduateBO = null;
        ThesisBO thesisBO = null;
        List<Postgraduate> postgraduates  = null;
        DepartmentStaffBO departmentStaffBO = null;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        String keyword = httpServletRequest.getParameter("keyword");
        String year = httpServletRequest.getParameter("year");
        String state = httpServletRequest.getParameter("state");

        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + PostgraduateBO.class.getName();
            String departmentBOINDJ = "osgi:service/" + DepartmentStaffBO.class.getName();
            String thesisJNDI = "osgi:service/" + ThesisBO.class.getName();
            postgraduateBO = (PostgraduateBO) ctx.lookup(jndi);
            thesisBO = (ThesisBO) ctx.lookup(thesisJNDI);
            departmentStaffBO = (DepartmentStaffBO) ctx.lookup(departmentBOINDJ);
            DepartmentStaff departmentStaff = departmentStaffBO.findDepartmentStaffByUserId(user.getId());
            int majorId = departmentStaff.getMajor().getId();
            List<Integer> years = postgraduateBO.getYears();
            List<String> states = thesisBO.getStates();

            httpServletRequest.setAttribute("states", states);
            httpServletRequest.setAttribute("years", years);

            if (keyword == null || year == null || state == null) {

                postgraduates = postgraduateBO.getPostgraduateListByMajor(majorId);


            } else if (keyword.equals("") && year.equals("") && state.equals("")) {
                postgraduates = postgraduateBO.getPostgraduateListByMajor(majorId);
            } else if (keyword.equals("") && year.equals("") && !state.equals("")) {
                postgraduates = postgraduateBO.getPostgraduateListByState(state);
            } else if (keyword.equals("") && !year.equals("") && state.equals("")) {
                postgraduates = postgraduateBO.getPostgraduateListByYear(Integer.parseInt(year));
            } else if (!keyword.equals("") && year.equals("") && state.equals("")) {
                postgraduates = postgraduateBO.getPostgraduateListByKeyword(keyword);
            } else if (keyword.equals("") && !year.equals("") && !state.equals("")) {
                postgraduates = postgraduateBO.getPostgraduateListByStateAndYear(state, Integer.parseInt(year));

            } else if (!keyword.equals("") && year.equals("") && !state.equals("")) {
                postgraduates = postgraduateBO.getPostgraduateListByKeywordAndState(keyword, state);
            } else if (!keyword.equals("") && !year.equals("") && state.equals("")) {
                postgraduates = postgraduateBO.getPostgraduateListByKeywordAndYear(keyword, Integer.parseInt(year));
            } else {
                postgraduates = postgraduateBO.getPostgraduateListByKeywordAndYearAndState(keyword,
                        Integer.parseInt(year), state);
            }

            httpServletRequest.setAttribute("postgraduates", postgraduates);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/dept/commend_thesis.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
            String  thesisIdParam = httpServletRequest.getParameter("tid");
            int thesisId = Integer.parseInt(thesisIdParam);
            String  comment = httpServletRequest.getParameter("comment").trim();
            comment = comment.trim();
            comment = comment.replaceAll("\\s+", " ");
            System.out.println(comment);


        try {
            InitialContext ctx = new InitialContext();


            String thesisJNDI = "osgi:service/" + ThesisBO.class.getName();

            ThesisBO thesisBO = (ThesisBO) ctx.lookup(thesisJNDI);
            thesisBO.commend(thesisId, comment);
            Thesis thesis = thesisBO.getThesisById(thesisId);
            String updatedComment = thesis.getComment();
            httpServletResponse.setContentType("text/html; charset=utf-8");
            httpServletResponse.getWriter().write(updatedComment);

        } catch (NamingException e) {
            httpServletResponse.getWriter().write("false");
            e.printStackTrace();
        }


    }
}

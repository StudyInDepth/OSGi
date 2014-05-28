package postgraduatems.web.servlets;

import postgraduatems.department.services.DepartmentStaffBO;
import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.*;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.user.services.UserBO;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dotoan on 3/30/14.
 */
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");

        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + UserBO.class.getName();
            UserBO bo = (UserBO) ctx.lookup(jndi);


            User user = bo.findUserByEmail(email);
            if (user != null) {
                boolean logged = bo.login(email, password);
                if (logged == true) {
                    HttpSession session = httpServletRequest.getSession();
                    session.setAttribute("user", user);

                    String role = user.getRole();

                    if (role.equals(Role.POSTGRADUATE)) {
                        String postgraduateBOJNDI = "osgi:service/" + PostgraduateBO.class.getName();
                        PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(postgraduateBOJNDI);
                        Postgraduate postgraduate = postgraduateBO.findPostgraduateByUserId(user.getId());
                        session.setAttribute("postgraduate", postgraduate);
                        httpServletResponse.sendRedirect("/postgraduate/home");
                    } else if (role.equals(Role.LECTURER)) {

                        String lecturerBOJNDI = "osgi:service/" + LecturerBO.class.getName();
                        LecturerBO lecturerBO = (LecturerBO) ctx.lookup(lecturerBOJNDI);
                        Lecturer  lecturer = lecturerBO.findLecturerByUserId(user.getId());
                        session.setAttribute("lecturer", lecturer);
                        httpServletResponse.sendRedirect("/lecturer/home");
                    } else if (role.equals(Role.DEPT)){

                        String deptBOJNDI = "osgi:service/" + DepartmentStaffBO.class.getName();
                        DepartmentStaffBO departmentStaffBO = (DepartmentStaffBO) ctx.lookup(deptBOJNDI);
                        DepartmentStaff staff = departmentStaffBO.findDepartmentStaffByUserId(user.getId());
                        session.setAttribute("staff", staff);
                        httpServletResponse.sendRedirect("/dept/home");
                    } else if (role.equals(Role.FACULTY)){
                        httpServletResponse.sendRedirect("/faculty/home");
                    }
                } else {
                    RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/login/login.jsp");
                    httpServletRequest.setAttribute("login_err", "true");
                    httpServletResponse.setContentType("text/html; charset=utf-8");
                    dispatcher.include(httpServletRequest, httpServletResponse);
                }
            } else {
                // user doesn't exist
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/login/login.jsp");
                httpServletRequest.setAttribute("login_err", "true");
                httpServletResponse.setContentType("text/html; charset=utf-8");
                dispatcher.include(httpServletRequest, httpServletResponse);
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

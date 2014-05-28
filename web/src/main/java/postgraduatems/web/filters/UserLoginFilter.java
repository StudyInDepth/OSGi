package postgraduatems.web.filters;

import postgraduatems.persistence.entities.Role;
import postgraduatems.persistence.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dotoan on 3/30/14.
 */
public class UserLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        Object user  = null;
        if(session != null) {
            user = session.getAttribute("user");
        }
        if(user == null) {
            if(email == null || password == null) {
                RequestDispatcher dispatcher = httpServletRequest
                    .getRequestDispatcher("/view/login/login.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);  
            }
        } else  {
            // user has already logged in
            User _user  = (User) user;
            String role = _user.getRole();
            if (role.equals(Role.POSTGRADUATE)) {
                httpServletResponse.sendRedirect("/postgraduate/home");
            } else if (role.equals(Role.LECTURER)) {
                httpServletResponse.sendRedirect("/lecturer/home");

            } else if (role.equals(Role.DEPT)){
                httpServletResponse.sendRedirect("/dept/home");

            } else {
                httpServletResponse.sendRedirect("/faculty/home");

            }
        }
    }

    @Override
    public void destroy() {

    }
}

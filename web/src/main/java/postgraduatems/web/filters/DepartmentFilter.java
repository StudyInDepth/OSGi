package postgraduatems.web.filters;

import postgraduatems.persistence.entities.Role;
import postgraduatems.persistence.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dotoan on 4/9/14.
 */
public class DepartmentFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            User _user = (User) user;
            String role = _user.getRole();
            if (role.equals(Role.DEPT)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.getWriter().println("Permission denied!");
            }
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}

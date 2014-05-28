package postgraduatems.web.servlets.postgraduate;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.User;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.postgraduate.services.SeminarRegistrationBO;
import postgraduatems.seminar.services.SeminarBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by dotoan on 5/3/14.
 */
public class SeminarRegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            String register  = httpServletRequest.getParameter("register");
            String unregister  = httpServletRequest.getParameter("unregister");

            InitialContext context = new InitialContext();
            String seminarRegistrationBOJNDI  = "osgi:service/" + SeminarRegistrationBO.class.getName();
            SeminarRegistrationBO seminarRegistrationBO = (SeminarRegistrationBO) context.lookup(seminarRegistrationBOJNDI);
            String jndiPostgraduateBO  = "osgi:service/" + PostgraduateBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) context.lookup(jndiPostgraduateBO);
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            Postgraduate postgraduate = postgraduateBO.findPostgraduateByUserId(user.getId());
            if (register != null) {
                int sid = Integer.parseInt(register);
                seminarRegistrationBO.register(postgraduate.getId(), sid);
                httpServletRequest.setAttribute("register_succeeded", true);
            }  else if (unregister != null){
                int sid = Integer.parseInt(unregister);
                seminarRegistrationBO.unregister(postgraduate.getId(), sid);
                httpServletRequest.setAttribute("unregister_succeeded", true);
            }
            doGet(httpServletRequest, httpServletResponse);
        }  catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext context = new InitialContext();
            String jndiSeminarBO  = "osgi:service/" + SeminarBO.class.getName();
            SeminarBO seminarBO = (SeminarBO) context.lookup(jndiSeminarBO);
            Seminar seminar = seminarBO.findNewestSeminar();
            System.out.println("newest seminar" + seminar);

            if (seminar != null) {
                String jndiPostgraduateBO  = "osgi:service/" + PostgraduateBO.class.getName();
                PostgraduateBO postgraduateBO = (PostgraduateBO) context.lookup(jndiPostgraduateBO);
                HttpSession session = httpServletRequest.getSession();
                User user = (User) session.getAttribute("user");
                Postgraduate postgraduate = postgraduateBO.findPostgraduateByUserId(user.getId());
                String state = postgraduate.getThesis().getState();
                Seminar registeredSeminar = postgraduate.getSeminar();
                System.out.println(registeredSeminar);
                System.out.println("seminar id 2" + seminar.getId());
                boolean registered = false;
                boolean registrable = false;
                boolean dueDate = false;

                if (registeredSeminar != null && registeredSeminar.getId() == seminar.getId()) {
                        registered = true;

                }

                java.util.Date currentUtilDate = new java.util.Date();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date currentSqlDate = java.sql.Date.valueOf(format.format(currentUtilDate));

                if (currentSqlDate.compareTo(seminar.getStartRegisteringDate()) >= 0
                        && currentSqlDate.compareTo(seminar.getDueRegisteringDate()) <= 0) {
                    registrable = true;
                } else if (currentSqlDate.compareTo(seminar.getStartRegisteringDate()) < 0) {
                    dueDate = false;
                } else {
                    dueDate = true;
                }

                httpServletRequest.setAttribute("registered", registered);
                httpServletRequest.setAttribute("registrable", registrable);
                httpServletRequest.setAttribute("dueDate", dueDate);
                httpServletRequest.setAttribute("seminar", seminar);
                httpServletRequest.setAttribute("state", state);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_newest_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            } else {
                httpServletRequest.setAttribute("nullSeminar", true);
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/postgraduate/register_newest_seminar.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);

            }
        }  catch (NamingException e) {
            e.printStackTrace();
        }

    }
}

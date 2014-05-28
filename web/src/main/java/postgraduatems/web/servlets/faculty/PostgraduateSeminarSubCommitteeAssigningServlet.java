package postgraduatems.web.servlets.faculty;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.SeminarSubCommittee;
import postgraduatems.postgraduate.services.PostgraduateBO;
import postgraduatems.seminar.services.SeminarSubcommitteeAssigningBO;
import postgraduatems.seminar.services.SeminarBO;
import postgraduatems.seminar.services.SeminarSubCommitteeBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dotoan on 5/18/14.
 */
public class PostgraduateSeminarSubCommitteeAssigningServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            String jndi = "osgi:service/" + PostgraduateBO.class.getName();
            String jndiSeminarBO = "osgi:service/" + SeminarBO.class.getName();
            String subCommitteeBOJNDI = "osgi:service/" + SeminarSubCommitteeBO.class.getName();
            PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndi);

            SeminarBO seminarBO = (SeminarBO) ctx.lookup(jndiSeminarBO);
            SeminarSubCommitteeBO seminarSubCommitteeBO = (SeminarSubCommitteeBO) ctx.lookup(subCommitteeBOJNDI);


            List<SeminarSubCommittee> seminarSubCommittees = seminarSubCommitteeBO.getNewestSeminarSubCommittees();
            System.out.println("gggggOKOKO " + seminarSubCommittees);
            if ((seminarSubCommittees == null) || ( seminarSubCommittees != null && seminarSubCommittees.size() == 0)
                    ) {
                httpServletRequest.setAttribute("emptySeminarSubCommittees", true);
                System.out.println("111");

            } else if ( seminarSubCommittees != null && seminarSubCommittees.size() > 0){
                Seminar seminar = seminarBO.findSeminarById(seminarSubCommittees.get(0).getSeminar().getId());
                List<? extends Postgraduate> postgraduates = seminar.getPostgraduates();
                System.out.println("222");
                System.out.println();

                httpServletRequest.setAttribute("postgraduates", postgraduates);
                httpServletRequest.setAttribute("seminarSubCommittees", seminarSubCommittees);
            }


            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/assign_seminar_subcommittee.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            httpServletRequest.setAttribute("seminar_services_unavailable", true);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/assign_seminar_subcommittee.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);

           //e.printStackTrace();

            System.out.println("333");

        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String ssidParam = httpServletRequest.getParameter("ssid");
        String pidParam = httpServletRequest.getParameter("pid");
        if (!ssidParam.equals("")) {
            int ssid = Integer.parseInt(ssidParam);
            int pid = Integer.parseInt(pidParam);

            try {
                InitialContext ctx = new InitialContext();
                String jndi = "osgi:service/" + SeminarSubcommitteeAssigningBO.class.getName();
                String jndiPBO = "osgi:service/" + PostgraduateBO.class.getName();
                SeminarSubcommitteeAssigningBO assigningBO = (SeminarSubcommitteeAssigningBO) ctx.lookup(jndi);
                assigningBO.assign(pid, ssid);
                PostgraduateBO postgraduateBO = (PostgraduateBO) ctx.lookup(jndiPBO);
                Postgraduate postgraduate = postgraduateBO.findPostgraduateById(pid);
                SeminarSubCommittee committee = postgraduate.getSeminarSubCommittee();
                String response = committee.getName();
                httpServletResponse.setContentType("text/html; charset=utf-8");
                httpServletResponse.getWriter().write(response);

            } catch (NamingException e) {
                httpServletResponse.getWriter().write("false");
                //e.printStackTrace();
            }
        } else {
            httpServletResponse.getWriter().write("empty_ssid");
        }
    }
}

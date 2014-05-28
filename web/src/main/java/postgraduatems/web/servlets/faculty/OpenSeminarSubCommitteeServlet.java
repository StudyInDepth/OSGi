package postgraduatems.web.servlets.faculty;

import postgraduatems.lecturer.services.LecturerBO;
import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.SeminarSubCommittee;
import postgraduatems.postgraduate.services.PostgraduateBO;
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
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by dotoan on 5/6/14.
 */
public class OpenSeminarSubCommitteeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            InitialContext context = new InitialContext();
            String jndiSeminarBO = "osgi:service/" + SeminarBO.class.getName();
            SeminarBO seminarBO = (SeminarBO) context.lookup(jndiSeminarBO);
            Seminar seminar = seminarBO.findNewestSeminar();
            httpServletRequest.setAttribute("seminar", seminar);

            String jndiLecturerBO = "osgi:service/" + LecturerBO.class.getName();
            LecturerBO lecturerBO = (LecturerBO) context.lookup(jndiLecturerBO);
            List<Lecturer> lecturers = lecturerBO.getLecturerList();
            httpServletRequest.setAttribute("lecturers", lecturers);

            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/open_seminar_committee.jsp");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String name = httpServletRequest.getParameter("name").trim();
        String place = httpServletRequest.getParameter("place").trim();
        String heldDateParam = httpServletRequest.getParameter("held_date").trim();
        String startingTimeParam = httpServletRequest.getParameter("starting_time").trim();
        String seminarIdParam = httpServletRequest.getParameter("seminar_id").trim();
        String presidentIdParam = httpServletRequest.getParameter("president_id").trim();
        String vicePresidentIdParam = httpServletRequest.getParameter("vice_president_id").trim();
        String secretaryIdParam = httpServletRequest.getParameter("secretary_id").trim();
        String[] lecturerIdsParam = httpServletRequest.getParameterValues("lecturer_ids");

        if (name.trim().equals("") || place.trim().equals("") || heldDateParam.equals("")
                || startingTimeParam.equals("") || seminarIdParam.equals("") || presidentIdParam.equals("")
                || vicePresidentIdParam.equals("") || secretaryIdParam.equals("")
                || lecturerIdsParam == null ) {
            httpServletRequest.setAttribute("field_error", true);
            doGet(httpServletRequest, httpServletResponse);
        } else {

            int seminarId = Integer.parseInt(seminarIdParam);
            int presidentId = Integer.parseInt(presidentIdParam);
            int vicePresidentId = Integer.parseInt(vicePresidentIdParam);
            int secretaryId = Integer.parseInt(secretaryIdParam);
            Time staringTime = Time.valueOf(startingTimeParam+":00");
            Date heldDate = Date.valueOf(heldDateParam);
            java.util.Date currentUtilDate = new java.util.Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date currentSqlDate = java.sql.Date.valueOf(format.format(currentUtilDate));
            if (heldDate.compareTo(currentSqlDate) < 0) {
                httpServletRequest.setAttribute("heldDateError", true);
                doGet(httpServletRequest, httpServletResponse);
            }

            int[] lecturerIds = new int[lecturerIdsParam.length];
            int i = 0;
            for (String lecturerIdString : lecturerIdsParam) {
                lecturerIds[i++] = Integer.parseInt(lecturerIdString);
            }

            try {
                InitialContext context = new InitialContext();
                String jndiSeminarSubCommitteeBO = "osgi:service/" + SeminarSubCommitteeBO.class.getName();
                SeminarSubCommitteeBO seminarSubCommitteeBO = (SeminarSubCommitteeBO) context.lookup(jndiSeminarSubCommitteeBO);
                SeminarSubCommittee seminarSubCommittee = seminarSubCommitteeBO.save(name, place, heldDate, staringTime,
                        seminarId, presidentId, secretaryId, vicePresidentId, lecturerIds);
                httpServletRequest.setAttribute("seminarSubCommittee", seminarSubCommittee);

                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/view/faculty/display_new_seminar_subcommittee.jsp");
                dispatcher.forward(httpServletRequest, httpServletResponse);

            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
}

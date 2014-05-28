package postgraduatems.persistence.services.impl;

import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.SeminarSubCommittee;
import postgraduatems.persistence.entities.impl.LecturerImpl;
import postgraduatems.persistence.entities.impl.SeminarImpl;
import postgraduatems.persistence.entities.impl.SeminarSubCommitteeImpl;
import postgraduatems.persistence.services.SeminarSubCommitteeDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by dotoan on 5/6/14.
 */
public class SeminarSubCommitteeDAOImpl implements SeminarSubCommitteeDAO {
    EntityManager entityManager;

    @Override
    public SeminarSubCommittee save(String name, String place, Date heldDate, Time staringTime,
                                    int seminarId, int presidentId, int secretaryId, int vicePresidentId,
                                    int[] lecturerIds) {


        SeminarSubCommitteeImpl seminarSubCommittee = new SeminarSubCommitteeImpl();
        seminarSubCommittee.setName(name);
        seminarSubCommittee.setPlace(place);

        seminarSubCommittee.setHeldDate(heldDate);
        seminarSubCommittee.setStaringTime(staringTime);

        SeminarImpl seminar = entityManager.find(SeminarImpl.class, seminarId);
        System.out.println(seminar);

        seminarSubCommittee.setSeminar(seminar);


        LecturerImpl president = entityManager.find(LecturerImpl.class, presidentId);
        seminarSubCommittee.setPresident(president);

        LecturerImpl vicePresident = entityManager.find(LecturerImpl.class, vicePresidentId);
        seminarSubCommittee.setVicePresident(vicePresident);

        LecturerImpl secretary = entityManager.find(LecturerImpl.class, secretaryId);
        seminarSubCommittee.setSecretary(secretary);

        entityManager.persist(seminarSubCommittee);
        entityManager.flush();
        entityManager.refresh(seminarSubCommittee);

        if (lecturerIds != null && lecturerIds.length > 0) {
            for (int id : lecturerIds) {
                LecturerImpl lecturer = entityManager.find(LecturerImpl.class, id);
                seminarSubCommittee.addLecturer(lecturer);
            }
        }


        entityManager.merge(seminarSubCommittee);
        entityManager.flush();
        entityManager.refresh(seminarSubCommittee);
        return seminarSubCommittee;
    }

    @Override
    public SeminarSubCommittee findSeminarSubCommitteeById(int id) {
        return entityManager.find(SeminarSubCommitteeImpl.class, id);
    }

    @Override
    public List<SeminarSubCommittee> getNewestSeminarSubCommittees() {
        java.util.Date currentUtilDate = new java.util.Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date currentSqlDate = java.sql.Date.valueOf(format.format(currentUtilDate));
        String dateCriterion = currentSqlDate.toString();
        dateCriterion = "'" + dateCriterion + "'";
        String sql = "select s from seminar_subcommittees s " +
                " Where s.heldDate > " + dateCriterion;
        Query query = entityManager.createQuery(sql, SeminarSubCommitteeImpl.class);
        return query.getResultList();
    }

    @Override
    public SeminarSubCommittee update(int id, String name, String place, Date heldDate, Time staringTime,
                                      int seminarId, int presidentId, int secretaryId, int vicePresidentId,
                                      int[] lecturerIds) {
        SeminarSubCommitteeImpl seminarSubCommittee = entityManager.find(SeminarSubCommitteeImpl.class, id);
        seminarSubCommittee.setName(name);
        seminarSubCommittee.setPlace(place);
        seminarSubCommittee.setHeldDate(heldDate);
        seminarSubCommittee.setStaringTime(staringTime);

        SeminarImpl seminar = entityManager.find(SeminarImpl.class, seminarId);
        System.out.println(seminar);

        seminarSubCommittee.setSeminar(seminar);


        LecturerImpl president = entityManager.find(LecturerImpl.class, presidentId);
        seminarSubCommittee.setPresident(president);

        LecturerImpl vicePresident = entityManager.find(LecturerImpl.class, vicePresidentId);
        seminarSubCommittee.setVicePresident(vicePresident);

        LecturerImpl secretary = entityManager.find(LecturerImpl.class, secretaryId);
        seminarSubCommittee.setSecretary(secretary);


        if (seminarSubCommittee.getLecturers().size() > 0) {
            for (int i = seminarSubCommittee.getLecturers().size() - 1; i >= 0; i--) {
                seminarSubCommittee.removeLecturer(i);
            }
        }


        if (lecturerIds != null && lecturerIds.length > 0) {
            for (int lid : lecturerIds) {
                LecturerImpl lecturer = entityManager.find(LecturerImpl.class, lid);
                seminarSubCommittee.addLecturer(lecturer);
            }
        }


        if (seminarSubCommittee.getPostgraduates().size() > 0) {
            for (int i = seminarSubCommittee.getPostgraduates().size() - 1; i >= 0; i--) {
                seminarSubCommittee.removePostgraduate(i);
            }
        }




        entityManager.merge(seminarSubCommittee);
        entityManager.flush();
        entityManager.refresh(seminarSubCommittee);
        return seminarSubCommittee;
    }

    @Override
    public SeminarSubCommittee findLecturerSeminarSubCommittee(int id) {
        List<SeminarSubCommittee> seminarSubCommittees = null;
        Seminar seminar = null;


        java.util.Date currentUtilDate = new java.util.Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date currentSqlDate = java.sql.Date.valueOf(format.format(currentUtilDate));
        String dateCriterion = currentSqlDate.toString();
        dateCriterion = "'" + dateCriterion + "'";
        System.out.println(currentSqlDate);
        String sql = "select s from seminars s "
                + " WHERE s.scheduledDate >= " + dateCriterion
                + " order by s.scheduledDate DESC";
        Query query = entityManager.createQuery(sql, SeminarImpl.class);
        List<Seminar> seminars = query.getResultList();
        if (seminars.size() > 0) {
            seminar = seminars.get(0);
        }

        if (seminar != null) {
            String sql1 = "select ss from seminar_subcommittees ss where ss.seminar.id = " + seminar.getId();
            Query query1 = entityManager.createQuery(sql1, SeminarSubCommitteeImpl.class);
            seminarSubCommittees = query1.getResultList();
            if (seminarSubCommittees.size() > 0) {
                for (SeminarSubCommittee committee : seminarSubCommittees) {
                    if (committee.getPresident().getId() == id) {
                        return committee;
                    }

                    if (committee.getSecretary().getId() == id) {
                        return committee;
                    }


                    if (committee.getVicePresident().getId() == id) {
                        return committee;
                    }

                    for (Lecturer lecturer : committee.getLecturers()) {
                        if (lecturer.getId() == id) {
                            return committee;
                        }
                    }

                }
            }
        }
        return null;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void init() {
        System.out.println(this);
    }
}

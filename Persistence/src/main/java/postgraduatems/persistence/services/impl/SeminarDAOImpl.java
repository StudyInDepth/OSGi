package postgraduatems.persistence.services.impl;

import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.SeminarSubCommittee;
import postgraduatems.persistence.entities.impl.SeminarImpl;
import postgraduatems.persistence.services.SeminarDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by dotoan on 4/30/14.
 */
public class SeminarDAOImpl implements SeminarDAO {

    EntityManager entityManager;
    @Override
    public Seminar save(String title, String description, Date scheduledDate, Date startRegDate,
                        Date dueRegDate) {
        SeminarImpl seminar = new SeminarImpl(title, description, scheduledDate, startRegDate, dueRegDate);
        entityManager.persist(seminar);
        entityManager.refresh(seminar);
        entityManager.flush();
        return seminar;
    }

    @Override
    public List<Seminar> findAllSeminars() {
        String sql = "select s from seminars s order by s.scheduledDate DESC";
        Query query = entityManager.createQuery(sql, SeminarImpl.class);
        return query.getResultList();
    }


    @Override
    public Seminar delete(int id) {
        Seminar seminar = entityManager.find(SeminarImpl.class, id);
        Seminar returnSeminar = seminar;
        entityManager.remove(seminar);
        entityManager.flush();
        System.out.println(returnSeminar);
        return returnSeminar;
    }

    @Override
    public Seminar findNewestSeminar() {
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
            return seminars.get(0);
        }
        return null;
    }

    @Override
    public Seminar update(int id, String title, String description, Date scheduledDate, Date startRegDate, Date dueRegDate) {
        SeminarImpl seminar = entityManager.find(SeminarImpl.class, id);
        seminar.setTitle(title);
        seminar.setDescription(description);
        seminar.setScheduledDate(scheduledDate);
        seminar.setStartRegisteringDate(startRegDate);
        seminar.setDueRegisteringDate(dueRegDate);
        entityManager.merge(seminar);
        entityManager.flush();
        entityManager.refresh(seminar);
        return seminar;
    }

    @Override
    public Seminar findSeminarById(int id) {
        return entityManager.find(SeminarImpl.class, id);
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

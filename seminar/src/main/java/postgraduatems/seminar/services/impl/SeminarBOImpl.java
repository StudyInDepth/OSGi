package postgraduatems.seminar.services.impl;

import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.services.SeminarDAO;
import postgraduatems.seminar.services.SeminarBO;

import java.sql.Date;
import java.util.List;

/**
 * Created by dotoan on 4/30/14.
 */
public class SeminarBOImpl implements SeminarBO {
    SeminarDAO dao;

    @Override
    public List<Seminar> findAllSeminars() {
        return dao.findAllSeminars();
    }

    @Override
    public Seminar findNewestSeminar() {
        return dao.findNewestSeminar();
    }

    @Override
    public Seminar update(int id, String title, String description, Date scheduledDate, Date startRegDate, Date dueRegDate) {
        return dao.update(id, title, description, scheduledDate, startRegDate, dueRegDate);
    }

    @Override
    public Seminar findSeminarById(int id) {
        return dao.findSeminarById(id);
    }



    @Override
    public Seminar openSeminar(String title, String description, Date scheduledDate,
                               Date startRegDate, Date dueRegDate) {
        return dao.save(title, description, scheduledDate, startRegDate, dueRegDate);
    }

    public SeminarDAO getDao() {
        return dao;
    }

    public void setDao(SeminarDAO dao) {
        this.dao = dao;
    }

    public void init() {
        System.out.println(this);
       // System.out.println(findNewestSeminar().getScheduledDate());

    }

}

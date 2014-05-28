package postgraduatems.lecturer.services.impl;

import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.services.LecturerDAO;
import postgraduatems.lecturer.services.LecturerBO;

import java.util.List;

/**
 * Created by toandv on 3/29/14.
 */
public class LecturerBOImpl implements LecturerBO {
    private LecturerDAO dao;

    public LecturerDAO getDao() {
        return dao;
    }

    public void setDao(LecturerDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Lecturer> getLecturerList() {
        return dao.getLecturerList();
    }

    @Override
    public List<Lecturer> getLecturerList(String keyword) {
        return dao.getLecturerList(keyword);
    }

    @Override
    public Lecturer findLecturerByUserId(int id) {
        return dao.findLecturerByUserId(id);
    }

    @Override
    public Lecturer findLecturerById(int id) {
        return dao.findLecturerById(id);
    }

    @Override
    public Lecturer findLecturerByEmail(String email) {
        return dao.findLecturerByEmail(email);
    }

    public void init() {
        System.out.println(this);
    }
}

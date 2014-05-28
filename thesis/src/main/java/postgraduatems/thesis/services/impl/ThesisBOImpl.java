package postgraduatems.thesis.services.impl;

import postgraduatems.persistence.entities.Thesis;
import postgraduatems.persistence.entities.ThesisState;
import postgraduatems.persistence.services.ThesisDAO;
import postgraduatems.thesis.services.ThesisBO;

import java.util.List;

/**
 * Created by toandv on 3/29/14.
 */
public class ThesisBOImpl implements ThesisBO {

    private ThesisDAO dao;
    public void init() {
        System.out.println(this);
    }

    @Override
    public void confirm(int id) {
        dao.confirm(id);
    }

    @Override
    public List<Thesis> getReviewersById(int id) {
        return dao.getReviewersById(id);
    }

    @Override
    public Thesis setReviewers(int tid, int[] reviewerIds) {
        return dao.setReviewers(tid, reviewerIds);
    }

    @Override
    public Thesis getThesisById(int id) {
        return dao.findThesisById(id);
    }

    @Override
    public void commend(int id, String comment) {
        dao.commend(id, comment);
    }

    @Override
    public List<String> getStates() {
        return dao.getStates();
    }

    @Override
    public void verifyDescription(int[] ids) {
        for (int id : ids ) {
            dao.verifyDescription(id);
        }
    }


    @Override
    public void uploadDescription(int id) {
       dao.uploadDescription(id);
    }

    @Override
    public void accept(int[] ids) {

        for (int id : ids) {
            dao.accept(id);
        }

    }

    @Override
    public Thesis update(Thesis thesis) {
        return dao.update(thesis);
    }

    public ThesisDAO getDao() {
        return dao;
    }

    public void setDao(ThesisDAO dao) {
        this.dao = dao;
    }
}

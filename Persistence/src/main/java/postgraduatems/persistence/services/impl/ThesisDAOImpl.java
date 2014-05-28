package postgraduatems.persistence.services.impl;

import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Thesis;
import postgraduatems.persistence.entities.ThesisState;
import postgraduatems.persistence.entities.impl.LecturerImpl;
import postgraduatems.persistence.entities.impl.PostgraduateImpl;
import postgraduatems.persistence.entities.impl.ThesisImpl;
import postgraduatems.persistence.services.ThesisDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dotoan on 4/27/14.
 */
public class ThesisDAOImpl implements ThesisDAO {

    private EntityManager entityManager;

    @Override
    public void confirm(int id) {
        ThesisImpl thesis = entityManager.find(ThesisImpl.class, id);

        entityManager.merge(thesis);
        entityManager.flush();
    }

    @Override
    public void accept(int id) {
        ThesisImpl thesis = entityManager.find(ThesisImpl.class, id);
        thesis.setState(ThesisState.BEING_VERIFIED);
        entityManager.merge(thesis);
        entityManager.flush();
    }

    @Override
    public void verifyDescription(int id) {
        ThesisImpl thesis = entityManager.find(ThesisImpl.class, id);
        thesis.setState(ThesisState.STARTED);
        entityManager.merge(thesis);
        entityManager.flush();
    }

    @Override
    public void uploadDescription(int id) {
        ThesisImpl thesis = entityManager.find(ThesisImpl.class, id);
        thesis.setFileUploaded(true);
        entityManager.merge(thesis);
        entityManager.flush();
    }

    @Override
    public Thesis update(Thesis thesis) {
        entityManager.merge(thesis);
        entityManager.flush();
        return entityManager.find(ThesisImpl.class, thesis.getId());
    }

    @Override
    public Thesis findThesisById(int id) {
        return entityManager.find(ThesisImpl.class, id);
    }

    @Override
    public List<String> getStates() {
        String queryString = "SELECT DISTINCT t.state FROM theses t  ORDER BY t.state";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return q.getResultList();
    }

    @Override
    public void commend(int id, String comment) {
        ThesisImpl thesis = entityManager.find(ThesisImpl.class, id);
        thesis.setComment(comment);
        entityManager.merge(thesis);
        entityManager.flush();
    }

    @Override
    public Thesis setReviewers(int tid, int[] reviewerIds) {
        ThesisImpl thesis = entityManager.find(ThesisImpl.class, tid);

        thesis.setReviewers(null);


        entityManager.merge(thesis);
        entityManager.flush();
        entityManager.refresh(thesis);
        System.out.println("reviewers " + thesis.getReviewers());

        //   thesis = entityManager.find(ThesisImpl.class, tid);
        if (reviewerIds != null) {
            for (int id : reviewerIds) {
                LecturerImpl lecturer = entityManager.find(LecturerImpl.class, id);
                thesis.addReviewer(lecturer);
            }
        }


        thesis.setState(ThesisState.PRESENTATION_PREPARED);
        entityManager.merge(thesis);
        entityManager.flush();
        entityManager.refresh(thesis);
        return thesis;
    }

    @Override
    public List<Thesis> getReviewersById(int id) {

        String sql = "select t from theses t";

        Query query = entityManager.createQuery(sql, ThesisImpl.class);
        List<Thesis> theses = query.getResultList();
        List<Thesis> reviewTheses = new ArrayList<Thesis>();
        if (theses.size() > 0) {
            for (Thesis thesis : theses) {
                for (Lecturer lecturer : thesis.getReviewers()) {
                    if (thesis.getId() == id) {
                        reviewTheses.add(thesis);
                    }
                }
            }
        }
        return reviewTheses;
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

package postgraduatems.persistence.services.impl;

import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.impl.LecturerImpl;
import postgraduatems.persistence.services.LecturerDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by dotoan on 3/23/14.
 */
public class LecturerDAOImpl implements LecturerDAO {
    EntityManager entityManager;



    @Override
    public Lecturer findLecturerByEmail(String email) {
        String sql = "select l from lecturers l where l.email = '" + email + "'";
        Query query = entityManager.createQuery(sql, LecturerImpl.class);
        List<Lecturer> lecturers = query.getResultList();
        if (lecturers != null && lecturers.size() != 0) {
            return lecturers.get(0);
        }
        return null;
    }

    @Override
    public Lecturer findLecturerByUserId(int uid) {
        String sql = "select l from lecturers l where l.userId = " + uid + "";
        Query query = entityManager.createQuery(sql, LecturerImpl.class);
        List<Lecturer> lecturers = query.getResultList();
        if (lecturers != null && lecturers.size() != 0) {
            return lecturers.get(0);
        }
        return null;
    }

    @Override
    public Lecturer findLecturerById(int id) {
        return entityManager.find(LecturerImpl.class, id);
    }

    @Override
    public List<Lecturer> getLecturerList(String keyword) {
        String queryString = "SELECT l from lecturers l"
                + " WHERE "
                + " (l.name LIKE '%" + keyword + "%'"
                + " OR l.degree LIKE '%" + keyword + "%'"
                + " OR l.email LIKE '%" + keyword + "%'"
                + " OR l.address LIKE '%" + keyword + "%')"
                + " ORDER BY l.name";
        Query q = entityManager.createQuery(queryString, LecturerImpl.class);
        System.out.println(queryString);
        return  q.getResultList();
    }

    @Override
    public List<Lecturer> getLecturerList() {
        String sql = "SELECT l FROM lecturers l ORDER BY l.name";
        Query q = entityManager.createQuery(sql, LecturerImpl.class);
        return q.getResultList();
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


package postgraduatems.persistence.services.impl;

import postgraduatems.persistence.entities.Major;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Role;
import postgraduatems.persistence.entities.ThesisState;
import postgraduatems.persistence.entities.impl.*;
import postgraduatems.persistence.services.PostgraduateDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

/**
 * Created by dotoan on 3/23/14.
 */
public class PostgraduateDAOImpl implements PostgraduateDAO {

    private EntityManager entityManager;



    @Override
    public List<Postgraduate> getPostgraduateList() {
        String queryString = "SELECT  p FROM postgraduates p  ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return q.getResultList();
    }

    @Override
    public List<Major> getMajors() {
        String queryString = "SELECT m FROM majors m";
        Query q = entityManager.createQuery(queryString, MajorImpl.class);
        return q.getResultList();
    }

    @Override
    public List<Integer> getYears() {
        String queryString = "SELECT DISTINCT p.year FROM postgraduates p  ORDER BY p.year";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return q.getResultList();
    }

    @Override
    public Postgraduate findPostgraduateByEmail(String email) {
        String sql = "select p from postgraduates p where p.email = '" + email + "'";
        Query query = entityManager.createQuery(sql, PostgraduateImpl.class);
        List<Postgraduate> postgraduates = query.getResultList();
        if (postgraduates != null && postgraduates.size() != 0) {
            return postgraduates.get(0);
        }
        return null;
    }

    @Override
    public List<Postgraduate> getPostgraduateListByKeywordAndYearAndState(String keyword, int year, String state) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE p.year=" + year
                + " AND p.thesis.state = '" + state + "'"
                + " AND (p.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.state LIKE '%" + keyword + "%'"
                + " OR p.lecturer.name LIKE '%" + keyword + "%')"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByKeywordAndState(String keyword, String state) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE p.thesis.state = '" + state + "'"
                + " AND (p.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.state LIKE '%" + keyword + "%'"
                + " OR p.lecturer.name LIKE '%" + keyword + "%')"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndYear(int majorId, int year) {
        String sql = "select p from postgraduates p where p.major.id = " + majorId
                + " AND p.year = " + year
                + " ORDER BY p.name";

        Query query = entityManager.createQuery(sql, PostgraduateImpl.class);
        return query.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndState(int majorId, String state) {
        String sql = "select p from postgraduates p where p.major.id = " + majorId
                + " AND p.thesis.state = '" + state + "'"
                + " ORDER BY p.name";
        Query query = entityManager.createQuery(sql, PostgraduateImpl.class);
        return query.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndKeyword(int majorId, String keyword) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE p.major.id = " + majorId
                + " AND (p.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.state LIKE '%" + keyword + "%'"
                + " OR p.lecturer.name LIKE '%" + keyword + "%')"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndKeywordAndYear(int majorId, String keyword, int year) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE p.year=" + year
                + " AND p.major.id = " + majorId
                + " AND (p.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.state LIKE '%" + keyword + "%'"
                + " OR p.lecturer.name LIKE '%" + keyword + "%')"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndKeywordAndState(int majorId, String keyword, String state) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE p.thesis.state = '" + state + "'"
                + " AND p.major.id = " + majorId
                + " AND (p.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.state LIKE '%" + keyword + "%'"
                + " OR p.lecturer.name LIKE '%" + keyword + "%')"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndYearAndState(int majorId, int year, String state) {
        String sql = "select p from postgraduates p where p.major.id = " + majorId
                + " AND p.thesis.state = '" + state + "'"
                + " AND p.year = " + year
                + " ORDER BY p.name";
        Query query = entityManager.createQuery(sql, PostgraduateImpl.class);
        return query.getResultList();
    }

    @Override
    public void registerSeminar(int pid, int sid) {
        PostgraduateImpl postgraduate = entityManager.find(PostgraduateImpl.class, pid);
        SeminarImpl seminar = entityManager.find(SeminarImpl.class, sid);
        postgraduate.setSeminar(seminar);
        entityManager.merge(postgraduate);
        entityManager.flush();
    }

    @Override
    public void assign(int pid, int ssid) {
        PostgraduateImpl postgraduate = entityManager.find(PostgraduateImpl.class, pid);
        SeminarSubCommitteeImpl seminarSubCommittee = entityManager.find(SeminarSubCommitteeImpl.class, ssid);
        postgraduate.setSeminarSubCommittee(null);
        entityManager.merge(postgraduate);
        entityManager.flush();
        entityManager.refresh(postgraduate);

        postgraduate.setSeminarSubCommittee(seminarSubCommittee);
        entityManager.merge(postgraduate);
        entityManager.flush();
        entityManager.refresh(postgraduate);
        System.out.println(postgraduate.getSeminarSubCommittee() + " ssss " + postgraduate.getId());
    }

    @Override
    public void unregisterSeminar(int pid, int sid) {
        PostgraduateImpl postgraduate = entityManager.find(PostgraduateImpl.class, pid);
        postgraduate.setSeminar(null);
        postgraduate.setSeminarSubCommittee(null);
        entityManager.merge(postgraduate);
        entityManager.flush();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndYearAndStateAndKeyword(int majorId, int year, String state, String keyword) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE p.thesis.state = '" + state + "'"
                + " AND p.major.id = " + majorId
                + " AND p.year = " + year
                + " AND (p.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.state LIKE '%" + keyword + "%'"
                + " OR p.lecturer.name LIKE '%" + keyword + "%')"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajor(int majorId) {
        String sql = "select p from postgraduates p where p.major.id = " + majorId
                + " ORDER BY p.name";
        Query query = entityManager.createQuery(sql, PostgraduateImpl.class);
        return query.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByStateAndYear(String state, int year) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE p.year=" + year
                + " AND p.thesis.state = '" + state + "'"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByState(String state) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE p.thesis.state = '" + state + "'"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    @Override
    public Postgraduate edit(int id, String name, String email, String address,
                             String phone, int year, Date birthDate, int majorId) {
        PostgraduateImpl postgraduate = entityManager.find(PostgraduateImpl.class, id);
        postgraduate.setName(name);
        postgraduate.setEmail(email);
        postgraduate.setAddress(address);
        postgraduate.setPhoneNumber(phone);
        postgraduate.setYear(year);
        postgraduate.setDateOfBirth(birthDate);
        MajorImpl major = entityManager.find(MajorImpl.class, id);

        postgraduate.setMajor(major);
        entityManager.merge(postgraduate);
        entityManager.flush();
        entityManager.refresh(postgraduate);

        return postgraduate;
    }

    @Override
    public Postgraduate add(String name, String email, String address, String phone, int year, Date dateOfBirth) {
        PostgraduateImpl postgraduate = new PostgraduateImpl();
        postgraduate.setName(name);
        postgraduate.setEmail(email);
        postgraduate.setAddress(address);
        postgraduate.setPhoneNumber(phone);
        postgraduate.setYear(year);
        postgraduate.setDateOfBirth(dateOfBirth);
        ThesisImpl thesis = new ThesisImpl();
        thesis.setName("Chưa có tên đề tài");
        thesis.setState(ThesisState.NOT_STARTED);
        postgraduate.setThesis(thesis);
        UserImpl user = new UserImpl();
        user.setEmail(email);
        user.setPassword(email);
        user.setRole(Role.POSTGRADUATE);
        entityManager.persist(user);
        entityManager.flush();
        entityManager.refresh(user);
        postgraduate.setUserId(user.getId());
        entityManager.persist(postgraduate);
        entityManager.flush();
        entityManager.refresh(postgraduate);
        return postgraduate;
    }

    @Override
    public Postgraduate findPostgraduateByUserId(int uid) {
        String sql = "select p from postgraduates p where p.userId = " + uid;
        Query query = entityManager.createQuery(sql, PostgraduateImpl.class);
        List<Postgraduate> postgraduates = query.getResultList();
        if (postgraduates != null && postgraduates.size() != 0) {
            return postgraduates.get(0);
        }
        return null;
    }

    @Override
    public Postgraduate findPostgraduateById(int id) {
        return entityManager.find(PostgraduateImpl.class, id);
    }

    @Override
    public Postgraduate update(Postgraduate postgraduate) {
        entityManager.merge(postgraduate);
        entityManager.flush();
        return findPostgraduateById(postgraduate.getId());
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Postgraduate> getPostgraduateListByYear(int year) {
        String queryString = "SELECT p FROM postgraduates p WHERE p.year=?1 ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class).setParameter(1, year);
        return q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByLecturerId(int lecturerId) {
        String queryString = "SELECT p FROM postgraduates p WHERE p.lecturer.id =?1 ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class).setParameter(1, lecturerId);
        return q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByKeyword(String keyword) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE "
                + " (p.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.state LIKE '%" + keyword + "%'"
                + " OR p.lecturer.name LIKE '%" + keyword + "%')"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByKeywordAndYear(String keyword, int year) {
        String queryString = "SELECT p from postgraduates p"
                + " WHERE p.year=" + year
                + " AND (p.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.name LIKE '%" + keyword + "%'"
                + " OR p.thesis.state LIKE '%" + keyword + "%'"
                + " OR p.lecturer.name LIKE '%" + keyword + "%')"
                + " ORDER BY p.name";
        Query q = entityManager.createQuery(queryString, PostgraduateImpl.class);
        return  q.getResultList();
    }

    public void init() {
        System.out.println(this);
    }
}

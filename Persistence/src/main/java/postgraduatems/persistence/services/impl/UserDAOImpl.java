package postgraduatems.persistence.services.impl;

import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Thesis;
import postgraduatems.persistence.entities.User;
import postgraduatems.persistence.entities.impl.LecturerImpl;
import postgraduatems.persistence.entities.impl.PostgraduateImpl;
import postgraduatems.persistence.entities.impl.ThesisImpl;
import postgraduatems.persistence.entities.impl.UserImpl;
import postgraduatems.persistence.services.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by dotoan on 3/30/14.
 */
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    @Override
    public User findUserByEmail(String email) {
        String sql = "select u from users u where u.email = '" + email + "'";
        Query query = entityManager.createQuery(sql, UserImpl.class);
        List<User> users = query.getResultList();
        if (users != null && users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User findUserById(int id) {
        return entityManager.find(UserImpl.class, id);
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

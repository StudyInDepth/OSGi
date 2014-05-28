package postgraduatems.user.services.impl;

import postgraduatems.persistence.entities.User;
import postgraduatems.persistence.services.UserDAO;
import postgraduatems.user.services.UserBO;

/**
 * Created by dotoan on 3/30/14.
 */
public class UserBOImpl implements UserBO {

    private UserDAO dao;

    @Override
    public boolean login(String email, String password) {
        User user = findUserByEmail(email);
        if (user != null) {
           if ( user.getEmail().equals(email) && user.getPassword().equals(password)) {
               return true;
           }
        }
        return false;
    }

    @Override
    public User findUserById(int id) {
        return dao.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return dao.findUserByEmail(email);
    }

    public UserDAO getDao() {
        return dao;
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public void init() {
        System.out.println(this);
    }
}

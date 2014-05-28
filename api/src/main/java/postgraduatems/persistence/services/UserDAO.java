package postgraduatems.persistence.services;

import postgraduatems.persistence.entities.User;

/**
 * Created by dotoan on 3/30/14.
 */
public interface UserDAO {
    User findUserByEmail(String email);
    User findUserById(int id);
}

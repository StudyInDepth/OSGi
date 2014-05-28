package postgraduatems.user.services;

import postgraduatems.persistence.entities.User;

/**
 * Created by dotoan on 3/30/14.
 */
public interface UserBO {
    boolean login(String email, String password);
    User findUserByEmail(String email);
    User findUserById(int id);
}

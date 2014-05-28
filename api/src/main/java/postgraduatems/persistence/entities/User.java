package postgraduatems.persistence.entities;

/**
 * Created by dotoan on 3/30/14.
 */
public interface User {
    String getEmail();

    String getRole();

    String getPassword();

    int getId();
}

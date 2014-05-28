package postgraduatems.persistence.entities;

import java.util.List;

/**
 * Created by dotoan on 3/22/14.
 */
public interface Lecturer {
    String getName();

    String getDegree();

    String getPhoneNumber();

    String getEmail();

    String getAddress();

    int getId();

    int getUserId();

    Major getMajor();

    List<? extends Thesis> getTheses();
}

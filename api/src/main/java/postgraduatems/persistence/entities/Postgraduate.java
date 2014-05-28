package postgraduatems.persistence.entities;

import java.sql.Date;
import java.util.List;

/**
 * Created by dotoan on 3/22/14.
 */
public interface Postgraduate {

    int getId();

    String getName();

    int getAcademicYear();

    String getEmail();

    void setEmail(String email);

    String getPhoneNumber();

    void setPhoneNumber(String phone);

    String getAddress();

    void setAddress(String address);

    Thesis getThesis();

    Lecturer getLecturer();

    void setLecturer(Lecturer lecturer);

    void setThesisState(String state);

    int getUserId();
    Seminar getSeminar();
    SeminarSubCommittee getSeminarSubCommittee();

    public Date getDateOfBirth();

    Major getMajor();
}

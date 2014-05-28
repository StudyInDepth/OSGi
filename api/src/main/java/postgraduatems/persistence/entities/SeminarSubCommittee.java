package postgraduatems.persistence.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by dotoan on 5/6/14.
 */
public interface SeminarSubCommittee {
    int getId();

    String getName();

    Date getHeldDate();

    Time getStaringTime();

    String getPlace();

    List<? extends Lecturer> getLecturers();

    Lecturer getPresident();

    Lecturer getVicePresident();

    Lecturer getSecretary();

    List<? extends Postgraduate> getPostgraduates();

    Seminar getSeminar();


}

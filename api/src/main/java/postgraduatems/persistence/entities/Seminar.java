package postgraduatems.persistence.entities;

import java.sql.Date;
import java.util.List;

/**
 * Created by dotoan on 4/30/14.
 */
public interface Seminar {
    int getId();

    String getTitle();

    String getDescription();

    Date getScheduledDate();

    Date getStartRegisteringDate();

    Date getDueRegisteringDate();

    List<? extends Postgraduate> getPostgraduates();
    List<? extends SeminarSubCommittee> getSeminarSubCommittees();
}

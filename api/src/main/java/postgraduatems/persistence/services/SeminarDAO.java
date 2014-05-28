package postgraduatems.persistence.services;

import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.SeminarSubCommittee;

import java.sql.Date;
import java.util.List;

/**
 * Created by dotoan on 4/30/14.
 */
public interface SeminarDAO {
    Seminar save(String title, String description, Date scheduledDate,
                        Date startRegDate, Date dueRegDate);
    List<Seminar> findAllSeminars();
    Seminar findSeminarById(int id);
    Seminar update(int id, String title, String description, Date scheduledDate,
                   Date startRegDate, Date dueRegDate);
    Seminar findNewestSeminar();
    Seminar delete(int id);

}

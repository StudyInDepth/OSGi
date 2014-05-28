package postgraduatems.seminar.services;

import postgraduatems.persistence.entities.Seminar;

import java.sql.Date;
import java.util.List;

/**
 * Created by dotoan on 5/3/14.
 *
 */
public interface SeminarBO {
    Seminar openSeminar(String title, String description, Date scheduledDate,
                        Date startRegDate, Date dueRegDate);

    List<Seminar> findAllSeminars();
    Seminar findSeminarById(int id);
    Seminar update(int id, String title, String description, Date scheduledDate,
                        Date startRegDate, Date dueRegDate);
    Seminar findNewestSeminar();
}

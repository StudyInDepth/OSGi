package postgraduatems.postgraduate.services;

import postgraduatems.persistence.entities.Seminar;

import java.util.List;

/**
 * Created by dotoan on 5/2/14.
 */
public interface SeminarRegistrationBO {
    void register(int postgraduateId, int seminarId);
    void unregister(int postgraduateId, int seminarId);
}

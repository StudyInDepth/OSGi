package postgraduatems.lecturer.services;

import postgraduatems.persistence.entities.Lecturer;

import java.util.List;

/**
 * Created by toandv on 3/29/14.
 */
public interface LecturerBO {
    List<Lecturer> getLecturerList();
    Lecturer findLecturerByEmail(String email);
    Lecturer findLecturerById(int id);
    Lecturer findLecturerByUserId(int id);
    List<Lecturer> getLecturerList(String keyword);
}

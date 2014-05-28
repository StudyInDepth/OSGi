package postgraduatems.persistence.services;

import postgraduatems.persistence.entities.Lecturer;

import java.util.List;

/**
 * Created by dotoan on 3/23/14.
 */
public interface LecturerDAO {
    List<Lecturer> getLecturerList();
    List<Lecturer> getLecturerList(String keyword);
    Lecturer findLecturerByEmail(String email);
    Lecturer findLecturerById(int id);
    Lecturer findLecturerByUserId(int uid);
}

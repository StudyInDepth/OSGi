package postgraduatems.persistence.services;

import postgraduatems.persistence.entities.Major;
import postgraduatems.persistence.entities.Postgraduate;

import java.sql.Date;
import java.util.List;

/**
 * Created by dotoan on 3/22/14.
 */
public interface PostgraduateDAO {
    Postgraduate update(Postgraduate postgraduate);

    List<Postgraduate> getPostgraduateList();

    List<Postgraduate> getPostgraduateListByYear(int year);

    List<Postgraduate> getPostgraduateListByKeywordAndYear(String keyword, int year);

    List<Postgraduate> getPostgraduateListByKeyword(String keyword);

    List<Postgraduate> getPostgraduateListByLecturerId(int lecturerId);

    List<Integer> getYears();
    List<Major> getMajors();

    Postgraduate findPostgraduateByEmail(String email);

    Postgraduate findPostgraduateById(int id);

    Postgraduate findPostgraduateByUserId(int uid);

    Postgraduate edit(int id, String name, String email, String address, String phone, int year, Date birthDate, int majorId);

    Postgraduate add(String name, String email, String address, String phone, int year, Date dateOfBirth);

    List<Postgraduate> getPostgraduateListByKeywordAndYearAndState(String keyword, int year, String state);

    List<Postgraduate> getPostgraduateListByKeywordAndState(String keyword, String state);

    List<Postgraduate> getPostgraduateListByState(String state);

    List<Postgraduate> getPostgraduateListByStateAndYear(String state, int year);

    List<Postgraduate> getPostgraduateListByMajor(int majorId);

    List<Postgraduate> getPostgraduateListByMajorAndYear(int majorId, int year);

    List<Postgraduate> getPostgraduateListByMajorAndState(int majorId, String state);

    List<Postgraduate> getPostgraduateListByMajorAndKeyword(int majorId, String keyword);

    List<Postgraduate> getPostgraduateListByMajorAndKeywordAndYear(int majorId, String keyword, int year);

    List<Postgraduate> getPostgraduateListByMajorAndKeywordAndState(int majorId, String keyword, String state);

    List<Postgraduate> getPostgraduateListByMajorAndYearAndState(int majorId, int year, String state);

    List<Postgraduate> getPostgraduateListByMajorAndYearAndStateAndKeyword(int majorId, int year, String state, String keyword);

    void registerSeminar(int pid, int sid);
    void unregisterSeminar(int pid, int sid);
    void assign(int pid, int ssid);

}

package postgraduatems.postgraduate.services;

import postgraduatems.persistence.entities.Major;
import postgraduatems.persistence.entities.Postgraduate;

import java.util.List;

/**
 * Created by dotoan on 3/23/14.
 */
public interface PostgraduateBO {
    Postgraduate findPostgraduateByEmail(String email);
    Postgraduate findPostgraduateById(int id);
    Postgraduate findPostgraduateByUserId(int id);
    List<Postgraduate> getPostgraduateList();
    List<Postgraduate> getPostgraduateListByYear(int year);
    List<Postgraduate> getPostgraduateListByKeywordAndYear(String keyword, int year);
    List<Postgraduate> getPostgraduateListByKeywordAndYearAndState(String keyword, int year, String state);
    List<Postgraduate> getPostgraduateListByKeywordAndState(String keyword, String state);
    List<Postgraduate> getPostgraduateListByStateAndYear(String state, int year);
    List<Postgraduate> getPostgraduateListByState(String state);
    List<Postgraduate> getPostgraduateListByKeyword(String keyword);
    List<Postgraduate> getPostgraduateListByLecturerId(int lecturerId);
    List<Integer> getYears();
    List<Major> geMajors();
    List<Postgraduate> getPostgraduateListByMajor(int majorId);

    List<Postgraduate> getPostgraduateListByMajorAndYear(int majorId, int year);

    List<Postgraduate> getPostgraduateListByMajorAndState(int majorId, String state);

    List<Postgraduate> getPostgraduateListByMajorAndKeyword(int majorId, String keyword);

    List<Postgraduate> getPostgraduateListByMajorAndKeywordAndYear(int majorId, String keyword, int year);

    List<Postgraduate> getPostgraduateListByMajorAndKeywordAndState(int majorId, String keyword, String state);

    List<Postgraduate> getPostgraduateListByMajorAndYearAndState(int majorId, int year, String state);

    List<Postgraduate> getPostgraduateListByMajorAndYearAndStateAndKeyword(int majorId, int year, String state, String keyword);


}

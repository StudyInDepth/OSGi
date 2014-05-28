package postgraduatems.postgraduate.services.impl;

import postgraduatems.persistence.entities.Major;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.services.PostgraduateDAO;
import postgraduatems.postgraduate.services.PostgraduateBO;

import java.util.List;

/**
 * Created by dotoan on 3/23/14.
 */
public class PostgraduateBOImpl implements  PostgraduateBO {
    private PostgraduateDAO dao;

    @Override
    public Postgraduate findPostgraduateByUserId(int id) {
        return dao.findPostgraduateByUserId(id);
    }

    @Override
    public Postgraduate findPostgraduateById(int id) {
        return dao.findPostgraduateById(id);
    }

    @Override
    public Postgraduate findPostgraduateByEmail(String email) {
         return dao.findPostgraduateByEmail(email);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByStateAndYear(String state, int year) {
        return dao.getPostgraduateListByStateAndYear(state, year);
    }

    @Override
    public List<Postgraduate> getPostgraduateList() {
        return  dao.getPostgraduateList();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByYear(int year) {
        return dao.getPostgraduateListByYear(year);
    }

    public PostgraduateDAO getDao() {
        return dao;
    }

    public void setDao(PostgraduateDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Postgraduate> getPostgraduateListByLecturerId(int lecturerId) {
        return dao.getPostgraduateListByLecturerId(lecturerId);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByKeyword(String keyword) {
        return dao.getPostgraduateListByKeyword(keyword);
    }


    @Override
    public List<Postgraduate> getPostgraduateListByMajor(int majorId) {
        return dao.getPostgraduateListByMajor(majorId);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndYear(int majorId, int year) {
        return dao.getPostgraduateListByMajorAndYear(majorId, year);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndState(int majorId, String state) {
        return dao.getPostgraduateListByMajorAndState(majorId, state);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndKeyword(int majorId, String keyword) {
        return dao.getPostgraduateListByMajorAndKeyword(majorId, keyword);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndKeywordAndYear(int majorId, String keyword, int year) {
        return dao.getPostgraduateListByMajorAndKeywordAndYear(majorId, keyword, year);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndKeywordAndState(int majorId, String keyword, String state) {
        return dao.getPostgraduateListByMajorAndKeywordAndState(majorId, keyword, state);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndYearAndState(int majorId, int year, String state) {
        return dao.getPostgraduateListByMajorAndYearAndState(majorId, year, state);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByMajorAndYearAndStateAndKeyword(
            int majorId, int year, String state, String keyword) {
        return dao.getPostgraduateListByMajorAndYearAndStateAndKeyword(majorId, year, state, keyword);
    }

    @Override
    public List<Major> geMajors() {
        return dao.getMajors();
    }

    @Override
    public List<Integer> getYears() {
        return dao.getYears();
    }

    @Override
    public List<Postgraduate> getPostgraduateListByKeywordAndYearAndState(String keyword, int year, String state) {
        return dao.getPostgraduateListByKeywordAndYearAndState(keyword, year, state);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByKeywordAndState(String keyword, String state) {
        return dao.getPostgraduateListByKeywordAndState(keyword, state);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByState(String state) {
        return dao.getPostgraduateListByState(state);
    }

    @Override
    public List<Postgraduate> getPostgraduateListByKeywordAndYear(String keyword, int year) {
        return dao.getPostgraduateListByKeywordAndYear(keyword, year);
    }

    public void init() {
        System.out.println(this);
    }
}

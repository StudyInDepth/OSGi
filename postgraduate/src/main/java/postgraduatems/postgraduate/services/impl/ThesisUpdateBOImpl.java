package postgraduatems.postgraduate.services.impl;

import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Thesis;
import postgraduatems.persistence.entities.ThesisState;
import postgraduatems.persistence.services.LecturerDAO;
import postgraduatems.persistence.services.PostgraduateDAO;
import postgraduatems.postgraduate.services.ThesisUpdateBO;

/**
 * Created by dotoan on 4/16/14.
 */
public class ThesisUpdateBOImpl implements ThesisUpdateBO {
    PostgraduateDAO postgraduateDAO;
    LecturerDAO lecturerDAO;

    public Postgraduate updateThesis(int postgraduateId, String thesisName, int lecturerId) {
        Postgraduate postgraduate = postgraduateDAO.findPostgraduateById(postgraduateId);
        Lecturer lecturer = lecturerDAO.findLecturerById(lecturerId);
        Thesis thesis = postgraduate.getThesis();
        if (thesis.getState().equals(ThesisState.NOT_STARTED)
                || thesis.getState().equals(ThesisState.BEING_PREPARED)) {
            postgraduate.setLecturer(lecturer);
            thesis.setName(thesisName);
            thesis.setState(ThesisState.BEING_PREPARED);
            return postgraduateDAO.update(postgraduate);
        } else {
          return postgraduate;
        }
    }

    public PostgraduateDAO getPostgraduateDAO() {
        return postgraduateDAO;
    }

    public void setPostgraduateDAO(PostgraduateDAO postgraduateDAO) {
        this.postgraduateDAO = postgraduateDAO;
    }

    public LecturerDAO getLecturerDAO() {
        return lecturerDAO;
    }

    public void setLecturerDAO(LecturerDAO lecturerDAO) {
        this.lecturerDAO = lecturerDAO;
    }

    public void init() {
        System.out.println(this);
    }

    public static void main(String[] args) {

    }
}

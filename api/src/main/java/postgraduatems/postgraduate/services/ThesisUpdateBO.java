package postgraduatems.postgraduate.services;

import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;

/**
 * Created by dotoan on 4/15/14.
 */
public interface ThesisUpdateBO {
    Postgraduate updateThesis(int postgraduateId, String thesisName, int lecturerId);
}

package postgraduatems.persistence.services;

import postgraduatems.persistence.entities.Thesis;

import java.util.List;

/**
 * Created by dotoan on 4/27/14.
 */
public interface ThesisDAO {
    void confirm(int id);
    void accept(int id);
    void verifyDescription(int id);
    void uploadDescription(int id);
    Thesis update(Thesis thesis);
    Thesis findThesisById(int id);
    List<String> getStates();

    void commend(int id, String comment);

    Thesis setReviewers(int tid, int[] reviewerIds);

    List<Thesis> getReviewersById(int id);


}

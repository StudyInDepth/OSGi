package postgraduatems.thesis.services;

import postgraduatems.persistence.entities.Thesis;

import java.util.List;

/**
 * Created by dotoan on 4/27/14.
 */
public interface ThesisBO {
    void confirm(int id);
    Thesis update(Thesis thesis);
    void accept(int[] ids);

    void uploadDescription(int id);
    void verifyDescription(int[] ids);
    List<String> getStates();
    void commend(int id, String comment);

    Thesis getThesisById(int id);

    Thesis setReviewers(int tid, int[] reviewerIds);

    List<Thesis> getReviewersById(int id);
}

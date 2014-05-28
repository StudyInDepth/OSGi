package postgraduatems.persistence.entities;

import java.util.List;

/**
 * Created by dotoan on 3/22/14.
 */
public interface Thesis {
    int getId();

    String getName();

    void setName(String name);

    String getState();

    void setState(String state);

    Postgraduate getPostgraduate();

    String getComment();

    void setComment(String comment);

    boolean isFileUploaded();

    List<? extends Lecturer> getReviewers();

}

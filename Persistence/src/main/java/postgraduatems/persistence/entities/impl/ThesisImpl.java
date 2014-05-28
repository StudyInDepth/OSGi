package postgraduatems.persistence.entities.impl;

import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Thesis;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dotoan on 3/22/14.
 */

@Entity(name="theses")
public class ThesisImpl implements Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String state;
    private String comment;

    @OneToOne(mappedBy = "thesis", fetch = FetchType.EAGER)
    private PostgraduateImpl postgraduate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="theses_lecturers",
        joinColumns = @JoinColumn(name = "thesis_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="lecturer_id", referencedColumnName = "id")
    )
    private List<LecturerImpl> reviewers;

    @Override
    public List<LecturerImpl> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<LecturerImpl> reviewers) {
        this.reviewers = reviewers;
    }

    @Override
    public boolean isFileUploaded() {
        return fileUploaded;
    }

    public void setFileUploaded(boolean fileUploaded) {
        this.fileUploaded = fileUploaded;
    }

    public void addReviewer(LecturerImpl lecturer) {
        reviewers.add(lecturer);
    }

    @Override
    public PostgraduateImpl getPostgraduate() {
        return postgraduate;
    }

    public void setPostgraduate(PostgraduateImpl postgraduate) {
        this.postgraduate = postgraduate;
    }

    @Column(name="file_uploaded", columnDefinition = "tinyint(1)")
    private boolean fileUploaded;

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getState() {
        return state;
    }


}

package postgraduatems.persistence.entities.impl;

/**
 * Created by dotoan on 4/30/14.
 */

import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.SeminarSubCommittee;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name="seminars")
public class SeminarImpl implements Seminar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name="scheduled_date")
    private Date scheduledDate;

    @Temporal(TemporalType.DATE)
    @Column(name="start_reg_date")
    private Date startRegisteringDate;

    @Temporal(TemporalType.DATE)
    @Column(name="due_reg_date")
    private Date dueRegisteringDate;

    @OneToMany(mappedBy = "seminar", fetch = FetchType.EAGER)
    @OrderBy("name")
    private List<PostgraduateImpl> postgraduates;

    @OneToMany(mappedBy = "seminar", fetch = FetchType.EAGER)
    @OrderBy("name")
    private List<SeminarSubCommitteeImpl> seminarSubCommittees;

    public SeminarImpl() {}

    public List<SeminarSubCommitteeImpl> getSeminarSubCommittees() {
        return seminarSubCommittees;
    }

    public void setSeminarSubCommittees(List<SeminarSubCommitteeImpl> seminarSubCommittees) {
        this.seminarSubCommittees = seminarSubCommittees;
    }

    public SeminarImpl(String title, String description, Date scheduledDate,
                       Date startRegisteringDate, Date dueRegisteringDate) {
        this.title = title;
        this.description = description;
        this.scheduledDate = scheduledDate;
        this.startRegisteringDate = startRegisteringDate;
        this.dueRegisteringDate = dueRegisteringDate;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((SeminarImpl) obj).getId());
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    @Override
    public Date getStartRegisteringDate() {
        return startRegisteringDate;
    }

    public void setStartRegisteringDate(Date startRegisteringDate) {
        this.startRegisteringDate = startRegisteringDate;
    }

    @Override
    public Date getDueRegisteringDate() {
        return dueRegisteringDate;
    }

    @Override
    public List<PostgraduateImpl> getPostgraduates() {
        return postgraduates;
    }

    public void setPostgraduates(List<PostgraduateImpl> postgraduates) {
        this.postgraduates = postgraduates;
    }

    public void setDueRegisteringDate(Date dueRegisteringDate) {
        this.dueRegisteringDate = dueRegisteringDate;
    }
}

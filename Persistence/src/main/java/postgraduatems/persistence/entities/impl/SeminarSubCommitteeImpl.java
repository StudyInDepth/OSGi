package postgraduatems.persistence.entities.impl;

import postgraduatems.persistence.entities.SeminarSubCommittee;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by dotoan on 5/6/14.
 */
@Entity(name = "seminar_subcommittees")
public class SeminarSubCommitteeImpl implements SeminarSubCommittee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "held_date")
    private Date heldDate;

    @Column(name = "staring_time")
    private Time staringTime;

    private String place;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "seminar_subcommittees_lecturers",
            joinColumns = @JoinColumn(name = "ssc_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lid", referencedColumnName = "id"))
    private List<LecturerImpl> lecturers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "seminarSubCommittee")
    private List<PostgraduateImpl> postgraduates;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seminar_id")
    private SeminarImpl seminar;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "president_id")
    private LecturerImpl president;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vice_president_id")
    private LecturerImpl vicePresident;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secretary_id")
    private LecturerImpl secretary;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getHeldDate() {
        return heldDate;
    }

    public void setHeldDate(Date heldDate) {
        this.heldDate = heldDate;
    }

    @Override
    public Time getStaringTime() {
        return staringTime;
    }

    public void setStaringTime(Time staringTime) {
        this.staringTime = staringTime;
    }

    @Override
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public List<LecturerImpl> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<LecturerImpl> lecturers) {
        this.lecturers = lecturers;
    }

    @Override
    public List<PostgraduateImpl> getPostgraduates() {
        return postgraduates;
    }

    public void setPostgraduates(List<PostgraduateImpl> postgraduates) {
        this.postgraduates = postgraduates;
    }

    @Override
    public SeminarImpl getSeminar() {
        return seminar;
    }

    public void setSeminar(SeminarImpl seminar) {
        this.seminar = seminar;
    }

    @Override
    public LecturerImpl getPresident() {
        return president;
    }

    public void setPresident(LecturerImpl president) {
        this.president = president;
    }

    @Override
    public LecturerImpl getVicePresident() {
        return vicePresident;
    }

    public void setVicePresident(LecturerImpl vicePresident) {
        this.vicePresident = vicePresident;
    }

    @Override
    public LecturerImpl getSecretary() {
        return secretary;
    }

    public void setSecretary(LecturerImpl secretary) {
        this.secretary = secretary;
    }

    public void addLecturer(LecturerImpl lecturer) {
        lecturers.add(lecturer);
    }

    public void removeLecturer(int index) {
        try {
            lecturers.remove(index);
        } catch (IndexOutOfBoundsException  e) {
            e.printStackTrace();
        }
    }


    public void addPostgraduate(PostgraduateImpl postgraduate) {
        postgraduates.add(postgraduate);
    }

    public void removePostgraduate(int index) {
        try {
            postgraduates.remove(index);
        } catch (IndexOutOfBoundsException  e) {
            e.printStackTrace();
        }
    }
}

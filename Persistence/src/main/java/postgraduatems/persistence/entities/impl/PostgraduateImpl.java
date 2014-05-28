package postgraduatems.persistence.entities.impl;

import postgraduatems.persistence.entities.Lecturer;
import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.entities.Seminar;
import postgraduatems.persistence.entities.SeminarSubCommittee;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by dotoan on 3/22/14.
 */
@Entity(name="postgraduates")
public class PostgraduateImpl implements Postgraduate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int year;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "date_of_birth")
    private Date dateOfBirth;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id")
    private LecturerImpl lecturer;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thesis_id")
    private ThesisImpl thesis;
    @Column(nullable = false, name = "user_id", unique = true)
    private int userId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seminar_id")
    private SeminarImpl seminar;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id")
    private MajorImpl major;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seminar_subcommittee_id")
    private SeminarSubCommitteeImpl seminarSubCommittee;


    @Override
    public SeminarImpl getSeminar() {
        return seminar;
    }

    public void setSeminar(SeminarImpl seminar) {
        this.seminar = seminar;
    }

    @Override
    public SeminarSubCommitteeImpl getSeminarSubCommittee() {
        return seminarSubCommittee;
    }

    public void setSeminarSubCommittee(SeminarSubCommitteeImpl seminarSubCommittee) {
        this.seminarSubCommittee = seminarSubCommittee;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAcademicYear() {
        return year;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public ThesisImpl getThesis() {
        return thesis;
    }

    public void setThesis(ThesisImpl thesis) {
        this.thesis = thesis;
    }

    @Override
    public LecturerImpl getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerImpl lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public void setLecturer(Lecturer lecturer) {
        LecturerImpl lecturer1 = (LecturerImpl) lecturer;
        this.lecturer = lecturer1;
    }

    @Override
    public void setThesisState(String state) {
        this.getThesis().setState(state);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public MajorImpl getMajor() {
        return major;
    }

    public void setMajor(MajorImpl major) {
        this.major = major;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}

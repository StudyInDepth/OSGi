package postgraduatems.persistence.entities.impl;

import postgraduatems.persistence.entities.Lecturer;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dotoan on 3/22/14.
 */
@Entity(name = "lecturers")
public class LecturerImpl implements Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String degree;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id")
    private MajorImpl major;

    @ManyToMany(mappedBy = "reviewers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ThesisImpl> theses;

    @Override
    public List<ThesisImpl> getTheses() {
        return theses;
    }

    public void setTheses(List<ThesisImpl> theses) {
        this.theses = theses;
    }

    @Override
    public MajorImpl getMajor() {
        return major;
    }

    public void setMajor(MajorImpl major) {
        this.major = major;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(nullable = false, name = "user_id", unique = true)
    private int userId;

    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

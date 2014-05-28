package postgraduatems.persistence.entities.impl;

import postgraduatems.persistence.entities.DepartmentStaff;

import javax.persistence.*;

/**
 * Created by dotoan on 5/14/14.
 */
@Entity(name = "department_staff")
public class DepartmentStaffImpl implements DepartmentStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id", unique = true)
    private int userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id")
    private MajorImpl major;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public MajorImpl getMajor() {
        return major;
    }

    public void setMajor(MajorImpl major) {
        this.major = major;
    }
}

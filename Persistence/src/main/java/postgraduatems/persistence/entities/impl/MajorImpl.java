package postgraduatems.persistence.entities.impl;

import postgraduatems.persistence.entities.Major;

import javax.persistence.*;

/**
 * Created by dotoan on 5/12/14.
 */
@Entity(name = "majors")
public class MajorImpl implements Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

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
}

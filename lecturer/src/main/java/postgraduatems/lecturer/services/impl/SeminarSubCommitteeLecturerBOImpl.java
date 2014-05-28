package postgraduatems.lecturer.services.impl;

import postgraduatems.lecturer.services.SeminarSubCommitteeLecturerBO;
import postgraduatems.persistence.entities.SeminarSubCommittee;
import postgraduatems.persistence.services.SeminarSubCommitteeDAO;

/**
 * Created by dotoan on 5/22/14.
 */
public class SeminarSubCommitteeLecturerBOImpl implements SeminarSubCommitteeLecturerBO {

    SeminarSubCommitteeDAO dao;
    @Override
    public SeminarSubCommittee findLecturerSeminarSubCommittee(int id) {
        return dao.findLecturerSeminarSubCommittee(id);
    }

    public SeminarSubCommitteeDAO getDao() {
        return dao;
    }

    public void setDao(SeminarSubCommitteeDAO dao) {
        this.dao = dao;
    }

    public void init() {
        System.out.println(this);
    }
}

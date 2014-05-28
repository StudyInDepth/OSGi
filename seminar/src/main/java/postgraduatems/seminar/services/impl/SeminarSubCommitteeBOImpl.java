package postgraduatems.seminar.services.impl;

import postgraduatems.persistence.entities.SeminarSubCommittee;
import postgraduatems.persistence.services.SeminarSubCommitteeDAO;
import postgraduatems.seminar.services.SeminarSubCommitteeBO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by dotoan on 5/6/14.
 */
public class SeminarSubCommitteeBOImpl implements SeminarSubCommitteeBO {
    SeminarSubCommitteeDAO dao;

    @Override
    public List<SeminarSubCommittee> getNewestSeminarSubCommittees() {
        return dao.getNewestSeminarSubCommittees();
    }

    @Override
    public SeminarSubCommittee findSeminarSubCommitteeById(int id) {
        return dao.findSeminarSubCommitteeById(id);
    }

    @Override
    public SeminarSubCommittee save(String name, String place, Date heldDate, Time staringTime,
                                    int seminarId, int presidentId, int secretaryId, int vicePresidentId,
                                    int[] lecturerIds) {
        return dao.save(name, place, heldDate, staringTime,
                seminarId, presidentId, secretaryId, vicePresidentId,
                lecturerIds);
    }

    public SeminarSubCommittee update(int id, String name, String place, Date heldDate, Time staringTime,
                                      int seminarId, int presidentId, int secretaryId, int vicePresidentId,
                                      int[] lecturerIds, int[] postgraduateIds) {
        return dao.update(id, name, place, heldDate, staringTime,
                seminarId, presidentId, secretaryId, vicePresidentId,
                lecturerIds);
    }
    public SeminarSubCommitteeDAO getDao() {
        return dao;
    }

    public void setDao(SeminarSubCommitteeDAO dao) {
        this.dao = dao;
    }

    public void init() {
        System.out.println(this);
        //int[] lids = {1, 2};
       // int[] pids = {1, 2};
        //this.update(1,"YOY", "TOT", Date.valueOf("2014-12-20"), Time.valueOf("08:50:50"), 1, 1, 1, 1, null, null);
    }
}

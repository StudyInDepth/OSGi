package postgraduatems.seminar.services.impl;

import postgraduatems.persistence.services.PostgraduateDAO;
import postgraduatems.seminar.services.SeminarSubcommitteeAssigningBO;

/**
 * Created by dotoan on 5/18/14.
 */
public class SeminarSubcommitteeAssigningBOImpl implements SeminarSubcommitteeAssigningBO {
    PostgraduateDAO dao;

    public PostgraduateDAO getDao() {
        return dao;
    }

    public void setDao(PostgraduateDAO dao) {
        this.dao = dao;
    }

    @Override
    public void assign(int pid, int ssid) {
        dao.assign(pid, ssid);
    }

    public void init() {
        System.out.println(this);

    }
}

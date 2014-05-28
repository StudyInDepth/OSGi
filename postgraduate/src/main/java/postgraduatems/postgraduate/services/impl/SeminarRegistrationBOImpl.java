package postgraduatems.postgraduate.services.impl;


import postgraduatems.persistence.services.PostgraduateDAO;
import postgraduatems.postgraduate.services.SeminarRegistrationBO;

/**
 * Created by dotoan on 5/3/14.
 */
public class SeminarRegistrationBOImpl implements SeminarRegistrationBO {
    PostgraduateDAO dao;

    @Override
    public void register(int postgraduateId, int seminarId) {
        dao.registerSeminar(postgraduateId, seminarId);
    }

    @Override
    public void unregister(int postgraduateId, int seminarId) {
        dao.unregisterSeminar(postgraduateId, seminarId);
    }

    public PostgraduateDAO getDao() {
        return dao;
    }

    public void setDao(PostgraduateDAO dao) {
        this.dao = dao;
    }
    public void init() {
        System.out.println(this);
    }
}

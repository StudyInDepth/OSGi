package postgraduatems.postgraduate.services.impl;

import postgraduatems.persistence.entities.Postgraduate;
import postgraduatems.persistence.services.PostgraduateDAO;
import postgraduatems.postgraduate.services.EditPostgraduateBO;

import java.sql.Date;

/**
 * Created by dotoan on 4/20/14.
 */
public class EditPostgraduateBOImpl implements EditPostgraduateBO {
    PostgraduateDAO dao;

    @Override
    public Postgraduate edit(int id, String name, String email, String address, String phone, int year, Date dateOfBirth) {
        return null;
    }

    @Override
    public Postgraduate add(String name, String email, String address, String phone, int year, Date dateOfBirth) {
        return dao.add(name, email, address, phone, year, dateOfBirth);
    }

    @Override
    public Postgraduate update(Postgraduate postgraduate) {

        return dao.update(postgraduate);
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

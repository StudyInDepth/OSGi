package postgraduatems.postgraduate.services;

import postgraduatems.persistence.entities.Postgraduate;

import java.sql.Date;

/**
 * Created by dotoan on 4/17/14.
 */
public interface EditPostgraduateBO {
    Postgraduate edit(int id, String name, String email, String address, String phone, int year, Date dateOfBirth);
    Postgraduate add(String name, String email, String address, String phone, int year, Date dateOfBirth);
    Postgraduate update(Postgraduate postgraduate);
}

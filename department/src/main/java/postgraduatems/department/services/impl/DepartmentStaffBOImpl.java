package postgraduatems.department.services.impl;

import postgraduatems.department.services.DepartmentStaffBO;
import postgraduatems.persistence.entities.DepartmentStaff;
import postgraduatems.persistence.services.DepartmentStaffDAO;

/**
 * Created by dotoan on 5/14/14.
 */
public class DepartmentStaffBOImpl implements DepartmentStaffBO {
    DepartmentStaffDAO dao;
    @Override
    public DepartmentStaff findDepartmentStaffByUserId(int userId) {
        return dao.findDepartmentStaffByUserId(userId);
    }

    @Override
    public DepartmentStaff findDepartmentStaffById(int id) {
        return dao.findDepartmentStaffById(id);
    }

    public DepartmentStaffDAO getDao() {
        return dao;
    }

    public void setDao(DepartmentStaffDAO dao) {
        this.dao = dao;
    }

    public void init() {
        System.out.println(this);
    }
}

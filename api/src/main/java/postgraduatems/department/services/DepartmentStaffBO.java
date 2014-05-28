package postgraduatems.department.services;

import postgraduatems.persistence.entities.DepartmentStaff;

/**
 * Created by dotoan on 5/14/14.
 */
public interface DepartmentStaffBO {
    DepartmentStaff findDepartmentStaffByUserId(int userId);
    DepartmentStaff findDepartmentStaffById(int id);
}

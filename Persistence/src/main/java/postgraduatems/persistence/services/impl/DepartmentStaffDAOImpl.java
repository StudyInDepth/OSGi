package postgraduatems.persistence.services.impl;

import postgraduatems.persistence.entities.DepartmentStaff;
import postgraduatems.persistence.entities.impl.DepartmentStaffImpl;
import postgraduatems.persistence.services.DepartmentStaffDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by dotoan on 5/14/14.
 */
public class DepartmentStaffDAOImpl implements DepartmentStaffDAO {
    EntityManager entityManager;
    @Override
    public DepartmentStaff findDepartmentStaffByUserId(int userId) {
        String sql = "SELECT d from department_staff d WHERE d.userId =" + userId;
        Query query = entityManager.createQuery(sql, DepartmentStaffImpl.class);
        List<DepartmentStaffImpl> departmentStaffList = query.getResultList();
        if (departmentStaffList.size() > 0) {
            return departmentStaffList.get(0);
        }
        return null;
    }

    @Override
    public DepartmentStaff findDepartmentStaffById(int id) {
        return entityManager.find(DepartmentStaffImpl.class, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void init() {
        System.out.println(this);
    }
}

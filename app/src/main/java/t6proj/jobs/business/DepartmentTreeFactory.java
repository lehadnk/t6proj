package t6proj.jobs.business;

import org.springframework.stereotype.Component;
import t6proj.jobs.dto.DepartmentTreeNode;
import t6proj.jobs.persistence.dao.DepartmentDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class DepartmentTreeFactory {
    private final DepartmentDao departmentDao;

    public DepartmentTreeFactory(
        DepartmentDao departmentDao
    ) {
        this.departmentDao = departmentDao;
    }

    public List<DepartmentTreeNode> build() {
        var departments = this.departmentDao.getAllDepartments();

        var departmentTreeNodes = new HashMap<Integer, DepartmentTreeNode>();
        for(var department : departments) {
            var departmentTreeNode = new DepartmentTreeNode();
            departmentTreeNode.id = department.id;
            departmentTreeNode.parentId = department.parentDepartmentId;
            departmentTreeNode.title = department.title;

            departmentTreeNodes.put(department.id, departmentTreeNode);
        }

        for(var department : departments) {
            if (department.parentDepartmentId != null) {
                if (departmentTreeNodes.get(department.parentDepartmentId).children == null) {
                    departmentTreeNodes.get(department.parentDepartmentId).children = new ArrayList<>();
                }

                departmentTreeNodes.get(department.parentDepartmentId).children.add(
                        departmentTreeNodes.get(department.id)
                );
            }
        }

        return departmentTreeNodes.values().stream().filter((dep) -> dep.parentId == null).toList();
    }
}

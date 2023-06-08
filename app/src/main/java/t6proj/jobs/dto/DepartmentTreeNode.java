package t6proj.jobs.dto;

import java.util.List;

public class DepartmentTreeNode {
    public Integer id;
    public Integer parentId;
    public String title;
    public List<DepartmentTreeNode> children;
}

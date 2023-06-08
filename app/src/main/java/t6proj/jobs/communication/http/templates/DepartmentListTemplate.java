package t6proj.jobs.communication.http.templates;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;
import t6proj.jobs.dto.DepartmentTreeNode;

import java.util.List;

public class DepartmentListTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    public String templatePath = "jobs/communication/http/templates/html/department-list.html";

    public DepartmentListTemplate(
            AuthorizedAdminLayout layout,
            List<DepartmentTreeNode> departmentTreeNodes
    ) {
        super(layout);
        this.context.setVariable("departmentTreeNodes", departmentTreeNodes);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}

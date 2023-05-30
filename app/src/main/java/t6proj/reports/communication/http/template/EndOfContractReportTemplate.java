package t6proj.reports.communication.http.template;

import adminlte.html_controller.communication.http.layout.authorized_admin.AbstractAuthorizedAdminLayoutTemplate;
import adminlte.html_controller.communication.http.layout.authorized_admin.AuthorizedAdminLayout;

public class EndOfContractReportTemplate extends AbstractAuthorizedAdminLayoutTemplate {
    private String templatePath = "reports/communication/http/template/html/end_of_contract_report.html";

    public EndOfContractReportTemplate(
            AuthorizedAdminLayout layout,
            String tableContents
    ) {
        super(layout);
        this.context.setVariable("tableContents", tableContents);
    }

    @Override
    public String getTemplatePath() {
        return this.templatePath;
    }
}

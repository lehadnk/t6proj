package t6proj.reports.communication.http.controller;

import adminlte.authentication.AuthenticationServiceInterface;
import adminlte.common_templates.communication.templates.AuthorizedAdminTableTemplate;
import adminlte.entity_list_table.EntityListTableService;
import adminlte.flash_message.FlashMessageService;
import adminlte.html_controller.business.AbstractHtmlController;
import adminlte.html_controller.communication.http.layout.LayoutFactory;
import adminlte.html_template_renderer.HtmlTemplateRendererService;
import adminlte.session.SessionServiceInterface;
import adminlte.web_form.WebFormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import t6proj.reports.communication.http.table.DepartmentSpendingsReportTable;
import t6proj.reports.communication.http.table.EndOfContractReportTable;
import t6proj.authorization.communication.http.RequiresAuthorizedUser;
import t6proj.reports.ReportsService;
import t6proj.reports.communication.http.template.EndOfContractReportTemplate;

@Controller
@RequestMapping("/reports/")
public class ReportsController extends AbstractHtmlController {

    private final ReportsService reportsService;

    public ReportsController(
            LayoutFactory layoutFactory,
            HtmlTemplateRendererService htmlTemplateRendererService,
            WebFormService webFormService,
            EntityListTableService entityListTableService,
            SessionServiceInterface sessionService,
            AuthenticationServiceInterface authenticationService,
            FlashMessageService flashMessageService,
            ReportsService reportsService
    ) {
        super(layoutFactory, htmlTemplateRendererService, webFormService, entityListTableService, sessionService, authenticationService, flashMessageService);
        this.reportsService = reportsService;
    }

    @GetMapping("/end-of-contract/")
    @ResponseBody
    @RequiresAuthorizedUser
    public String getEndOfContractReport(
            @RequestParam(value = "id", defaultValue = "1") Integer id
    ) {
        var report = this.reportsService.getEndOfContractReport(id, 20);
        var endOfContractReportTable = new EndOfContractReportTable(report);

        return this.renderTemplate(
                new EndOfContractReportTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Отчет об истекающих контрактах"),
                        this.renderTable(endOfContractReportTable)
                )
        );
    }

    @GetMapping("/department-spendings/")
    @ResponseBody
    @RequiresAuthorizedUser
    public String getDepartmentSpendingsReport(
            @RequestParam(value = "id", defaultValue = "1") Integer id
    ) {
        var report = this.reportsService.getDepartmentSpendingsReport(id, 20);
        var departmentSpendingsReportTable = new DepartmentSpendingsReportTable(report);

        return this.renderTemplate(
                new AuthorizedAdminTableTemplate(
                        this.layoutFactory.createAuthorizedAdminLayout("Отчет о тратах отделов"),
                        this.renderTable(departmentSpendingsReportTable)
                )
        );
    }
}

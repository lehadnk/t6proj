package t6proj.adminlte;

import adminlte.entity_list_table.EntityListTableBeanConfiguration;
import adminlte.flash_message.FlashMessageBeanConfiguration;
import adminlte.html_controller.HtmlControllerBeanConfiguration;
import adminlte.html_template_renderer.HtmlTemplateRendererBeanConfiguration;
import adminlte.navigation_menu.NavigationMenuBeanConfiguration;
import adminlte.web_form.WebFormBeanConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Import({
        EntityListTableBeanConfiguration.class,
        HtmlControllerBeanConfiguration.class,
        HtmlTemplateRendererBeanConfiguration.class,
        NavigationMenuBeanConfiguration.class,
        WebFormBeanConfiguration.class,
        FlashMessageBeanConfiguration.class
})
@Configuration
public class AdminlteConfigurationBean {
    @Bean
    public SpringResourceTemplateResolver backofficeTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/t6proj/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(0);
        templateResolver.setCheckExistence(true);

        return templateResolver;
    }
}

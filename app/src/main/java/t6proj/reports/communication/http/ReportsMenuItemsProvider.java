package t6proj.reports.communication.http;

import adminlte.navigation_menu.communication.MenuItemsProviderInterface;
import adminlte.navigation_menu.communication.menu_item.MenuItemInterface;
import adminlte.navigation_menu.communication.navigation_menu.NavigationMenuItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportsMenuItemsProvider implements MenuItemsProviderInterface {
    @Override
    public List<MenuItemInterface> getMenuItems() {
        var menuItems = new ArrayList<MenuItemInterface>();
        menuItems.add(new NavigationMenuItem().setTitle("Истекающие контракты").setUrl("/reports/end-of-contract/"));
        menuItems.add(new NavigationMenuItem().setTitle("Траты по отделам").setUrl("/reports/department-spendings/"));
        return menuItems;
    }
}

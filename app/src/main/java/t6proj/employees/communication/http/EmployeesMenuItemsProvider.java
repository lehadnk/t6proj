package t6proj.employees.communication.http;

import adminlte.navigation_menu.communication.MenuItemsProviderInterface;
import adminlte.navigation_menu.communication.menu_item.MenuItemInterface;
import adminlte.navigation_menu.communication.navigation_menu.NavigationMenuItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeesMenuItemsProvider implements MenuItemsProviderInterface {
    @Override
    public List<MenuItemInterface> getMenuItems() {
        var menuItems = new ArrayList<MenuItemInterface>();
        menuItems.add(new NavigationMenuItem().setTitle("Сотрудники").setUrl("/employees/"));
        menuItems.add(new NavigationMenuItem().setTitle("Запросы от сотрудников").setUrl("/employee-requests/"));
        return menuItems;
    }
}

package t6proj.jobs.communication.http;

import adminlte.navigation_menu.communication.MenuItemsProviderInterface;
import adminlte.navigation_menu.communication.menu_item.MenuItemInterface;
import adminlte.navigation_menu.communication.navigation_menu.NavigationMenuItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobsMenuItemsProvider implements MenuItemsProviderInterface {
    @Override
    public List<MenuItemInterface> getMenuItems() {
        var menuItems = new ArrayList<MenuItemInterface>();
        menuItems.add(new NavigationMenuItem().setTitle("Департаменты").setUrl("/departments/"));
        menuItems.add(new NavigationMenuItem().setTitle("Рабочие места").setUrl("/jobs/"));
        return menuItems;
    }
}

package t6proj.user.communication.http;

import adminlte.navigation_menu.communication.MenuItemsProviderInterface;
import adminlte.navigation_menu.communication.menu_item.MenuItemInterface;
import adminlte.navigation_menu.communication.navigation_menu.NavigationMenuItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMenuItemsProvider implements MenuItemsProviderInterface {
    @Override
    public List<MenuItemInterface> getMenuItems() {
        var menuItems = new ArrayList<MenuItemInterface>();
        menuItems.add(new NavigationMenuItem().setTitle("Пользователи").setUrl("/users/"));
        return menuItems;
    }
}

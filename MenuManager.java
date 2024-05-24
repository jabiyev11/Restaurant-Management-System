package RestaurantPractice;
import java.util.List;

public class MenuManager {

    private List<Item> menuItems;

    public MenuManager(List<Item> menuItems) {
        this.menuItems = menuItems;
    }

    public void addMenuItem(Item item) {
        menuItems.add(item);
    }

    public void displayMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("Unfortunately, The Menu is currently empty");
        } else {
            System.out.println("Current Menu: ");
            for (Item item : menuItems) {
                System.out.println(item);
            }
        }
    }

    public Item getItemByName(String name) {
        for (Item item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item; 
            }
        }
        return null; 
    }

    public boolean updateMenuItem(String name, String newName, Double newPrice, String newCategory) {
        for (Item item : menuItems) {
            if (item.getName().equals(name)) {
                item.setName(newName);
                item.setPrice(newPrice);
                item.setCategory(newCategory);
                return true;
            }
        }
        return false;
    }

    public boolean removeMenuItem(String name) {
        for (Item item : menuItems) {
            if (item.getName().equals(name)) {
                menuItems.remove(item);
                return true;
            }
        }
        return false;
    }
}

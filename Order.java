package RestaurantPractice;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private Map<Item, Integer> itemsWithQuantities;
    private double totalPrice;

    public Order() {
        this.orderId = nextOrderId++;
        this.itemsWithQuantities = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public int getOrderId() {
        return orderId;
    }

    public Map<Item, Integer> getItemsWithQuantities() {
        return itemsWithQuantities;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addItem(Item item, int quantity) {
        if (itemsWithQuantities.containsKey(item)) {
            int currentQuantity = itemsWithQuantities.get(item);
            itemsWithQuantities.put(item, currentQuantity + quantity);
        } else {
            itemsWithQuantities.put(item, quantity);
        }
        totalPrice += item.getPrice() * quantity;
    }

    public boolean removeItem(Item item, int quantity) {
        if (itemsWithQuantities.containsKey(item)) {
            int currentQuantity = itemsWithQuantities.get(item);
            if (quantity >= currentQuantity) {
                totalPrice -= item.getPrice() * currentQuantity;
                itemsWithQuantities.remove(item);
            } else {
                itemsWithQuantities.put(item, currentQuantity - quantity);
                totalPrice -= item.getPrice() * quantity;
            }

            return true;

        }

        return false;
    }

}
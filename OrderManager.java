package RestaurantPractice;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private List<Order> orders;

    public OrderManager(){
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders(){
        return orders;
    }

    public Order createOrder(){
        Order newOrder = new Order();
        orders.add(newOrder);
        return newOrder;
    }

    public Order getOrderById(int orderId){
        for(Order order : orders){
            if(order.getOrderId() == orderId){
                return order;
            }
        }

        return null;
    }

    public boolean finalizeOrder(int orderId){
        Order order = getOrderById(orderId);
        if(order != null){
            orders.remove(order);
            return true;
        }
        return false;
    }
}

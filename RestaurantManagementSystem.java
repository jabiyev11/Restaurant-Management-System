package RestaurantPractice;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RestaurantManagementSystem {

   private MenuManager menuManager;
   private ReservationManager reservationManager;
   private OrderManager orderManager;
   private Scanner scanner;

   public RestaurantManagementSystem() {
      menuManager = new MenuManager(new ArrayList<Item>());
      reservationManager = new ReservationManager();
      orderManager = new OrderManager();
      scanner = new Scanner(System.in);
   }

   public static void main(String[] args) {

      RestaurantManagementSystem system = new RestaurantManagementSystem();
      system.run();

   }

   public void run() {
      boolean running = true;

      while (running) {
         displayMainMenu();
         int choice = getUserInput("Choose an option: ");

         if (choice == 1) {
            manageMenuOptions(); 
         } else if (choice == 2) {
            manageReservationOptions();
         } else if (choice == 3) {
            manageOrderOptions();
         } else if (choice == 4) {
            System.out.println("Exiting the system. Goodbye!");
            running = false;
         } else {
            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
         }
      }

      scanner.close();
   }

   private static void displayMainMenu() {
      System.out.println("Dear customer, Welcome to our Restaurant: ");
      System.out.println("1. Manage Menu");
      System.out.println("2. Handle Reservation");
      System.out.println("3. Process Orders");
      System.out.println("4. Exit");
   }

   private void manageMenuOptions() {
      boolean managingMenu = true;

      while (managingMenu) {
         System.out.println("Manage Menu:");
         System.out.println("1. Add Menu Item");
         System.out.println("2. Display Menu");
         System.out.println("3. Update Menu Item");
         System.out.println("4. Remove Menu Item");
         System.out.println("5. Return to Main Menu");

         int choice = getUserInput("Choose an option: ");

         switch (choice) {
            case 1:
               addMenuItem();
               break;
            case 2:
               menuManager.displayMenu();
               break;
            case 3:
               updateMenuItem();
               break;
            case 4:
               removeMenuItem();
               break;
            case 5:
               System.out.println("Returning to Main Menu.");
               managingMenu = false;
               break;
            default:
               System.out.println("Invalid choice. Please enter a number between 1 and 5.");
               break;
         }

      }

   }

   private void manageReservationOptions() {
      boolean managingReservations = true;

      while (managingReservations) {
         System.out.println("Reservation Menu:");
         System.out.println("1. Add Reservation");
         System.out.println("2. View Reservations");
         System.out.println("3. Cancel Reservation");
         System.out.println("4. Return to Main Menu");

         int choice = getUserInput("Choose an option:");

         switch (choice) {
            case 1:
               addReservation();
               break;
            case 2:
               reservationManager.viewReservations();
               break;
            case 3:
               cancelReservation();
               break;
            case 4:
               System.out.println("Returning to Main Menu.");
               managingReservations = false;
               break;
            default:
               System.out.println("Invalid choice. Please enter a number between 1 and 4.");
               break;
         }
      }
   }

   private void manageOrderOptions() {
      System.out.println("Order Management:");
      System.out.println("1. Create Order");
      System.out.println("2. View Orders");
      System.out.println("3. Update Order");
      System.out.println("4. Finalize Order");
      System.out.println("5. Return to Main Menu");

      int choice = getUserInput("Choose an option: ");

      switch (choice) {
          case 1:
              createOrder();
              break;
          case 2:
              viewOrders();
              break;
          case 3:
              updateOrder();
              break;
          case 4:
              finalizeOrder();
              break;
          case 5:
              System.out.println("Returning to Main Menu.");
              break;
          default:
              System.out.println("Invalid choice. Please enter a number between 1 and 5.");
              break;
      }
  }

   private int getUserInput(String input) {
      System.out.print(input);
      while (!scanner.hasNextInt()) {
         System.out.println("Invalid input. Please enter the number");
         scanner.next();
      }
      return scanner.nextInt();

   }

   // Menu Managing Methods

   private void addMenuItem() {
      System.out.print("Enter item name: ");
      String itemName = scanner.next();

      System.out.print("Enter item price: ");
      Double itemPrice = scanner.nextDouble();

      System.out.print("Enter item category: ");
      String itemCategory = scanner.next();

      menuManager.addMenuItem(new Item(itemName, itemPrice, itemCategory));
      System.out.println("Item added successfully");

   }

   private void updateMenuItem() {
      System.out.print("Enter the name of the item to update: ");
      String currentName = scanner.next();

      System.out.print("Enter new item name: ");
      String newName = scanner.next();

      System.out.print("Enter new item price: ");
      Double newPrice = scanner.nextDouble();

      System.out.print("Enter new item category: ");
      String newCategory = scanner.next();

      boolean success = menuManager.updateMenuItem(currentName, newName, newPrice, newCategory);

      if (success) {
         System.out.println("Item updated succesfully");
      } else {
         System.out.println("Item not found");
      }

   }

   private void removeMenuItem() {
      System.out.print("Enter the name of an item to remove: ");
      String itemName = scanner.next();

      boolean success = menuManager.removeMenuItem(itemName);
      if (success) {
         System.out.println("Item removed succesfully");
      } else {
         System.out.println("No Item was found");
      }
   }

   // Handling Reservation methods

   private void addReservation() {
      System.out.print("Enter customer's name: ");
      String customerName = scanner.next();

      System.out.print("Enter reservation date (YYYY-MM-DD): ");
      String dateInput = scanner.next();

      Date reservationDate = parseDate(dateInput);
      if (reservationDate == null) {
         System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
         return;
      }

      System.out.print("Enter reservation time (HH:MM): ");
      String reservationTime = scanner.next();

      System.out.print("Enter number of people: ");
      int numberOfPeople = getUserInput("Please enter a valid number.");

      if (reservationManager.isTimeSlotAvailable(reservationDate, reservationTime)) {
         Reservation reservation = new Reservation(customerName, reservationDate, reservationTime, numberOfPeople);
         reservationManager.addReservation(reservation);
         System.out.println("Reservation added successfully.");
      } else {
         System.out.println("Time slot is not available. Please choose a different time.");
      }
   }

   private Date parseDate(String dateStr) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      dateFormat.setLenient(false);
      try {
         return dateFormat.parse(dateStr);
      } catch (ParseException e) {
         return null;
      }
   }

   private void cancelReservation() {
      System.out.print("Enter reservation ID to cancel: ");
      int reservationId = getUserInput("Enter a valid number.");

      boolean success = reservationManager.cancelReservation(reservationId);
      if (success) {
         System.out.println("Reservation canceled successfully.");
      } else {
         System.out.println("Reservation not found.");
      }
   }

   // Order Processing methods

   private void createOrder() {
      Order newOrder = orderManager.createOrder();

      boolean addingItems = true;

      while (addingItems) {
         menuManager.displayMenu();
         System.out.print("Enter the name of the item to add to the order:");
         String itemName = scanner.next();

         Item item = menuManager.getItemByName(itemName);
         if (item == null) {
            System.out.println("Item not found. Please try again.");
            continue;
         }

         System.out.print("Enter the quantity:");
         int quantity = getUserInput("Please enter a valid number:");

         newOrder.addItem(item, quantity);

         System.out.println("Would you like to add more items? (yes/no)");
         String response = scanner.next();
         addingItems = response.equalsIgnoreCase("yes");
      }

      System.out.println("Order created successfully. Order ID: " + newOrder.getOrderId());
   }

   private void viewOrders() {
      List<Order> orders = orderManager.getOrders();

      if (orders.isEmpty()) {
         System.out.println("No orders to display.");
         return;
      }

      for (Order order : orders) {
         System.out.println("Order ID: " + order.getOrderId());
         System.out.print("Items: ");
         for (Map.Entry<Item, Integer> entry : order.getItemsWithQuantities().entrySet()) {
            System.out.println(entry.getKey().getName() + " - Quantity: " + entry.getValue());
         }
         System.out.println("Total Price: $" + order.getTotalPrice());
         System.out.println();
      }
   }

   private void updateOrder() {
      System.out.print("Enter the order ID to update: ");
      int orderId = getUserInput("Please enter a valid number:");

      Order order = orderManager.getOrderById(orderId);
      if (order == null) {
         System.out.println("Order not found.");
         return;
      }

      System.out.println("1. Add Item to Order");
      System.out.println("2. Remove Item from Order");

      int choice = getUserInput("Choose an option:");

      switch (choice) {
         case 1:
            menuManager.displayMenu(); 
            System.out.print("Enter the name of the item to add: ");
            String itemName = scanner.next();

            Item item = menuManager.getItemByName(itemName); // Find the item in the menu
            if (item == null) {
               System.out.println("Item not found.");
               return;
            }

            System.out.print("Enter the quantity: ");
            int quantity = getUserInput("Please enter a valid number:");

            order.addItem(item, quantity); // Add item to order
            System.out.println("Item added to the order.");
            break;

         case 2:
            System.out.print("Enter the name of the item to remove: ");
            itemName = scanner.next();

            item = menuManager.getItemByName(itemName);
            if (item == null) {
               System.out.println("Item not found.");
               return;
            }

            System.out.print("Enter the quantity to remove: ");
            quantity = getUserInput("Please enter a valid number:");

            boolean removed = order.removeItem(item, quantity);
            if (removed) {
               System.out.println("Item removed from the order.");
            } else {
               System.out.println("Could not remove the item. Check if the quantity is correct.");
            }
            break;

         default:
            System.out.println("Invalid choice.");
            break;
      }
   }

   private void finalizeOrder() {
      System.out.print("Enter the order ID to finalize: ");
      int orderId = getUserInput("Please enter a valid number:");
  
      Order order = orderManager.getOrderById(orderId);
      if (order == null) {
          System.out.println("Order not found. Please enter a correct order ID.");
          return;
      }
  
      System.out.println("Finalizing Order...");
      System.out.println("Order ID: " + order.getOrderId());
      System.out.print("Items: ");
      for (Map.Entry<Item, Integer> entry : order.getItemsWithQuantities().entrySet()) {
          Item item = entry.getKey();
          int quantity = entry.getValue();
          System.out.println(item.getName() + " - Quantity: " + quantity);
      }
      System.out.println("Total Price: $" + order.getTotalPrice());
  
      boolean success = orderManager.finalizeOrder(orderId); 
      if (success) {
          System.out.println("Order finalized successfully.");
      } else {
          System.out.println("Failed to finalize order. Please try again.");
      }
  }
  

}

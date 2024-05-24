package RestaurantPractice;
public class Item {

    private String name;
    private Double price;
    private String category;

    public Item(String name, Double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price > 0.0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be a negative number");
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Price: %.2f, Category: %s", name, price, category);
    }

}

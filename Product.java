import java.util.*;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name.toLowerCase(); // Normalize to lowercase
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }

    public double getDiscountedPrice(int quantity) {
        double total = quantity * price;
        if (name.equals("bread") && quantity >= 3) {
            int free = quantity / 3;
            total -= free * price;
        } else if (name.equals("milk") && quantity >= 3) {
            total *= 0.90;
        } else if (name.equals("apple") && quantity > 5) {
            total -= quantity * 5;
        }
        return total;
    }

    public String getOfferText() {
        if (name.equals("bread")) return "Buy 2 Get 1 Free";
        if (name.equals("milk")) return "10% off on 3 or more";
        if (name.equals("apple")) return "5$ off each if greater than 5";
        return "No Offer";
    }
}

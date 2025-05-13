class ProductManager {
    private List<Product> productList = new ArrayList<>();

    public ProductManager() {
        // Default products
        productList.add(new Product(1, "apple", 10.0, 100));
        productList.add(new Product(2, "milk", 25.0, 50));
        productList.add(new Product(3, "bread", 20.0, 60));
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void showProducts() {
        System.out.printf("\n%-15s %-10s %-10s %-20s\n", "Product", "Price($)", "Stock", "Offer");
        for (Product p : productList) {
            System.out.printf("%-15s %-10.2f %-10d %-20s\n", capitalize(p.getName()), p.getPrice(), p.getStock(), p.getOfferText());
        }
    }

    public Product getProductByName(String name) {
        name = name.toLowerCase();
        for (Product p : productList) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void addProduct(String name, double price, int stock) {
        name = name.toLowerCase();
        Product existing = getProductByName(name);
        if (existing != null) {
            System.out.println("ℹ️ Product already exists. Updating price and stock.");
            existing.setPrice(price);
            existing.setStock(existing.getStock() + stock);
        } else {
            int id = productList.size() + 1;
            Product p = new Product(id, name, price, stock);
            productList.add(p);
        }
        System.out.println("✅ Product added/updated.");
    }

    public void removeProduct(String name) {
        name = name.toLowerCase();
        Product p = getProductByName(name);
        if (p != null) {
            productList.remove(p);
            System.out.println("✅ Product removed.");
        } else {
            System.out.println("❌ Product not found.");
        }
    }

    public void showOffers() {
        System.out.println("\n--- Current Offers ---");
        for (Product p : productList) {
            String offer = p.getOfferText();
            if (!offer.equals("No Offer"))
                System.out.println("• " + capitalize(p.getName()) + ": " + offer);
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

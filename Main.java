public class Main {
    static Scanner sc = new Scanner(System.in);
    static ProductManager pm = new ProductManager();

    public static void main(String[] args) {
        System.out.println("Welcome to the Billing System!");

        while (true) {
            System.out.println("\nLogin as:\n1. Admin\n2. Customer");
            System.out.print("Choice: ");
            int role = sc.nextInt();
            sc.nextLine(); // Clear buffer

            if (role == 1) {
                System.out.print("Enter admin password: ");
                String pwd = sc.nextLine();
                if (pwd.equals("admin123")) {
                    adminMenu();
                } else {
                    System.out.println("❌ Incorrect password!");
                }
            } else if (role == 2) {
                customerMenu();
            } else {
                System.out.println("❌ Invalid option.");
            }
        }
    }

    static void adminMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Show Products");
            System.out.println("2. Add Product");
            System.out.println("3. Remove Product");
            System.out.println("4. View Offers");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> pm.showProducts();
                case 2 -> {
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine();
                    pm.addProduct(name, price, stock);
                }
                case 3 -> {
                    System.out.print("Enter product name to remove: ");
                    String name = sc.nextLine();
                    pm.removeProduct(name);
                }
                case 4 -> pm.showOffers();
                case 5 -> {
                    System.out.println("✅ Logged out from Admin.");
                    running = false;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    static void customerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Show Products");
            System.out.println("2. View Offers");
            System.out.println("3. Create Bill");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> pm.showProducts();
                case 2 -> pm.showOffers();
                case 3 -> createBill();
                case 4 -> {
                    System.out.println("✅ Logged out from Customer.");
                    running = false;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    static void createBill() {
        Map<Product, Integer> cart = new HashMap<>();
        double total = 0;

        while (true) {
            System.out.print("Enter product to buy (or type 'done'): ");
            String name = sc.nextLine().toLowerCase();

            if (name.equalsIgnoreCase("done"))
                break;

            Product p = pm.getProductByName(name);
            if (p == null) {
                System.out.println("❌ Product not found.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();

            if (qty > p.getStock()) {
                System.out.println("❌ Not enough stock.");
                continue;
            }

            cart.put(p, cart.getOrDefault(p, 0) + qty);
        }

        System.out.println("\n--- BILL ---");
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            double itemTotal = p.getDiscountedPrice(qty);

            System.out.printf("%s x %d = ₹%.2f\n", capitalize(p.getName()), qty, itemTotal);
            total += itemTotal;

            // Reduce stock
            p.setStock(p.getStock() - qty);
        }

        System.out.println("Total Bill: ₹" + String.format("%.2f", total));
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

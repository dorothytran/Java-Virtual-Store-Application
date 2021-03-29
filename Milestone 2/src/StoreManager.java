// Dorothy Tran
// Cassidy Pacada
import java.util.ArrayList;
import java.util.HashMap;

/**
 * StoreManager class that manages the Inventory class and the ShoppingCart class
 */
public class StoreManager {
    private Inventory inventory;
    private HashMap<Integer, ShoppingCart> cartTrack;

    /**
     * Constructor for StoreManager that creates new Inventory and StoreManager object
     */
    public StoreManager() {
        this.inventory = new Inventory();
        this.cartTrack = new HashMap<Integer, ShoppingCart>();
        addToInven(new Product("Sims Deluxe", 1, 40.0), 20);
        addToInven(new Product("Minecraft", 2, 45.50), 5);
        addToInven(new Product("Mario Kart", 3, 80.20), 10);
        addToInven(new Product("Cyberpunk", 4, 80.0), 5);
    }

    /**
     * This method adds a product to the inventory given a product and the amount of said product
     *
     * @param product  Product, the product being added to the inventory
     * @param quantity int, the amount of product being added to the inventory
     */
    public void addToInven(Product product, int quantity) {
        this.inventory.addStock(product, quantity);
    }


    /**
     * This method creates a new shopping cart id and returns that id
     *
     * @return an integer containing the shopping cart id
     */
    public int assignNewCartID() {
        int current = this.cartTrack.size();
        this.cartTrack.put(current, new ShoppingCart());
        return current;
    }

    /**
     * Accessor method to get the inventory of the store
     *
     * @return Inventory, inventory of the store
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Accessor method to get the shopping cart of the store
     *
     * @return ShoppingCart, shopping cart of the store
     */
    public ShoppingCart getShoppingCart(int cartID) {
        return this.cartTrack.get(cartID);
    }

    /**
     * Method checks the amount of stock in the inventory
     *
     * @param productID int, checks stock given a product ID
     * @return int, returns the amount of stock in the inventory
     */
    public int checkStock(int productID) {
        return inventory.getStock(productID);
    }

    /**
     * This method adds a product to a specific shopping cart
     *
     * @param product  Product, the product being added to the cart
     * @param quantity int, the amount of product being added to the cart
     * @param cartID   int, the id of the specific cart that is receiving the product
     */
    public void addToCart(Product product, int quantity, int cartID) {
        if (this.cartTrack.containsKey(cartID)) {
            this.cartTrack.get(cartID).addStock(product, quantity);
            this.inventory.removeStock(product.getID(), quantity);
        }
    }

    /**
     * Method that removes the specified quantity of an item based on the cart ID while adding it back into the inventory
     *
     * @param product  Product, product ID of the item in the cart
     * @param quantity int, amount of product in the cart
     * @param cartID   int, shopping cart ID
     */
    public void removeFromCart(Product product, int quantity, int cartID) {
        if (this.cartTrack.containsKey(cartID)) {
            int pos = this.cartTrack.get(cartID).getProductList().indexOf(product);
            if (this.cartTrack.get(cartID).getStockList().get(pos) - quantity > 0) {
                this.cartTrack.get(cartID).removeStock(product.getID(), quantity);
                this.inventory.addStock(product, quantity);
            } else {
                this.inventory.addStock(product, this.cartTrack.get(cartID).getStockList().get(pos));
                this.cartTrack.get(cartID).removeStock(product.getID(), quantity);
                this.cartTrack.get(cartID).getProductList().remove(pos);
                this.cartTrack.get(cartID).getStockList().remove(pos);
            }
        }
    }

    /**
     * Method that processes a transaction of a purchase
     *
     * @return double, total cost of the purchase made by the user
     */
    public double transaction(ArrayList<Product> product, ArrayList<Integer> amount) {
        int i = 0;
        int totalTemp;
        double cost = 0;

        while (i < amount.size() && i < product.size()) {
            if (i <= product.size() - 1) {
                System.out.println("Product name: " + product.get(i).getName() +  ", " + "The quantity is: " + amount.get(i));
            }
            i++;
        }
        for (totalTemp = 0; totalTemp < product.size(); totalTemp++) {
            double productPrice = inventory.getPrice(product.get(totalTemp).getID());
            cost += (amount.get(totalTemp) * productPrice);
        }
        System.out.println("The total cost is: " + cost);
        return cost;
    }
}

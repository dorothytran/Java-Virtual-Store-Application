//Dorothy Tran 101141902
//Cassidy Pacada 101143345
package myStore;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * StoreManager class handles the Inventory and ShoppingCart classes
 */
public class StoreManager {
    private Inventory inventory;
    private HashMap<Integer, ShoppingCart> cartTrack;  //keeps track of the cart ids and their respective shopping carts

    /**
     * Constructor for StoreManager that creates a new StoreManager object and a new inventory.
     * For testing purposes, products have been added to inventory
     */
    public StoreManager(){              //added in milestone 1, edited in milestone 2
        this.inventory = new Inventory();
        this.cartTrack = new HashMap<Integer, ShoppingCart>();
        addToInven(new Product("Sims Deluxe", 1, 40.0), 20);
        addToInven(new Product("Minecraft", 2, 45.50), 5);
        addToInven(new Product("Mario Kart", 3, 80.20), 10);
        addToInven(new Product("Cyberpunk", 4, 80.0), 5);
    }

    /**
     * Accessor method to get the store's inventory
     * @return Inventory, store's inventory
     */
    public Inventory getInventory(){    //added in milestone 1
        return this.inventory;
    }

    /**
     * Method checks the amount of stock for a specified product in the inventory
     * @param product Product, the product ID for the desired product
     * @return int, the amount of stock in the inventory for the specified product
     */
    public int checkStock(Product product){   //added in milestone 1
        return this.inventory.getProductQuantity(product);
    }

    /**
     * Method creates a new shopping cart id and returns that id
     * @return and integer containing the shopping cart id
     */
    public int assignNewCartID(){   //added in milestone 2
        int current = this.cartTrack.size();
        this.cartTrack.put(current, new ShoppingCart());
        return current; //original size of cartTrack is 0 so every time current is added, size and current increments
    }

    /**
     * Accessor method to get the user's shopping cart
     * @param cartID int, the id for the specified shopping cart
     * @return ShoppingCart, the specified shopping cart
     */
    public ShoppingCart getShoppingCart(int cartID){  //added in milestone 2
        return this.cartTrack.get(cartID);
    }

    /**
     * Method adds a product to the inventory given a specified product
     * @param product the product to be added to the inventory
     * @param quantity the amount of product to be added to the inventory
     */
    public void addToInven(Product product, int quantity){  //added in milestone 2, edited in milestone 3
        if(product == null){    //prevent NullPointerException
            return;
        }
        this.inventory.addProductQuantity(product, quantity);
    }

    /**
     * Method that adds a product to a specific shopping cart
     * @param product the product to be added to the cart
     * @param quantity the amount of product to be added to the cart
     * @param cartID the ID of the specific cart that is receiving the product
     */
    public void addToCart(Product product, int quantity, int cartID){ //added in milestone 2, edited in milestone 3
        if(product == null){ //prevent NullPointerException
            return;
        }
        if(this.cartTrack.containsKey(cartID)){
            this.cartTrack.get(cartID).addProductQuantity(product, quantity);
            this.inventory.removeProductQuantity(product, quantity);
        }
    }

    /**
     * Method that removes the specified quantity of an item from the cart with the specified cartID and adds that
     * quantity of item back into the inventory
     * @param product product to be removed from the cart
     * @param quantity amount of product to be removed from the cart
     * @param cartID ID of the specified cart that the product is to be removed from
     */
    public void removeFromCart(Product product, int quantity, int cartID){ //added in milestone 2, edited in milestone 3
        if (product == null){ //prevent NullPointerException
            return;
        }
        if(this.cartTrack.containsKey(cartID)){
            int pos = this.cartTrack.get(cartID).getProductList().indexOf(product);

            if(this.cartTrack.get(cartID).getStockList().get(pos) - quantity > 0){
                //removes specified amount if there is adequate amount in cart to be removed
                this.cartTrack.get(cartID).removeProductQuantity(product, quantity);
                this.inventory.addProductQuantity(product, quantity);
            }
            else{ //if amount to be removed is greater than amount in cart, removes all product in cart
                this.inventory.addProductQuantity(product, this.cartTrack.get(cartID).getStockList().get(pos));
                this.cartTrack.get(cartID).removeProductQuantity(product, quantity);
                this.cartTrack.get(cartID).getProductList().remove(pos);
                this.cartTrack.get(cartID).getStockList().remove(pos);
            }
        }
    }

    /**
     * Method that processes a transaction of a purchase
     * @param product list of products being bought
     * @param amount list of amounts of products being bought
     * @return double, total cost of the purchase made by user
     */
    public double transaction(ArrayList<Product> product, ArrayList<Integer> amount){
        double cost = 0;   //added in milestone 1, edited in milestone 4 (removed part that prints receipt)

        for(int i= 0; i < product.size(); i++){
            double productPrice = this.inventory.getPrice(product.get(i).getID());
            cost += amount.get(i) * productPrice;
        }
        return cost;
    }
}

// Dorothy Tran
// Cassidy Pacada
package myStore;
/**
 * ShoppingCart class that tracks the state of the users shopping cart
 */
public class ShoppingCart extends Inventory {
    private int cartID;

    /**
     * Default constructor for Shopping Cart
     */
    public ShoppingCart() {
        super();
    }

    /**
     * Accessor method to get the shopping cart ID
     *
     * @return int, cart ID of the shopping cart
     */
    public int getCartID() {
        return this.cartID;
    }

    /**
     * Mutator method that sets the shopping cart ID
     */
    public void setCartID(int cartID) { this.cartID = cartID; }
}
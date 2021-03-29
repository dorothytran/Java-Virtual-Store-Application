// Written by: Dorothy Tran
// Partner: Cassidy Pacada
package storetest;

import org.junit.jupiter.api.*;
import store.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * StoreManagerTest Class to test the methods of the store.StoreManager Class using JUnit Testing
 */
public class StoreManagerTest {
    private static StoreManager storeManager;
    private static Product product1;
    private static Product product2;
    private static Product product3;
    private static Product product4;

    /**
     * Initializing products from the store.Inventory for store.StoreManager
     */
    @BeforeEach
    public void init() {
        storeManager = new StoreManager();
        product1 = new Product("Sims Deluxe", 1, 40.00);
        product2 = new Product("Minecraft", 2, 45.50);
        product3 = new Product("Mario Kart", 3, 80.20);
        product4 = new Product("Cyberpunk", 4, 80.00);
    }

    /**
     * Method tests if additional products of specified quantities are added into the store.Inventory
     */
    @Test
    public void testAddToInven() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Testing addToInven() method");
        storeManager.addToInven(product1, 5);
        storeManager.addToInven(product2, 5);
        storeManager.addToInven(product3, 10);
        storeManager.addToInven(product4, 5);
        assertEquals(25, storeManager.getInventory().getStock(1), "Error with addToInven() method. store.Product was not added to the inventory.");
        assertEquals(10, storeManager.getInventory().getStock(2), "Error with addToInven() method. store.Product was not added to the inventory.");
        assertEquals(20, storeManager.getInventory().getStock(3), "Error with addToInven() method. store.Product was not added to the inventory.");
        assertEquals(10, storeManager.getInventory().getStock(4), "Error with addToInven() method. store.Product was not added to the inventory.");
        System.out.println("Adding Sims Deluxe Games to the store.Inventory >>> Expected: 25 | Actual: " + storeManager.getInventory().getStock(1));
        System.out.println("Adding Minecraft Games to the store.Inventory   >>> Expected: 10 | Actual: " + storeManager.getInventory().getStock(2));
        System.out.println("Adding Mario Kart Games to the store.Inventory  >>> Expected: 20 | Actual: " + storeManager.getInventory().getStock(3));
        System.out.println("Adding Cyberpunk Games to the store.Inventory   >>> Expected: 10 | Actual: " + storeManager.getInventory().getStock(4));
        System.out.println("All tests for addToInven() passed!");
    }

    /**
     * Method tests if a new shopping cart with a cart ID is generated to the store
     */
    @Test
    public void testAssignNewCartID() {
        assertEquals(0, storeManager.assignNewCartID(), "Error with assignNewCartID() method. Incorrect cart ID generated.");
        assertEquals(1, storeManager.assignNewCartID(), "Error with assignNewCartID() method. Incorrect cart ID generated.");
        assertEquals(2, storeManager.assignNewCartID(), "Error with assignNewCartID() method. Incorrect cart ID generated.");
        assertEquals(3, storeManager.assignNewCartID(), "Error with assignNewCartID() method. Incorrect cart ID generated.");
    }

    /**
     * Method tests if the total amount of stock in the store.Inventory given a product ID is returned
     * */
    @Test
    public void testCheckStock() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Testing checkStock() method");
        assertEquals(20, storeManager.checkStock(1),"Error with checkStock() method. The wrong amount of stock was counted in the inventory.");
        assertEquals(5, storeManager.checkStock(2), "Error with checkStock() method. The wrong amount of stock was counted in the inventory.");
        assertEquals(10, storeManager.checkStock(3),"Error with checkStock() method. The wrong amount of stock was counted in the inventory.");
        assertEquals(5, storeManager.checkStock(4), "Error with checkStock() method. The wrong amount of stock was counted in the inventory.");
        assertEquals(-1, storeManager.checkStock(5));
        System.out.println("Checking the amount of stock of Sims Deluxe >>> Expected: 20 | Actual: " + storeManager.checkStock(1));
        System.out.println("Checking the amount of stock of Minecraft   >>> Expected: 5, | Actual: " + storeManager.checkStock(2));
        System.out.println("Checking the amount of stock of Mario Kart  >>> Expected: 10 | Actual: " + storeManager.checkStock(3));
        System.out.println("Checking the amount of stock of Cyberpunk   >>> Expected: 5  | Actual: " + storeManager.checkStock(4));
        System.out.println("Checking amount of stock of null product    >>> Expected: -1 | Actual: " + storeManager.checkStock(5));
        System.out.println("All tests for checkStock() passed!");
    }

    /**
     * Method tests if products from the inventory are added into the user's shopping cart
     */
    @Test
    public void testAddToCart() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Testing addToCart() method");
        storeManager.assignNewCartID();
        storeManager.addToCart(product1, 2,0); // Adding one product to the Shopping Cart
        assertEquals(2, storeManager.getShoppingCart(0).getStock(1), "Error with addToCart() method. store.Product was not added to cart.");
        assertEquals("Sims Deluxe", storeManager.getShoppingCart(0).getName(1),"Error with addToCart() method. store.Product was not added to cart.");
        // Checking if stock from the inventory was removed
        assertEquals(18,storeManager.getInventory().getStock(1), "Error with addToCart() method. store.Product was not removed from inventory.");
        System.out.println("Products in CartID: 0 >>> Expected: [Sims Deluxe, Qty: 2]");
        System.out.println("                      >>> Actual:   [" + storeManager.getShoppingCart(0).getName(1)
                + ", Qty: " + storeManager.getShoppingCart(0).getStock(1) + "]");

        storeManager.assignNewCartID();
        // Adding two products to one Shopping Cart
        storeManager.addToCart(product1,1,1);
        storeManager.addToCart(product2,1,1);
        assertEquals(1, storeManager.getShoppingCart(1).getStock(1),"Error with addToCart() method. store.Product was not added to cart.");
        assertEquals(1, storeManager.getShoppingCart(1).getStock(2),"Error with addToCart() method. store.Product was not added to cart.");
        assertEquals("Sims Deluxe", storeManager.getShoppingCart(1).getName(1),"Error with addToCart() method. store.Product was not added to cart.");
        assertEquals("Minecraft", storeManager.getShoppingCart(1).getName(2),"Error with addToCart() method. store.Product was not added to cart.");
        // Checking if stock for each product from the inventory was removed
        assertEquals(17, storeManager.getInventory().getStock(1),"Error with addToCart() method. store.Product was not removed from inventory.");
        assertEquals(4, storeManager.getInventory().getStock(2), "Error with addToCart() method. store.Product was not removed from inventory.");
        System.out.println("Products in CartID: 1 >>> Expected: [Sims Deluxe, Qty: 1], [Minecraft, Qty: 1]");
        System.out.println("                      >>> Actual:   [" + storeManager.getShoppingCart(1).getName(1) + ", Qty: "
                + storeManager.getShoppingCart(1).getStock(1)
                + "], [" + storeManager.getShoppingCart(1).getName(2) + ", Qty: "
                + storeManager.getShoppingCart(1).getStock(2) + "]");

        storeManager.assignNewCartID();
        // Adding 3 different products into one shopping cart
        storeManager.addToCart(product1,2,2);
        storeManager.addToCart(product2,2,2);
        storeManager.addToCart(product3,10,2);
        assertEquals(2,storeManager.getShoppingCart(2).getStock(1), "Error with addToCart() method. store.Product was not added to cart.");
        assertEquals(2,storeManager.getShoppingCart(2).getStock(2), "Error with addToCart() method. store.Product was not added to cart.");
        assertEquals(10,storeManager.getShoppingCart(2).getStock(3), "Error with addToCart() method. store.Product was not added to cart.");
        assertEquals("Sims Deluxe", storeManager.getShoppingCart(2).getName(1),"Error with addToCart() method. store.Product was not added to cart.");
        assertEquals("Minecraft", storeManager.getShoppingCart(2).getName(2),"Error with addToCart() method. store.Product was not added to cart.");
        assertEquals("Mario Kart", storeManager.getShoppingCart(2).getName(3),"Error with addToCart() method. store.Product was not added to cart.");
        assertEquals(15, storeManager.getInventory().getStock(1),"Error with addToCart() method. store.Product was not removed from inventory.");
        assertEquals(2, storeManager.getInventory().getStock(2), "Error with addToCart() method. store.Product was not removed from inventory.");
        assertEquals(0,storeManager.getInventory().getStock(3), "Error with addToCart() method. store.Product was not removed from inventory.");
        System.out.println("Products in CartID: 2 >>> Expected: [Sims Deluxe, Qty: 2], [Minecraft, Qty: 2], [Mario Kart, Qty: 10]");
        System.out.println("                      >>> Actual:   [" + storeManager.getShoppingCart(2).getName(1) + ", Qty: " +
                storeManager.getShoppingCart(2).getStock(1) + "], [" + storeManager.getShoppingCart(2).getName(2)
                + ", Qty: " + storeManager.getShoppingCart(2).getStock(2) + "], [" + storeManager.getShoppingCart(2).getName(3)
                + ", Qty: " + storeManager.getShoppingCart(2).getStock(3) + "]");

        // Adding two of the same products into one shopping cart
        storeManager.assignNewCartID();
        storeManager.addToCart(product4,2,3);
        storeManager.addToCart(product4,1,3);
        assertEquals(3, storeManager.getShoppingCart(3).getStock(4), "Error with addToCart() method. store.Product was not added to cart.");
        assertEquals("Cyberpunk", storeManager.getShoppingCart(3).getName(4), "Error with addToCart() method. store.Product was not added to cart.");
        assertEquals(2, storeManager.getInventory().getStock(4), "Error with addToCart() method. store.Product was not removed from inventory.");
        System.out.println("Products in CartID: 3 >>> Expected: [Cyberpunk, Qty: 3]");
        System.out.println("                      >>> Actual:   [" + storeManager.getShoppingCart(3).getName(4)
                + ", Qty: " + storeManager.getShoppingCart(3).getStock(4) + "]");
        System.out.println("All tests for addToCart() passed!");
    }

    /**
     * Method tests if products from the user's shopping cart are removed when desired
     */
    @Test
    public void testRemoveFromCart() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Testing removeFromCart() method");
        storeManager.assignNewCartID();
        storeManager.addToCart(product1,2,0);
        storeManager.removeFromCart(product1,1,0);
        assertEquals(1, storeManager.getShoppingCart(0).getStock(1), "Error with removeFromCart(). store.Product was not removed.");
        assertEquals("Sims Deluxe", storeManager.getShoppingCart(0).getName(1),"Error with removeFromCart(). store.Product was not removed.");
        assertEquals(19, storeManager.getInventory().getStock(1), "Error with removeFromCart(). store.Product was not added to inventory.");
        System.out.println("Products in CartID: 0 >>> Expected: [Sims Deluxe, Qty: 1]");
        System.out.println("                      >>> Actual:   [" + storeManager.getShoppingCart(0).getName(1)
                + ", Qty: " + storeManager.getShoppingCart(0).getStock(1) + "]");

        storeManager.assignNewCartID();
        storeManager.addToCart(product1,2,1);
        storeManager.addToCart(product2,2,1);
        storeManager.addToCart(product3,10,1);
        storeManager.removeFromCart(product1,1,1);
        storeManager.removeFromCart(product2,1,1);
        storeManager.removeFromCart(product3,5,1);
        assertEquals(1,storeManager.getShoppingCart(1).getStock(1), "Error with removeFromCart() method. store.Product was not removed from cart.");
        assertEquals(1,storeManager.getShoppingCart(1).getStock(2), "Error with removeFromCart() method. store.Product was not removed from cart.");
        assertEquals(5,storeManager.getShoppingCart(1).getStock(3), "Error with removeFromCart() method. store.Product was not removed from cart.");
        assertEquals("Sims Deluxe", storeManager.getShoppingCart(1).getName(1),"Error with removeFromCart() method. store.Product was not removed from cart.");
        assertEquals("Minecraft", storeManager.getShoppingCart(1).getName(2),"Error with removeFromCart() method. store.Product was not removed from cart.");
        assertEquals("Mario Kart", storeManager.getShoppingCart(1).getName(3),"Error with removeFromCart() method. store.Product was not removed from cart.");
        assertEquals(18, storeManager.getInventory().getStock(1),"Error with removeFromCart() method. store.Product was not added to inventory.");
        assertEquals(4, storeManager.getInventory().getStock(2), "Error with removeFromCart() method. store.Product was not added to inventory.");
        assertEquals(5,storeManager.getInventory().getStock(3), "Error with removeFromCart() method. store.Product was not added to inventory.");
        System.out.println("Products in CartID: 1 >>> Expected: [Sims Deluxe, Qty: 1], [Minecraft, Qty: 1], [Mario Kart, Qty: 5]");
        System.out.println("                      >>> Actual:   [" + storeManager.getShoppingCart(1).getName(1) + ", Qty: " +
                storeManager.getShoppingCart(1).getStock(1) + "], [" + storeManager.getShoppingCart(1).getName(2)
                + ", Qty: " + storeManager.getShoppingCart(1).getStock(2) + "], [" + storeManager.getShoppingCart(1).getName(3)
                + ", Qty: " + storeManager.getShoppingCart(1).getStock(3) + "]");

        storeManager.assignNewCartID();
        storeManager.addToCart(product4,5,2);
        storeManager.removeFromCart(product4,1,2);
        storeManager.removeFromCart(product4,2,2);
        assertEquals(2,storeManager.getShoppingCart(2).getStock(4), "Error with removeFromCart() method. store.Product was not removed from cart.");
        assertEquals("Cyberpunk", storeManager.getShoppingCart(2).getName(4),"Error with removeFromCart() method. store.Product was not removed from cart.");
        assertEquals(3,storeManager.getInventory().getStock(4), "Error with removeFromCart() method. store.Product was not added to inventory.");
        System.out.println("Products in CartID: 1 >>> Expected: [Cyberpunk, Qty: 2]");
        System.out.println("                      >>> Actual:   [" + storeManager.getShoppingCart(2).getName(4)
                + ", Qty: " + storeManager.getShoppingCart(2).getStock(4) + "]");
        System.out.println("All tests for removeFromCart() passed!");
    }

    /**
     * Method tests the transaction of the user's purchase from the shopping cart are processed
     */
    @Test
    public void testTransaction() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Testing transaction() method");
        storeManager.assignNewCartID();
        storeManager.addToCart(product1, 2, 0);
        assertEquals(80.0, storeManager.transaction(storeManager.getShoppingCart(0).getProductList(), storeManager.getShoppingCart(0).getStockList()), "Error with transaction() method. Incorrect total cost.");
        System.out.println("Expected cost: $80.0");
        System.out.println();
        storeManager.assignNewCartID();
        storeManager.addToCart(product1, 1, 1);
        storeManager.addToCart(product2,3,1);
        assertEquals(176.5, storeManager.transaction(storeManager.getShoppingCart(1).getProductList(), storeManager.getShoppingCart(1).getStockList()), "Error with transaction() method. Incorrect total cost.");
        System.out.println("Expected cost: $176.5");
        System.out.println();
        storeManager.assignNewCartID();
        storeManager.addToCart(product1, 2, 2);
        storeManager.addToCart(product2,4,2);
        storeManager.addToCart(product3,2,2);
        assertEquals(422.4, storeManager.transaction(storeManager.getShoppingCart(2).getProductList(), storeManager.getShoppingCart(2).getStockList()), "Error with transaction() method. Incorrect total cost.");
        System.out.println("Expected cost: $422.4");
        System.out.println();
        storeManager.assignNewCartID();
        storeManager.addToCart(product1, 1, 3);
        storeManager.addToCart(product2,2,3);
        storeManager.addToCart(product3,3,3);
        storeManager.addToCart(product4,4,3);
        assertEquals(691.6, storeManager.transaction(storeManager.getShoppingCart(3).getProductList(), storeManager.getShoppingCart(3).getStockList()), "Error with transaction() method. Incorrect total cost.");
        System.out.println("Expected cost: $691.6");
        System.out.println();
        System.out.println("All tests for transaction() passed!");
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}

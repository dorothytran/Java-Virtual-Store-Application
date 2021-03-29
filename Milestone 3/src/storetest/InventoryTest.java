// Dorothy Tran 101141902 //
// Cassidy Pacada 101143345 //
package storetest;

import store.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * InventoryTest Class that tests the store.Inventory Class methods using JUnit Testing
 */
public class InventoryTest {
    private static Inventory inventory;
    private static Product product1;
    private static Product product2;
    private static Product product3;
    private static Product product4;

    /**
     * Initializing products from the store.Inventory given product ID and price
     */
    @BeforeEach
    public void init() {
        inventory = new Inventory();
        product1 = new Product("Sims Deluxe", 1, 40.00);
        product2 = new Product("Minecraft", 2, 45.50);
        product3 = new Product("Mario Kart", 3, 80.20);
        product4 = new Product("Cyberpunk", 4, 80.00);
    }

    /**
     * Method tests if the name of the product is returned
     */
    @Test
    public void testGetName() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Testing getName() method");
        inventory.addStock(product1, 1);
        inventory.addStock(product2, 1);
        inventory.addStock(product3, 1);
        inventory.addStock(product4, 1);
        assertEquals("Sims Deluxe", inventory.getName(1), "Error with getName() method. Incorrect store.Product Name.");
        assertEquals("Minecraft", inventory.getName(2), "Error with getName() method. Incorrect store.Product Name.");
        assertEquals("Mario Kart", inventory.getName(3), "Error with getName() method. Incorrect store.Product Name.");
        assertEquals("Cyberpunk", inventory.getName(4), "Error with getName() method. Incorrect store.Product Name.");
        assertNull(null, "store.Product does not exist in the store.Inventory");
        System.out.println("Name of ProductID: 1 >>> Expected: Sims Deluxe |  Actual: " + inventory.getName(1));
        System.out.println("Name of ProductID: 2 >>> Expected: Minecraft   |  Actual: " + inventory.getName(2));
        System.out.println("Name of ProductID: 3 >>> Expected: Mario Kart  |  Actual: " + inventory.getName(3));
        System.out.println("Name of ProductID: 4 >>> Expected: Cyberpunk   |  Actual: " + inventory.getName(4));
        System.out.println("Name of ProductID: 5 >>> Expected: null        |  Actual: " + inventory.getName(5));
        System.out.println("All tests for getName() passed!");
    }

    /**
     * Method tests if the price of the product is returned
     */
    @Test
    public void testGetPrice() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Testing getPrice() method");
        inventory.addStock(product1, 1);
        inventory.addStock(product2, 1);
        inventory.addStock(product3, 1);
        inventory.addStock(product4, 1);
        assertEquals(40.0, inventory.getPrice(1),"Error with getPrice() method. Incorrect store.Product Price");
        assertEquals(45.50,inventory.getPrice(2), "Error with getPrice() method. Incorrect store.Product Price");
        assertEquals(80.2, inventory.getPrice(3), "Error with getPrice() method. Incorrect store.Product Price");
        assertEquals(80.0, inventory.getPrice(4), "Error with getPrice() method. Incorrect store.Product Price");
        assertEquals(-1.0, inventory.getPrice(5), "Error with getPrice() method. store.Product does not exist in the store.Inventory");
        System.out.println("Checking the price of Sims Deluxe >>> Expected: $40.0 | Actual: $" + inventory.getPrice(1));
        System.out.println("Checking the price of Minecraft   >>> Expected: $45.5 | Actual: $" + inventory.getPrice(2));
        System.out.println("Checking the price of Mario Kart  >>> Expected: $80.2 | Actual: $" + inventory.getPrice(3));
        System.out.println("Checking the price of Cyberpunk   >>> Expected: $80.0 | Actual: $" + inventory.getPrice(4));
        System.out.println("Checking price of null store.Product    >>> Expected: -1.0  | Actual: " + inventory.getPrice(5));
        System.out.println("All tests for getPrice() passed!");
    }

    /**
     * Method that tests if the amount of stock in the store.Inventory is returned
     */
    @Test
    public void testGetStock() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Testing getStock() method");
        inventory.addStock(product1,15);
        inventory.addStock(product2,5);
        inventory.addStock(product3,10);
        inventory.addStock(product4,5);
        assertEquals(15, inventory.getStock(1), "Error with getStock() method. Incorrect Stock Count");
        assertEquals(5, inventory.getStock(2), "Error with getStock() method. Incorrect Stock Count");
        assertEquals(10, inventory.getStock(3), "Error with getStock() method. Incorrect Stock Count");
        assertEquals(5, inventory.getStock(4),"Error with getStock() method. Incorrect Stock Count");
        assertEquals(-1, inventory.getStock(5), "Error with getStock() method. store.Product does not exist in the store.Inventory");
        System.out.println("Checking the stock of Sims Deluxe >>> Expected: 15  |  Actual: " + inventory.getStock(1));
        System.out.println("Checking the stock of Minecraft   >>> Expected: 5   |  Actual: " + inventory.getStock(2));
        System.out.println("Checking the stock of Mario Kart  >>> Expected: 10  |  Actual: " + inventory.getStock(3));
        System.out.println("Checking the stock of Cyberpunk   >>> Expected: 5   |  Actual: " + inventory.getStock(4));
        System.out.println("Checking stock of null store.Product    >>> Expected: -1  |  Actual: " + inventory.getStock(5));
        System.out.println("All tests for getStock() passed!");
    }

    /**
     * Method tests if products of specified quantity are added into the inventory
     */
    @Test
    public void testAddStock() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Testing addStock() method");
        inventory.addStock(product1,3);
        inventory.addStock(product2,20);
        inventory.addStock(product3,10);
        inventory.addStock(product4,1);
        inventory.addStock(product4,3);
        assertEquals(3,inventory.getStock(1), "Additional products were not added to the inventory");
        assertEquals(20,inventory.getStock(2), "Additional products were not added to the inventory");
        assertEquals(10,inventory.getStock(3), "Additional products were not added to the inventory");
        assertEquals(4,inventory.getStock(4), "Additional products were not added to the inventory");
        System.out.println("Checking the stock added of Sims Deluxe >>> Expected: 3   |  Actual: " + inventory.getStock(1));
        System.out.println("Checking the stock added of Minecraft   >>> Expected: 20  |  Actual: " + inventory.getStock(2));
        System.out.println("Checking the stock added of Mario Kart  >>> Expected: 10  |  Actual: " + inventory.getStock(3));
        System.out.println("Checking the stock added of Cyberpunk   >>> Expected: 4   |  Actual: " + inventory.getStock(4));
        System.out.println("All tests for addStock() passed!");
    }

    /**
     * Method tests if specified quantity of products are removed from the inventory
     */
    @Test
    public void testRemoveStock() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Testing removeStock() method");
        inventory.addStock(product1,3);
        inventory.addStock(product2,10);
        inventory.addStock(product3,20);
        inventory.addStock(product4,2);
        assertTrue(inventory.removeStock(1,2));
        assertTrue(inventory.removeStock(2, 4));
        assertTrue(inventory.removeStock(3,3));
        assertTrue(inventory.removeStock(3,2));
        assertTrue(inventory.removeStock(4,2));
        assertEquals(1,inventory.getStock(1), "store.Product stock was not removed from the inventory");
        assertEquals(6,inventory.getStock(2), "store.Product stock was not removed from the inventory");
        assertEquals(15,inventory.getStock(3), "store.Product stock was not removed from the inventory");
        assertEquals(0,inventory.getStock(4), "store.Product stock was not removed from the inventory");
        System.out.println("Checking the stock removed of Sims Deluxe >>> Expected: 1   |  Actual: " + inventory.getStock(1));
        System.out.println("Checking the stock removed of Minecraft   >>> Expected: 6   |  Actual: " + inventory.getStock(2));
        System.out.println("Checking the stock removed of Mario Kart  >>> Expected: 15  |  Actual: " + inventory.getStock(3));
        System.out.println("Checking the stock removed of Cyberpunk   >>> Expected: 0   |  Actual: " + inventory.getStock(4));
        System.out.println("All tests for removeStock() passed!");
    }

    /**
     * Method that tests if the product information of a product from the inventory is returned
     */
    @Test
    public void testGetProductInfo() {
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Testing getProductInfo() method");
        inventory.addStock(product1,1);
        assertEquals(product1.getID(), inventory.getProductInfo(1).getID());
        assertEquals(product1.getName(), inventory.getProductInfo(1).getName());
        assertEquals(product1.getPrice(), inventory.getProductInfo(1).getPrice());
        System.out.println("store.Product info of Sims Deluxe >>> Expected ID: " + product1.getID() + " | Actual ID: " + inventory.getProductInfo(1).getID());
        System.out.println("                                         >>> Expected Name: " + product1.getName() + " | Actual Name: " + inventory.getProductInfo(1).getName());
        System.out.println("                                         >>> Expected Price: $" + product1.getPrice() + " | Actual Price: $" + inventory.getProductInfo(1).getPrice());

        inventory.addStock(product2,1);
        assertEquals(product2.getID(), inventory.getProductInfo(2).getID());
        assertEquals(product2.getName(), inventory.getProductInfo(2).getName());
        assertEquals(product2.getPrice(), inventory.getProductInfo(2).getPrice());
        System.out.println("store.Product info of Minecraft   >>> Expected ID: " + product2.getID() + " | Actual ID: " + inventory.getProductInfo(2).getID());
        System.out.println("                                         >>> Expected Name: " + product2.getName() + " | Actual Name: " + inventory.getProductInfo(2).getName());
        System.out.println("                                         >>> Expected Price: $" + product2.getPrice() + " | Actual Price: $" + inventory.getProductInfo(2).getPrice());


        inventory.addStock(product3,1);
        assertEquals(product3.getID(), inventory.getProductInfo(3).getID());
        assertEquals(product3.getName(), inventory.getProductInfo(3).getName());
        assertEquals(product3.getPrice(), inventory.getProductInfo(3).getPrice());
        System.out.println("store.Product info of Mario Kart  >>> Expected ID: " + product3.getID() + " | Actual ID: " + inventory.getProductInfo(3).getID());
        System.out.println("                                         >>> Expected Name: " + product3.getName() + " | Actual Name: " + inventory.getProductInfo(3).getName());
        System.out.println("                                         >>> Expected Price: $" + product3.getPrice() + " | Actual Price: $" + inventory.getProductInfo(3).getPrice());


        inventory.addStock(product4,1);
        assertEquals(product4.getID(), inventory.getProductInfo(4).getID());
        assertEquals(product4.getName(), inventory.getProductInfo(4).getName());
        assertEquals(product4.getPrice(), inventory.getProductInfo(4).getPrice());
        System.out.println("store.Product info of Cyberpunk   >>> Expected ID: " + product4.getID() + " | Actual ID: " + inventory.getProductInfo(4).getID());
        System.out.println("                                         >>> Expected Name: " + product4.getName() + " | Actual Name: " + inventory.getProductInfo(4).getName());
        System.out.println("                                         >>> Expected Price: $" + product4.getPrice() + " | Actual Price: $" + inventory.getProductInfo(4).getPrice());
        System.out.println("All tests for getProductInfo() passed!");
    }
}
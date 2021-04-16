// Dorothy Tran
// Cassidy Pacada
package myStore;

/**
 * Interface class for ProductStockContainer
 */
public interface ProductStockContainer {

    /**
     * Method that gets the amount of product
     * @param product Product, the type of product
     * @return int, quantity of products
     */
    int getProductQuantity(Product product);

    /**
     * Method that adds a specified quantity of product
     * @param product Product, the type of product
     * @param quantity int, quantity of products being added
     */
   void addProductQuantity(Product product, int quantity);

    /**
     * Method that removes a specified quantity of product
     * @param product Product, the type of product
     * @param quantity int, quantity of products being removed
     * @return boolean, whether the product was removed or not
     */
    boolean removeProductQuantity(Product product, int quantity);

    /**
     * Method that gets the number of products
     * @return int, specified quantity of products
     */
    int getNumOfProducts();
}

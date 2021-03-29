// Cassidy Pacada
// Dorothy Tran
package store;

import java.util.ArrayList;
/**
 * Inventory Class of the Virtual Store
 */
public class Inventory {
    private ArrayList<Integer> stock; // contains the number of items of each product
    private ArrayList<Product> product; // contains all products in inventory

    /** constructs the inventory with an empty arraylist for stock and an empty arraylist for product */
    public Inventory(){

        this.stock = new ArrayList<Integer>();
        this.product = new ArrayList<Product>();
    }

    /**
     * Method that iterates through product arraylist and returns the name of the product that has the same id as given product id
     * @param productID int, ID of the product
     * @return String, name of the product
     */
    public String getName(int productID){
        for(int i = 0; i < this.product.size(); i++){
            if(productID == this.product.get(i).getID()){
                return this.product.get(i).getName();
            }
        }
        return null; //product isn't in inventory
    }

    /**
     * This method returns the list of quantities of products in the inventory
     * @return an arraylist containing the stock values of each product in inventory
     */
    public ArrayList<Integer> getStockList(){
        return this.stock;
    }

    /**
     * This method returns the list of products in the inventory
     * @return an arraylist containing the products in inventory
     */
    public ArrayList<Product> getProductList(){
        return this.product;
    }

    /**
     * Method that iterates through product arraylist and returns the price of the product that has the same id as given product id
     * @param productID int, ID of the product
     * @return double, returns the price of the product that is in the inventory
     */
    public double getPrice(int productID){
        for(int i = 0; i < this.product.size(); i++){
            if(productID == this.product.get(i).getID()){
                return this.product.get(i).getPrice();
            }
        }
        return -1; //product isn't in inventory
    }

    /**
     * Method that iterates through product arraylist until it reaches index where product id matches given product id.
     * @param productID int, ID of the product
     * @return int, returns the amount of stock available in the inventory
     */
    public int getStock(int productID){
        for(int i = 0; i < this.product.size(); i++){
            if(productID == this.product.get(i).getID()){
                return this.stock.get(i); //stock of a product should be at same index as product index
            }
        }
        return -1; //product not in inventory
    }

    /**
     * Method that iterates through product arraylist until it reaches index where product id matches given product id
     * @param newProduct Product, adds a new product to inventory
     * @param numStock int, amount of stock being added to inventory
     */
    public void addStock(Product newProduct, int numStock) {
        int productIndex = -1;
        if(newProduct == null){
            return;
        }
        for (int i = 0; i < this.product.size(); i++) {
            if (newProduct.getID() == this.product.get(i).getID()) {
                productIndex = i;
            }
        }
        if (productIndex != -1) {
            this.stock.set(productIndex, numStock + this.stock.get(productIndex));
        }
        /** if the product isn't in inventory, it is added to the inventory*/
        else {
            this.stock.add(numStock);
            this.product.add(newProduct);
        }
    }

    /**
     * Method that iterates through product arraylist until it reaches index where product id matches given product id
     * @param productID int, ID of the product
     * @param stockRemoved int, amount of stock being removed from inventory
     * @return boolean,
     */
    public boolean removeStock(int productID, int stockRemoved){
        int productIndex = -1;
        for (int i = 0; i < this.product.size(); i++){
            if(productID == this.product.get(i).getID()){
                productIndex = i;
            }
        }
        if(productID != -1){
            /** if stock is a negative number, stock is set to 0 because it cannot be negative*/
            if(this.stock.get(productIndex)- stockRemoved < 0){
                this.stock.set(productIndex,0);
            }
            else{
                this.stock.set(productIndex, this.stock.get(productIndex) - stockRemoved);
            }
            return true;
        }
        else{
            return false; //product isn't in inventory
        }
    }

    /**
     * Method that iterates through a product ArrayList to get the product information
     * @param productID int, ID of the product
     * @return Product, returns the product
     */
    public Product getProductInfo(int productID) {
        for (int i = 0; i < product.size(); i++){
            if(productID == product.get(i).getID()){
                return product.get(i);
            }
        }
        return null;
    }
}

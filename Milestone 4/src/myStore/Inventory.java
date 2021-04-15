//Cassidy Pacada
//Dorothy Tran
package myStore;

import java.util.ArrayList;

/**
 * This class is the inventory of the virtual store and contains the information about the store's stock
 */
public class Inventory {
    private ArrayList<Integer> stock;
    private ArrayList<Product> product;

    /**
     * Inventory constructor that creates an empty arraylist for the products in the store and an empty arraylist for
     * the corresponding stock of the products
     */
    public Inventory(){
        this.stock = new ArrayList<Integer>();
        this.product = new ArrayList<Product>();
    }

    /**
     * Method that iterates through each product ID to check if it is the correct one
     * @param id int, ID of the desired product
     * @return boolean, whether or not the item is the correct product
     */
    public boolean checkExit(int id){
        for (Product value : this.product) {
            if (id == value.getID()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method that iterates through the product arraylist to get the name of the product with the specified product ID
     * @param productID int, ID of the desired product
     * @return String, name of the specified product
     */
    public String getName(int productID){                   //added in milestone 1
        for(int i = 0; i < this.product.size(); i++){
            if(productID == this.product.get(i).getID()){
                return this.product.get(i).getName();
            }
        }
        return null; //product not in inventory
    }

    /**
     * Method that iterates through product arraylist to get the price of the product with the specified product ID
     * @param productID int, ID of the desired product
     * @return double, returns the price of the specified product
     */
    public double getPrice(int productID){                  //added in milestone 1
        for(int i = 0; i < this.product.size(); i++){
            if (productID == this.product.get(i).getID()){
                return this.product.get(i).getPrice();
            }
        }
        return -1; //product not in inventory
    }

    /**
     * Method that iterates through product arraylist to get the index of the product with the specified product ID
     * and uses that index in the stock arraylist to return the stock value of the product
     * @param productID int, ID of the desired product
     * @return int, returns the amount of stock available in inventory for the specified product
     */
    public int getStock(int productID){                     //added in milestone 1
        for (int i = 0; i < this.product.size(); i++){
            if(productID == this.product.get(i).getID()){
                return this.stock.get(i);
            }
        }
        return -1; //product not in inventory
    }

    /**
     * Method that iterates through a product ArrayList to get all information about a product
     * @param productID int, ID of the desired product
     * @return Product, returns the product with the specified ID
     */
    public Product getProductInfo(int productID){           //added in milestone 2
        for (int i = 0; i < this.product.size(); i++){
            if(productID == this.product.get(i).getID()){
                return this.product.get(i);
            }
        }
        return null; //product not in inventory
    }

    /**
     * Method that returns a list containing the quantities of products in the inventory
     * @return arraylist containing the stock values of each product in inventory
     */
    public ArrayList<Integer> getStockList(){  //added in milestone 2
        return this.stock;
    }

    /**
     * Method that returns a list of products in the inventory
     * @return arraylist containing the products in inventory
     */
    public ArrayList<Product> getProductList(){             //added in milestone 2
        return this.product;
    }

    /**
     * Method that adds stock to a specified product. If product is not in inventory, adds stock and product to
     * inventory
     * @param newProduct Product to be added to inventory
     * @param numStock int, amount of stock to be added to inventory
     */
    public void addStock(Product newProduct, int numStock){     //added in milestone 1, edited in milestone 3
        int productIndex = -1;   //ensures productIndex is invalid index so no stock is added to the wrong product

        if(newProduct == null){  //prevent NullPointerException
            return;
        }

        for(int i = 0; i < this.product.size(); i++){
            if(newProduct.getID()== this.product.get(i).getID()){
                productIndex = i;
            }
        }
        if(productIndex != -1){  //product already exists in inventory, combine new stock with old stock
            this.stock.set(productIndex, numStock + this.stock.get(productIndex));
        }
        else{   //product not in inventory, append stock and product to respective lists
            this.stock.add(numStock);
            this.product.add(newProduct);
        }
    }

    /**
     * Method that removes a specified amount of stock for a specified product in the inventory
     * @param productID int, ID of the specified product
     * @param stockRemoved int, amount of product stock to be removed
     * @return boolean, whether or not the product was successfully removed
     */
    public boolean removeStock(int productID, int stockRemoved){    //added in milestone 1
        int productIndex = -1;  //ensures productIndex is invalid index so no stock is added to the wrong product

        for(int i = 0; i < this.product.size(); i++){
            if(productID == this.product.get(i).getID()){
                productIndex =i;
            }
        }

        if(productID != -1){  //if product is in inventory
            if(this.stock.get(productIndex) - stockRemoved < 0){  //if stock after removal is negative, set stock to 0
                this.stock.set(productIndex, 0);
            }
            else{
                this.stock.set(productIndex, this.stock.get(productIndex) - stockRemoved);
            }
            return true; //product removed successfully
        }
        else{ //product not in inventory
            return false;
        }
    }
}

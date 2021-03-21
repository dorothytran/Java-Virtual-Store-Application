// Written By: Cassidy Pacada 101143345
//Partner: Dorothy Tran 101141902

import java.util.ArrayList;
public class Inventory {
    private ArrayList<Integer> stock; // contains the number of items of each product
    private ArrayList<Product> product; // contains all products in inventory

    /** constructs the inventory with an empty arraylist for stock and an empty arraylist for product */
    public Inventory(){
        Product sims = new Product("Sims", 001, 40.0);
        Product minecraft = new Product("Minecraft",002,45.50);
        Product marioKart = new Product("Mario Kart", 003, 80.20);
        stock = new ArrayList<Integer>();
        product = new ArrayList<Product>();
        addStock(sims,10);
        addStock(minecraft, 10);
        addStock(marioKart, 10);
        ;    }

    /** iterates through product arraylist and returns the name of the product that has the same id as given product id */
    public String getName(int productID){
        for(int i = 0; i < product.size(); i++){
            if(productID == product.get(i).getID()){
                return product.get(i).getName();
            }
        }
        return null; //product isn't in inventory
    }

    /** iterates through product arraylist and returns the price of the product that has the same id as given product id */
    public double getPrice(int productID){
        for(int i = 0; i < product.size(); i++){
            if(productID == product.get(i).getID()){
                return product.get(i).getPrice();
            }
        }
        return -1; //product isn't in inventory
    }

    /** iterates through product arraylist until it reaches index where product id matches given product id.
     * goes to same index in stock arraylist and returns value */
    public int getStock(int productID){
        for(int i = 0; i < product.size(); i++){
            if(productID == product.get(i).getID()){
                return stock.get(i); //stock of a product should be at same index as product index
            }
        }
        return -1; //product not in inventory
    }

    /** iterates through product arraylist until it reaches index where product id matches given product id.
     * goes to same index in stock arraylist and adds numStock*/
    public void addStock(Product newProduct, int numStock) {
        int productIndex = -1;
        for (int i = 0; i < product.size(); i++) {
            if (newProduct.getID() == product.get(i).getID()) {
                productIndex = i;
            }
        }
        if (productIndex != -1) {
            stock.set(productIndex, numStock + stock.get(productIndex));
        }
        /** if the product isn't in inventory, it is added to the inventory*/
        else {
            stock.add(numStock);
            product.add(newProduct);
        }
    }

    /** iterates through product arraylist until it reaches index where product id matches given product id.
     * goes to same index in stock arraylist and subtracts numStock*/
    public boolean removeStock(int productID, int stockRemoved){
        int productIndex = -1;
        for (int i = 0; i < product.size(); i++){
            if(productID == product.get(i).getID()){
                productIndex = i;
            }
        }
        if(productID != -1){

            /** if stock is a negative number, stock is set to 0 because it cannot be negative*/
            if(stock.get(productIndex)-stockRemoved < 0){
                stock.set(productIndex,0);
            }

            else{
                stock.set(productIndex, stock.get(productIndex) - stockRemoved);
            }
            return true;
        }
        else{
            return false; //product isn't in inventory
        }

    }

}

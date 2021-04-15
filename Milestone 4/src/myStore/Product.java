//Dorothy Tran
//Cassidy Pacada

package myStore;

/**
 * This class contains the information about products being sold in hte store
 */
public class Product {
    private final String name;
    private final int id;
    private final double price;

    /**
     * Constructor for Product
     * @param name String value of the name of the specified product to be sold
     * @param id int value of the specified product to be sold
     * @param price double value of the cost of the specified product to be sold
     */
    public Product(String name, int id, double price){
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
     * Method that gets the name of the product
     * @return String value of the product name
     */
    public String getName(){
        return name;
    }

    /**
     * Method that gets the product ID
     * @return int value of the product id number
     */
    public int getID(){
        return id;
    }

    /**
     * Method that gets the product's price
     * @return double value of the price
     */
    public double getPrice(){
        return price;
    }

}

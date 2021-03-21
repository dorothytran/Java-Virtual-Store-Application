//Dorothy Tran 101141902//
// Cassidy Pacada 101143345//

public class Product {
    private final String name; // // Name of the product
    private final int id; // Product ID
    private final double price; // Price of the product


    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /* Get the name of the product */
    public String getName() {return name;}

    /* Get the ID of the product */
    public int getID() {return id;}

    /* Get the price of the product */
    public double getPrice() {return price;}

}
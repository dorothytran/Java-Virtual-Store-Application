// Cassidy Pacada
// Dorothy Tran
import java.util.Scanner;

/**
 * This class creates a user interface for the virtual store. The user adds and removes items to their carts from here
 * and they can head to checkout to buy their items.
 */
public class StoreView {
    private StoreManager storeManager;
    private int cartID;

    /**
     * This constructor creates a new store view with a store manager to handle the actions and a cart id to keep
     * track of customers
     * @param storeManager StoreManager, to handle user actions between cart and inventory
     * @param cartID cartID, to ensure that the products are being added and removed to the correct cart
     */
    public StoreView(StoreManager storeManager,int cartID){
        this.storeManager = storeManager;
        this.cartID = cartID;
    }

    /**
     *The main method simulates handling multiple customers at once
     * @param args
     */
    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreView sv1 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv2 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv3 = new StoreView(sm, sm.assignNewCartID());
        StoreView[] users = {sv1, sv2, sv3};
        int activeSV = users.length;
        Scanner sc = new Scanner(System.in);
        while(activeSV > 0){
            System.out.print("CHOOSE YOUR STOREVIEW >>> ");
            int choice = sc.nextInt();
            if(choice < users.length && choice >= 0){
                if(users[choice] != null){
                    if(users[choice].displayGUI()){
                        users[choice] = null;
                        activeSV--;
                    }
                }
                else{
                    System.out.println("MAIN > ERROR > BAD CHOICE\nTHAT STOREVIEW WAS DEACTIVATED");
                }
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");
    }

    /**
     * This method prints out the inventory items, their quantities, and their prices in a neat table
     */
    private void browseStock() {
        int stock;
        double price;
        String name;
        System.out.println("    Stock   |   Product Name    |   Unit Price  ]   Option  ");
        for (int i = 0; i < storeManager.getInventory().getStockList().size(); i++){
            stock = storeManager.getInventory().getStockList().get(i);
            price = storeManager.getInventory().getProductList().get(i).getPrice();
            name = storeManager.getInventory().getProductList().get(i).getName();
            System.out.println("    " + stock + "\t        " + name + "\t        " + price + "\t      ");
        }
    }

    /**
     * This method deals with the user interaction portion of the store. It displays certain aspects of the store
     * depending on the user input and it allows the user to do things with their cart such as add items, remove items,
     * check out of the store, and quit the store.
     * @return Returns true if the user is in the store. If the user exits the store, returns null
     */
    public boolean displayGUI(){
        Scanner sc = new Scanner(System.in);
        String productName;
        String command = "";
        int productAmount;
        Product product;

        while(!command.equalsIgnoreCase("y")){
            System.out.println("New Store View (y) >>>");
            System.out.println("Enter a command...");
            System.out.println("Type 'help' for a list of commands.");
            command = sc.nextLine();
            if(command.equalsIgnoreCase("browse")){
                System.out.println("-------------------------------BROWSE-------------------------------");
                browseStock();
            }
            else if(command.equalsIgnoreCase("y")){
                return false;
            }
            else if(command.equalsIgnoreCase("addtocart")){
                System.out.println("-------------------------------ADD-------------------------------");
                browseStock();
                System.out.println("Type desired product name");
                productName = sc.nextLine();
                for(int i = 0; i < storeManager.getInventory().getStockList().size(); i++){
                    if(storeManager.getInventory().getProductList().get(i).getName().equals(productName)) {
                        System.out.println(storeManager.getInventory().getProductList().get(i).getName());
                        System.out.println(productName);
                        product = storeManager.getInventory().getProductList().get(i);
                        if(storeManager.checkStock(product.getID()) > 0){
                            System.out.println("Type desired amount");
                            productAmount = sc.nextInt();
                            storeManager.addToCart(product, productAmount, this.cartID);
                            System.out.println("Item added");
                            for(Product p: this.storeManager.getShoppingCart(this.cartID).getProductList()) {
                                System.out.println(p.getName());
                            }
                        }
                        else{
                            System.out.println("Item is not available right now");
                        }

                    }
                }
            }
            else if(command.equalsIgnoreCase("removefromcart")){
                System.out.println("-------------------------------REMOVE-------------------------------");
                System.out.println("    Stock   |   Product Name    |   Unit Price  ]   Option  ");
                for (int i = 0; i < storeManager.getShoppingCart(this.cartID).getStockList().size(); i++){
                    int stock = storeManager.getShoppingCart(this.cartID).getStockList().get(i);
                    double price = storeManager.getShoppingCart(this.cartID).getProductList().get(i).getPrice();
                    String name = storeManager.getShoppingCart(this.cartID).getProductList().get(i).getName();
                    System.out.println("    " + stock + "\t        " + name + "\t        " + price + "\t      ");
                }
                System.out.println("Type name of product to be removed");
                productName = sc.nextLine();
                for(int i = 0; i < storeManager.getShoppingCart(this.cartID).getStockList().size(); i++) {
                    if (storeManager.getShoppingCart(this.cartID).getProductList().get(i).getName().equals(productName)) {
                        product = storeManager.getShoppingCart(this.cartID).getProductList().get(i);
                        if (storeManager.getShoppingCart(this.cartID).getStock(product.getID()) > 0) {
                            System.out.println("Type in amount to be removed");
                            productAmount = sc.nextInt();
                            storeManager.removeFromCart(product, productAmount, this.cartID);
                            System.out.println("Item removed");
                        }
                        else{
                            System.out.println("Item is not in cart");
                        }
                    }
                }
            }
            else if(command.equals("help")){
                System.out.println("Type 'browse' to see store products");
                System.out.println("Type 'addtocart' to add items to shopping cart");
                System.out.println("Type 'removefromcart' to remove items from shopping cart");
                System.out.println("Type 'buy' to proceed with the transaction");
                System.out.println("Type 'quit' to exit the store");
            }
            else if(command.equals("buy")){
                storeManager.transaction(storeManager.getShoppingCart(this.cartID).getProductList(), storeManager.getShoppingCart(this.cartID).getStockList());
                break;
            }

            else if(command.equals("quit")){
                for(int i = 0; i < storeManager.getShoppingCart(this.cartID).getStockList().size(); i++){
                    product = storeManager.getShoppingCart(this.cartID).getProductList().get(i);
                    productAmount = storeManager.getShoppingCart(this.cartID).getStock(product.getID());
                    storeManager.removeFromCart(product, productAmount, this.cartID);
                }
                break;
            }

        }
        return true;
    }
}

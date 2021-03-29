// Written by: Dorothy Tran
// Partner: Cassidy Pacada

public class StoreManager {

    private Inventory inventory;

    public StoreManager() {
        inventory = new Inventory();
    }

    public StoreManager(Inventory storeInventory) {
        inventory = storeInventory;
    }

    /** Checks the quantity of stock in store inventory */
    public int checkStock(int productID) {
        return inventory.getStock(productID);
    }

    public double transaction(int[][] cart) {

        double totalCost = 0;

        /** Checks inventory stock quantity */
        for(int i = 0; i < cart.length; i++) {

            int productID, quantity;
            productID = cart[i][0];
            quantity = cart[i][1];

            /** If transaction is unsuccessful */
            if (checkStock(productID) < quantity) {
                return -1;
            }
        }
        /** If transaction is successful */
        for(int i = 0; i < cart.length; i++) {

            int productID, quantity;
            productID = cart[i][0]; // places product ID in first array
            quantity = cart[i][1]; // places the quantity being purchased in second array
            totalCost = (inventory.getPrice(productID) * quantity);
            inventory.removeStock(quantity, productID);
        }
        return totalCost;
    }
}
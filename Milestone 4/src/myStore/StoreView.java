//Cassidy Pacada
//Dorothy Tran
package myStore;

import java.text.DecimalFormat;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This Class runs and displays the virtual store for the customer's use
 */
public class StoreView {
    private StoreManager storeManager;
    private int cartID;
    private final JFrame frame;
    private JPanel[] itemPanels;
    private JButton[] plusButtons;
    private JButton[] minusButtons;
    private JLabel cartContents;
    private Image img1, img2,img3,img4;
    private JLabel[] imageLabels;
    private JLabel[] productInfo;

    /**
     * Constructor for the virtual store, Creates the store manager, the identification for this particular user's cart,
     * the window for the store, adds images to the store for each of the products, and adds as panel display panels and
     * buttons as there are items in the store
     * @param storeManager StoreManager, to handle user action between cart and inventory
     * @param cartID cartID, ensures that the correct cart is being handled
     */
    public StoreView(StoreManager storeManager, int cartID){
        this.storeManager = storeManager;
        this.cartID = cartID;
        this.frame = new JFrame();

        imageLabels = new JLabel[storeManager.getInventory().getStockList().size()];
        img1 = new ImageIcon(getClass().getResource("Minecraft.JPG")).getImage().getScaledInstance(160, 225, Image.SCALE_DEFAULT);
        imageLabels[1] = new JLabel(new ImageIcon(img1));
        img2 = new ImageIcon(getClass().getResource("Cyberpunk.JPG")).getImage().getScaledInstance(160, 225,Image.SCALE_DEFAULT);
        imageLabels[3] = new JLabel(new ImageIcon(img2));
        img3 = new ImageIcon(getClass().getResource("Sims Deluxe.JPG")).getImage().getScaledInstance(160, 225,Image.SCALE_DEFAULT);
        imageLabels[0] = new JLabel(new ImageIcon(img3));
        img4 = new ImageIcon(getClass().getResource("Mario Kart.JPG")).getImage().getScaledInstance(160, 225,Image.SCALE_DEFAULT);;
        imageLabels[2] = new JLabel(new ImageIcon(img4));

        itemPanels = new JPanel[storeManager.getInventory().getStockList().size()];
        plusButtons = new JButton[storeManager.getInventory().getStockList().size()];
        minusButtons = new JButton[storeManager.getInventory().getStockList().size()];
        productInfo = new JLabel[storeManager.getInventory().getStockList().size()];
        cartContents = new JLabel();
        cartContents.setHorizontalAlignment(SwingConstants.CENTER);
        cartContents.setFont(new Font("Futura", Font.BOLD, 14));

        for(int i = 0; i < storeManager.getInventory().getStockList().size(); i++){
            itemPanels[i] = new JPanel(new BorderLayout());
            plusButtons[i] = new JButton("+");
            minusButtons[i] = new JButton("-");
            productInfo[i] = new JLabel(displayProduct(i));
            productInfo[i].setHorizontalAlignment(SwingConstants.CENTER);
            productInfo[i].setFont(new Font("Futura",Font.BOLD,14));
        }
    }

    /**
     * Updates the cart view whenever the user adds or removes something from the cart
     */
    private void updateCart(){
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuilder s = new StringBuilder("<html>CART VIEW <br>");

        for (int i=0; i<storeManager.getShoppingCart(this.cartID).getProductList().size(); i++){
            s.append(storeManager.getShoppingCart(this.cartID).getProductList().get(i).getName()).append(" (")
                    .append(storeManager.getShoppingCart(this.cartID).getStockList().get(i)).append("): ")
                    .append(String.format(" $"))
                    .append(df.format(((storeManager.getShoppingCart(this.cartID).getProductList().get(i).getPrice()))*(storeManager.getShoppingCart(this.cartID).getStockList().get(i))))
                    .append("<br>");
        }
        s.append("</html>");

        cartContents.setText(s.toString());
    }

    /**
     * Returns the name, price, and quantity of a product in inventory to be displayed in a label
     * @param productIndex int, the index of the specific product whose info is being displayed
     * @return String, contains the formatted name, amount, and price of product
     */
    private String displayProduct(int productIndex){
        String name = storeManager.getInventory().getProductList().get(productIndex).getName();
        int amount = storeManager.getInventory().getStockList().get(productIndex);
        double price = storeManager.getInventory().getProductList().get(productIndex).getPrice();

        return new StringBuilder().append(name).append(" (").append(amount).append("):").append(" $").append(price)
                .append(" each").toString();
    }

    /**
     * Returns the customer's receipt of what they bought, how much they bought, and their transaction total
     * @return String, containing their transaction information
     */
    private String checkoutPrinting(){
        StringBuilder s = new StringBuilder("Transaction: Click Yes to Continue with Checkout \n");
        double total = 0;
        //Loop through shopping cart and add to string
        for (int i=0; i<storeManager.getShoppingCart(this.cartID).getProductList().size(); i++){
            s.append(storeManager.getShoppingCart(this.cartID).getProductList().get(i).getName()).append(" (")
                    .append(storeManager.getShoppingCart(this.cartID).getStockList().get(i)).append(") ").append("\t \t   $")
                    .append(storeManager.getShoppingCart(this.cartID).getProductList().get(i).getPrice()).append(" Each \n");
            total += storeManager.getShoppingCart(this.cartID).getProductList().get(i).getPrice() * storeManager.getShoppingCart(this.cartID).getStockList().get(i);
        }
        s.append("Total is $").append(total);
        //print contents
        //print total
        return s.toString();
    }

    /**
     *JButton that prompts the user if they want to exist the store. If they do, exits the program.
     * @return JButton that allows the user to quit the program
     */
    private JButton quitButton(){
        JButton button = new JButton("QUIT");
        button.addActionListener(new ActionListener()   {
            @Override
            public void actionPerformed(ActionEvent ae){
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        return button;
    }

    /**
     * JButton that creates a popul that displays the user's cart and the total amount of the transaction. Prompts the
     * user if they wish to continue with checkout. If the user selects OK, program ends.
     * @return
     */
    private JButton checkoutButton(){
        JButton button = new JButton("CHECKOUT");
        button.addActionListener(new ActionListener()   {
            @Override
            public void actionPerformed(ActionEvent ae){
                if (JOptionPane.showConfirmDialog(frame, checkoutPrinting())
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        return button;
    }

    /**
     *Creates a window containing the virtual store including the products, the cart view, the quit and checkout buttons,
     * the addtocart buttons, and the remove from cart buttons.
     */
    public void displayGUI(){
        frame.setTitle("The Game Store");
        for(int i =0; i<plusButtons.length; i++) {
            final int j = i;
            plusButtons[i].addActionListener(new ActionListener() {
                @Override
                /**
                 *Adds one of the displayed product to the user's cart. Disables button if product is no longer in
                 * inventory
                 */
                public void actionPerformed(ActionEvent e) {
                    storeManager.addToCart(storeManager.getInventory().getProductList().get(j), 1, cartID);
                    minusButtons[j].setEnabled(true);
                    if (storeManager.getInventory().getStockList().get(j) == 0) {
                        plusButtons[j].setEnabled(false);
                    }
                    updateCart();
                    productInfo[j].setText(displayProduct(j));
                }
            });
            minusButtons[i].setEnabled(false);
            minusButtons[i].addActionListener(new ActionListener() {
                @Override
                /**
                 * Removes one of the displayed produdct from the user's cart. Disables button if product is no longer
                 * in shopping cart
                 */
                public void actionPerformed(ActionEvent e) {
                    storeManager.removeFromCart(storeManager.getInventory().getProductList().get(j), 1, cartID);
                    plusButtons[j].setEnabled(true);
                    if (!storeManager.getShoppingCart(cartID).checkExit(storeManager.getInventory().getProductList().get(j).getID())) {
                        minusButtons[j].setEnabled(false);
                    }
                    updateCart();
                    productInfo[j].setText(displayProduct(j));
                }
            });
        }
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel(new BorderLayout());
        JPanel bodyPanel = new JPanel(new BorderLayout());
        JPanel browsePanel = new JPanel(new GridLayout(storeManager.getInventory().getStockList().size()/2, 2));
        JPanel menuPanel = new JPanel(new BorderLayout());
        JPanel menuButtons = new JPanel(new GridLayout(3,1));
        JPanel cartViewPanel = new JPanel(new BorderLayout());

        JButton quitButton = quitButton();
        JButton checkoutButton = checkoutButton();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,15,5,15));
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        headerPanel.setPreferredSize(new Dimension(800, 40));
        bodyPanel.setPreferredSize(new Dimension(800, 600));
        browsePanel.setPreferredSize(new Dimension(500, 600));
        cartViewPanel.setPreferredSize(new Dimension(250, 300));

        quitButton.setPreferredSize(new Dimension(150, 30));
        checkoutButton.setPreferredSize(new Dimension(150, 30));

        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);

        bodyPanel.add(browsePanel, BorderLayout.WEST);
        bodyPanel.add(menuPanel, BorderLayout.EAST);

        menuPanel.add(menuButtons,BorderLayout.PAGE_START);
        menuPanel.add(cartViewPanel, BorderLayout.CENTER);
        cartViewPanel.add(cartContents,BorderLayout.CENTER);

        menuButtons.add(checkoutButton);
        menuButtons.add(quitButton);

        for(int i = 0; i < storeManager.getInventory().getStockList().size(); i++){
            JPanel picPanel = new JPanel();
            JPanel buttonPanel = new JPanel();
            browsePanel.add(itemPanels[i]);
            itemPanels[i].add(productInfo[i], BorderLayout.PAGE_START);
            itemPanels[i].add(picPanel, BorderLayout.CENTER);
            itemPanels[i].add(buttonPanel, BorderLayout.PAGE_END);
            itemPanels[i].add(imageLabels[i]);
            itemPanels[i].setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
            buttonPanel.add(minusButtons[i]);
            buttonPanel.add(plusButtons[i]);
        }
        JLabel headerLabel = new JLabel("DORO AND CASS' GAME STORE");
        JLabel idLabel = new JLabel("ID: 0");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Futura", Font.BOLD, 18));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(idLabel, BorderLayout.PAGE_END);

        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Exit store?")
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        frame.setVisible(true);
    }

    /**
     * Creates an instance of a customer and opens the store
     * @param args
     */
    public static void main(String[] args){
        StoreManager sm = new StoreManager();
        StoreView sv = new StoreView(sm, sm.assignNewCartID());
        sv.displayGUI();

    }
}

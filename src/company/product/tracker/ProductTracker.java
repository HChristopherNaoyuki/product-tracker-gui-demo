package company.product.tracker;

import java.awt.*;
import javax.swing.*;

public class ProductTracker extends JFrame
{
    private final ProductManager productManager;        // Instance of ProductManager to handle product data
    private JTextField productIdField;            // Field for entering product ID
    private JTextField productNameField;          // Field for entering product name
    private JTextField productPriceField;         // Field for entering product price
    private JTextArea productDetailsArea;         // Area to display product details
    private JButton submitButton;                  // Button for submitting new products
    private JButton searchButton;                  // Button for searching products

    // Constructor to set up the main frame
    public ProductTracker()
    {
        super("Product Tracker");
        setLookAndFeel();                           // Set the look and feel of the GUI
        setLayout(new BorderLayout());              // Use BorderLayout for the main frame

        // Initialize GUI components
        initializeComponents();
        
        // Create and layout panels
        createPanels();
        
        // Initialize ProductManager and load existing products
        productManager = ProductManager.getInstance("Products.txt", productDetailsArea);
        productManager.loadProductDetails();
        
        // Add action listeners for buttons
        addActionListeners();
    }

    // Set the look and feel for the GUI
    private void setLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();                    // Print stack trace in case of an error
        }
    }

    // Initialize input fields and buttons
    private void initializeComponents()
    {
        productIdField = new JTextField(10);        // Field for product ID
        productNameField = new JTextField(20);      // Field for product name
        productPriceField = new JTextField(10);     // Field for product price

        productDetailsArea = new JTextArea(10, 20); // Text area for displaying product details
        productDetailsArea.setEditable(false);       // Make it non-editable
        productDetailsArea.setLineWrap(true);        // Enable line wrapping
        productDetailsArea.setWrapStyleWord(true);   // Wrap at word boundaries
        productDetailsArea.setBackground(new Color(250, 250, 250)); // Set background color
        productDetailsArea.setFont(new Font("San Francisco", Font.PLAIN, 14)); // Set font

        submitButton = new JButton("Submit");        // Button to submit new product
        searchButton = new JButton("Search");        // Button to search for a product

        // Customize buttons
        customizeButton(submitButton);
        customizeButton(searchButton);
    }

    // Create and arrange panels for input fields, buttons, and details area
    private void createPanels()
    {
        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        inputPanel.add(new JLabel("Product ID:"));
        inputPanel.add(productIdField);
        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(productNameField);
        inputPanel.add(new JLabel("Product Price:"));
        inputPanel.add(productPriceField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(submitButton);
        buttonPanel.add(searchButton);

        // Panel for product details
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
        detailsPanel.add(new JScrollPane(productDetailsArea), BorderLayout.CENTER);

        // Add panels to the main frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.SOUTH);
    }

    // Customize button appearance
    private void customizeButton(JButton button)
    {
        button.setBackground(new Color(0, 122, 255)); // Set button background color
        button.setForeground(Color.WHITE);            // Set button text color
        button.setFocusPainted(false);                 // Remove focus paint
        button.setFont(new Font("San Francisco", Font.BOLD, 14)); // Set button font
        button.setPreferredSize(new Dimension(100, 40)); // Set preferred size
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Set border
    }

    // Add action listeners for the buttons
    private void addActionListeners()
    {
        submitButton.addActionListener(e -> 
        {
            // Save the product details and clear fields
            productManager.saveProduct(
                productIdField.getText(),
                productNameField.getText(),
                productPriceField.getText()
            );
            clearFields();
        });

        searchButton.addActionListener(e -> 
        {
            // Search for a product by ID
            productManager.searchProduct();
        });
    }

    // Clear input fields after submission
    private void clearFields()
    {
        productIdField.setText("");               // Clear product ID field
        productNameField.setText("");             // Clear product name field
        productPriceField.setText("");            // Clear product price field
    }

    // Main method to run the application
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> 
        {
            ProductTracker tracker = new ProductTracker(); // Create an instance of ProductTracker
            tracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
            tracker.pack();                                  // Pack components
            tracker.setVisible(true);                        // Make frame visible
            tracker.setLocationRelativeTo(null);            // Center frame on screen
        });
    }
}


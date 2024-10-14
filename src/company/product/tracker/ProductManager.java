package company.product.tracker;

import java.io.*;
import javax.swing.*;

public class ProductManager
{
    private static ProductManager instance;  // Singleton instance of ProductManager
    private File productsFile;                // File to store product details
    private JTextArea productDetailsArea;     // Text area to display product details

    // Private constructor to initialize the products file and text area
    private ProductManager(String filename, JTextArea productDetailsArea)
    {
        this.productsFile = new File(filename);
        this.productDetailsArea = productDetailsArea;
    }

    // Singleton access method
    public static synchronized ProductManager getInstance(String filename, JTextArea productDetailsArea)
    {
        // Create a new instance only if it doesn't already exist
        if (instance == null)
        {
            instance = new ProductManager(filename, productDetailsArea);
        }
        return instance;
    }

    // Load product details from the file and display them in the JTextArea
    public void loadProductDetails()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(productsFile)))
        {
            String line;

            // Read each line from the file
            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",");  // Split the line by comma
                productDetailsArea.append("Product ID: " + fields[0] + "\n" +
                                           "Product Name: " + fields[1] + "\n" +
                                           "Product Price: R " + fields[2] + "\n\n");
            }
        }
        catch (IOException e)
        {
            showErrorDialog("Error loading product details");
        }
    }

    // Save a new product to the file and display it in the JTextArea
    public void saveProduct(String id, String name, String price)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(productsFile, true)))
        {
            // Write the product details as a new line in the file
            writer.println(id + "," + name + "," + price);
            productDetailsArea.append("Product ID: " + id + "\n" +
                                       "Product Name: " + name + "\n" +
                                       "Product Price: R " + price + "\n\n");
        }
        catch (IOException e)
        {
            showErrorDialog("Error saving product details");
        }
    }

    // Search for a product by its ID and display its details
    public void searchProduct()
    {
        String productId = JOptionPane.showInputDialog("Enter product ID:");

        try (BufferedReader reader = new BufferedReader(new FileReader(productsFile)))
        {
            String line;

            // Read each line from the file
            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",");

                // Check if the product ID matches
                if (fields[0].equals(productId))
                {
                    JOptionPane.showMessageDialog(null, "Product Name: " + fields[1] +
                                                      "\nProduct Price: R " + fields[2]);
                    return;  // Exit the method if the product is found
                }
            }
            // Show a message if the product is not found
            JOptionPane.showMessageDialog(null, "Product not found");
        }
        catch (IOException e)
        {
            showErrorDialog("Error searching product");
        }
    }

    // Show an error dialog with a given message
    private void showErrorDialog(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

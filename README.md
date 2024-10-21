# Product Tracker

## Overview

The **Product Tracker** is a Java Swing application that allows users to manage product details efficiently. It provides functionalities to add new products, search for existing products, and display product information. The product details are stored in a text file, making it easy to access and manage the data.

## Features

- **Add New Products**: Users can input product ID, name, and price to save new products.
- **Search Products**: Users can search for products by their ID and view the details.
- **Display Product Details**: A text area displays all the product details, making it easy to review existing entries.

## Requirements

- Java Development Kit (JDK) 8 or higher
- An IDE that supports Java (e.g., IntelliJ IDEA, Eclipse)

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd product-tracker
   ```

2. **Compile the Application**:
   Make sure your JDK is set up correctly. You can compile the application using:
   ```bash
   javac -d bin src/company/product/tracker/*.java
   ```

3. **Run the Application**:
   Navigate to the `bin` directory and execute the main class:
   ```bash
   java company.product.tracker.ProductTracker
   ```

## File Structure

- `src/`: Contains all the source code for the application.
  - `company/product/tracker/ProductManager.java`: Manages product data, including loading, saving, and searching.
  - `company/product/tracker/ProductTracker.java`: The main GUI class that provides the user interface.
  
- `Products.txt`: The text file where product details are stored.

## Usage

- Upon launching the application, you can input product details in the respective fields.
- Click the **Submit** button to add a new product. The details will be displayed in the text area.
- Click the **Search** button to find a product by ID. A dialog box will show the product name and price if found.

## Notes

- Ensure that the `Products.txt` file exists in the project directory. If it doesn't, the application will create it when saving the first product.
- The application currently supports simple product management and can be expanded for additional functionalities in the future.

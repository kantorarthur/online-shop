package GUI;

import DataAccess.ProductDataAccess;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductsFrame extends JFrame {

    public ProductsFrame() {

        this.setSize(1200, 850);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        JTextField productNameField = new JTextField();
        JTextField productPriceField = new JTextField();
        JTextField productQuantityInStockField = new JTextField();

        JTextField productIdField = new JTextField();

        JTextField productId2Field = new JTextField();
        JTextField productName2Field = new JTextField();

        JTextField productId3Field = new JTextField();
        JTextField productPrice2Field = new JTextField();

        JTextField productId4Field = new JTextField();
        JTextField productQuantityInStock2Field = new JTextField();

        productNameField.setToolTipText("Enter product name");
        productPriceField.setToolTipText("Enter product price");
        productQuantityInStockField.setToolTipText("Enter quantity in stock");

        productIdField.setToolTipText("Enter product ID");

        productId2Field.setToolTipText("Enter product ID");
        productName2Field.setToolTipText("Enter new product name");

        productId3Field.setToolTipText("Enter product ID");
        productPrice2Field.setToolTipText("Enter new price");

        productId4Field.setToolTipText("Enter product ID");
        productQuantityInStock2Field.setToolTipText("Enter new stock quantity");

        JButton addProductBtn = new JButton("Add Product");
        JButton deleteProductBtn = new JButton("Delete Product");
        JButton editNameBtn = new JButton("Edit Name");
        JButton editPriceBtn = new JButton("Edit Price");
        JButton editQuantityInStockBtn = new JButton("Edit Stock");
        JButton viewAllProductsBtn = new JButton("View Products");

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(addProductBtn, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 2;
        panel.add(productNameField, gbc);

        gbc.gridx = 3;
        panel.add(new JLabel("Price:"), gbc);

        gbc.gridx = 4;
        panel.add(productPriceField, gbc);

        gbc.gridx = 5;
        panel.add(new JLabel("Stock:"), gbc);

        gbc.gridx = 6;
        panel.add(productQuantityInStockField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(deleteProductBtn, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("Product ID:"), gbc);

        gbc.gridx = 2;
        panel.add(productIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(editNameBtn, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("Product ID:"), gbc);

        gbc.gridx = 2;
        panel.add(productId2Field, gbc);

        gbc.gridx = 3;
        panel.add(new JLabel("New Name:"), gbc);

        gbc.gridx = 4;
        panel.add(productName2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(editPriceBtn, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("Product ID:"), gbc);

        gbc.gridx = 2;
        panel.add(productId3Field, gbc);

        gbc.gridx = 3;
        panel.add(new JLabel("New Price:"), gbc);

        gbc.gridx = 4;
        panel.add(productPrice2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(editQuantityInStockBtn, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("Product ID:"), gbc);

        gbc.gridx = 2;
        panel.add(productId4Field, gbc);

        gbc.gridx = 3;
        panel.add(new JLabel("New Stock:"), gbc);

        gbc.gridx = 4;
        panel.add(productQuantityInStock2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(viewAllProductsBtn, gbc);

        panel.setPreferredSize(new Dimension(1200, 260));

        JPanel centerPanel = new JPanel(new BorderLayout());
        JScrollPane textScrollPane = new JScrollPane(outputArea);
        centerPanel.add(textScrollPane, BorderLayout.CENTER);

        addProductBtn.addActionListener(e -> {
            String productName = productNameField.getText();
            double productPrice = Double.parseDouble(productPriceField.getText());
            int productQuantityInStock = Integer.parseInt(productQuantityInStockField.getText());

            Product product = new Product(productQuantityInStock, productPrice, productName);
            ProductDataAccess.insert(product);

            outputArea.setText(
                    "Product with ID: " +
                            product.getId() +
                            ", Name: " +
                            product.getName() +
                            ", Price: " +
                            product.getPrice() +
                            ", Stock: " +
                            product.getQuantityInStock() +
                            " has been added!"
            );
        });

        deleteProductBtn.addActionListener(e -> {
            int productId = Integer.parseInt(productIdField.getText());
            outputArea.setText(ProductDataAccess.delete(productId));
        });

        editNameBtn.addActionListener(e -> {
            int productId = Integer.parseInt(productId2Field.getText());
            String newName = productName2Field.getText();
            outputArea.setText(ProductDataAccess.updateName(productId, newName));
        });

        editPriceBtn.addActionListener(e -> {
            int productId = Integer.parseInt(productId3Field.getText());
            double newPrice = Double.parseDouble(productPrice2Field.getText());
            outputArea.setText(ProductDataAccess.updatePrice(productId, newPrice));
        });

        editQuantityInStockBtn.addActionListener(e -> {
            int productId = Integer.parseInt(productId4Field.getText());
            int newStock = Integer.parseInt(productQuantityInStock2Field.getText());
            outputArea.setText(ProductDataAccess.updateStock(productId, newStock));
        });

        viewAllProductsBtn.addActionListener(e -> {

            List<Product> products = ProductDataAccess.findAll();

            JTable table = TableGenerator.createTable(products);
            JScrollPane tableScroll = new JScrollPane(table);

            centerPanel.removeAll();
            centerPanel.setLayout(new GridLayout(2, 1));
            centerPanel.add(tableScroll);
            centerPanel.add(textScrollPane);

            centerPanel.revalidate();
            centerPanel.repaint();
        });

        this.add(panel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
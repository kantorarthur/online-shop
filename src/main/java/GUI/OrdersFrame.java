package GUI;

import BusinessLogic.OrdersManagement;
import DataAccess.ClientDataAccess;
import DataAccess.ProductDataAccess;
import Model.Client;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrdersFrame extends JFrame {

    private JTable clientsTable;
    private JTable productsTable;

    private int selectedClientId = -1;
    private int selectedProductId = -1;

    private OrdersManagement ordersManagement;

    public OrdersFrame() {
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.ordersManagement = new OrdersManagement();

        List<Client> clients = ClientDataAccess.findAll();
        List<Product> products = ProductDataAccess.findAll();

        clientsTable = TableGenerator.createTable(clients);
        productsTable = TableGenerator.createTable(products);

        JPanel tablesPanel = new JPanel(new GridLayout(1, 2, 20, 0));

        JPanel clientPanel = new JPanel(new BorderLayout());
        clientPanel.add(new JLabel("Select Client:", SwingConstants.CENTER), BorderLayout.NORTH);
        clientPanel.add(new JScrollPane(clientsTable), BorderLayout.CENTER);

        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.add(new JLabel("Select Product:", SwingConstants.CENTER), BorderLayout.NORTH);
        productPanel.add(new JScrollPane(productsTable), BorderLayout.CENTER);

        tablesPanel.add(clientPanel);
        tablesPanel.add(productPanel);
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel actionPanel = new JPanel(new FlowLayout());
        JLabel quantityLabel = new JLabel("Desired quantity:");
        JTextField quantityField = new JTextField(5);
        JButton placeOrderBtn = new JButton("Place order");

        actionPanel.add(quantityLabel);
        actionPanel.add(quantityField);
        actionPanel.add(placeOrderBtn);

        JTextArea outputArea = new JTextArea(5, 50);
        outputArea.setEditable(false);
        JScrollPane logScroll = new JScrollPane(outputArea);

        bottomPanel.add(actionPanel, BorderLayout.NORTH);
        bottomPanel.add(logScroll, BorderLayout.CENTER);



        clientsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
            {
                int selectedRow = clientsTable.getSelectedRow();
                if (selectedRow != -1)
                {
                    selectedClientId = (int) clientsTable.getValueAt(selectedRow, 0);
                    outputArea.append("Client selected with ID: " + selectedClientId + "\n");
                }
            }
        });

        productsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = productsTable.getSelectedRow();
                if (selectedRow != -1)
                {
                    selectedProductId = (int) productsTable.getValueAt(selectedRow, 2);
                    outputArea.append("Product selected with ID: " + selectedProductId + "\n");
                }
            }
        });

        placeOrderBtn.addActionListener(e -> {
            if (selectedClientId == -1 || selectedProductId == -1)
            {
                outputArea.append("Error: Please select a client and a product!\n");
                return;
            }

            try
            {
                int quantity = Integer.parseInt(quantityField.getText());
                if (quantity <= 0)
                {
                    outputArea.append("Error: Quantity have to be greater than 0!\n");
                    return;
                }

                String resultMessage = ordersManagement.createOrder(selectedClientId,selectedProductId,quantity);
                outputArea.append(resultMessage + "\n");

                refreshTabel();

            }
            catch (NumberFormatException ex)
            {
                outputArea.append("Error: Insert an integer number!\n");
            }
        });

        this.add(tablesPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void refreshTabel()
    {
        List<Client> clients = ClientDataAccess.findAll();
        List<Product> products = ProductDataAccess.findAll();

        clientsTable.setModel(TableGenerator.createTable(clients).getModel());
        productsTable.setModel(TableGenerator.createTable(products).getModel());
    }
}
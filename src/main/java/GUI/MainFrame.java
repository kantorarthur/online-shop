

package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    /**
     * From this frame we can access the other frames(Bill frame, client frame, order frame and products frame)
     */
    public MainFrame()
    {
        this.setSize(850, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(10, 1));

        JButton clientsManagementBtn = new JButton("Clients Management");
        JButton productsManagementBtn = new JButton("Products Management");
        JButton ordersManagementBtn = new JButton("Create Order");
        JButton viewAllBillsBtn = new JButton("Bills Management");

        panel.add(clientsManagementBtn);
        panel.add(productsManagementBtn);
        panel.add(ordersManagementBtn);
        panel.add(viewAllBillsBtn);

        clientsManagementBtn.addActionListener(e -> {
                new ClientsFrame();
        });

        productsManagementBtn.addActionListener(e -> {
                 new ProductsFrame();
        });

        ordersManagementBtn.addActionListener(e -> {
                new OrdersFrame();
        });

        viewAllBillsBtn.addActionListener(e -> {
                new BillsFrame();
        });


        this.add(panel);
        this.setVisible(true);
    }

}

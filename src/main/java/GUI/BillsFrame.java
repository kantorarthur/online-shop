package GUI;


import BusinessLogic.OrdersManagement;
import DataAccess.LogDataAccess;

import DataAccess.OrderDataAccess;
import Model.Bill;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BillsFrame extends JFrame{
    public BillsFrame()
    {
        this.setSize(850, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JTextArea outputArea = new JTextArea();

        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 2));

        List<Bill> bills = LogDataAccess.findAll();

        JTable table = TableGenerator.createTable(bills);
        JScrollPane tableScroll = new JScrollPane(table);
        JScrollPane textScrollPane = new JScrollPane(outputArea);
        centerPanel.setLayout(new GridLayout(2, 1));
        centerPanel.add(tableScroll);
        centerPanel.add(textScrollPane);



        panel.setPreferredSize(new Dimension(850, 90));
        this.add(panel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}

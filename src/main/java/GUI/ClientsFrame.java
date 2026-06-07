package GUI;

import DataAccess.ClientDataAccess;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientsFrame extends JFrame {

    public ClientsFrame() {

        this.setSize(850, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        JTextField clientNameField = new JTextField();
        JTextField clientEmailField = new JTextField();

        JTextField clientIdField = new JTextField();

        JTextField clientId2Field = new JTextField();
        JTextField clientName2Field = new JTextField();

        JTextField clientId3Field = new JTextField();
        JTextField clientEmail2Field = new JTextField();

        clientNameField.setToolTipText("Enter client name");
        clientEmailField.setToolTipText("Enter client email");

        clientIdField.setToolTipText("Enter client ID");

        clientId2Field.setToolTipText("Enter client ID");
        clientName2Field.setToolTipText("Enter new client name");

        clientId3Field.setToolTipText("Enter client ID");
        clientEmail2Field.setToolTipText("Enter new email");

        JButton addClientBtn = new JButton("Add Client");
        JButton deleteClientBtn = new JButton("Delete Client");
        JButton editNameBtn = new JButton("Edit Name");
        JButton editEmailBtn = new JButton("Edit Email");
        JButton viewAllClientsBtn = new JButton("View All Clients");

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(addClientBtn, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 2;
        panel.add(clientNameField, gbc);

        gbc.gridx = 3;
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 4;
        panel.add(clientEmailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(deleteClientBtn, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("Client ID:"), gbc);

        gbc.gridx = 2;
        panel.add(clientIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(editNameBtn, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("Client ID:"), gbc);

        gbc.gridx = 2;
        panel.add(clientId2Field, gbc);

        gbc.gridx = 3;
        panel.add(new JLabel("New Name:"), gbc);

        gbc.gridx = 4;
        panel.add(clientName2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(editEmailBtn, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel("Client ID:"), gbc);

        gbc.gridx = 2;
        panel.add(clientId3Field, gbc);

        gbc.gridx = 3;
        panel.add(new JLabel("New Email:"), gbc);

        gbc.gridx = 4;
        panel.add(clientEmail2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(viewAllClientsBtn, gbc);

        panel.setPreferredSize(new Dimension(850, 220));

        JPanel centerPanel = new JPanel(new BorderLayout());
        JScrollPane textScrollPane = new JScrollPane(outputArea);
        centerPanel.add(textScrollPane, BorderLayout.CENTER);

        addClientBtn.addActionListener(e -> {
            String clientName = clientNameField.getText();
            String clientEmail = clientEmailField.getText();

            Client client = new Client(clientEmail, clientName);
            ClientDataAccess.insert(client);

            outputArea.setText(
                    "Client with ID: " +
                            client.getId() +
                            ", Name: " +
                            client.getName() +
                            ", Email: " +
                            client.getEmail() +
                            " has been added!"
            );
        });

        deleteClientBtn.addActionListener(e -> {
            int clientId = Integer.parseInt(clientIdField.getText());
            outputArea.setText(ClientDataAccess.delete(clientId));
        });

        editNameBtn.addActionListener(e -> {
            int clientId = Integer.parseInt(clientId2Field.getText());
            String newName = clientName2Field.getText();
            outputArea.setText(ClientDataAccess.updateName(clientId, newName));
        });

        editEmailBtn.addActionListener(e -> {
            int clientId = Integer.parseInt(clientId3Field.getText());
            String newEmail = clientEmail2Field.getText();
            outputArea.setText(ClientDataAccess.updateEmail(clientId, newEmail));
        });

        viewAllClientsBtn.addActionListener(e -> {

            List<Client> clients = ClientDataAccess.findAll();

            JTable table = TableGenerator.createTable(clients);
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
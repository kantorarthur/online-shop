# Online Shop Management 

This Online Shop Management Application is a GUI-driven warehouse and sales management application designed to streamline business operations by maintaining clients, managing product inventory, and tracking orders and billing generation.

The application leverages a PostgreSQL database for robust, relational data storage and persistence.

The development of the application was made using Java (backend logic), Java Swing (user interface), FlatLaf for styling and designing and PostgreSQL(for secure, relational data persistency).

## Short video-presentation of the Application
https://github.com/user-attachments/assets/9ca84e36-dd53-4059-ba3b-23773fe645c3

## Features

## 1. Clients Management

<img width="1042" height="733" alt="image" src="https://github.com/user-attachments/assets/c761875e-4e6d-48eb-8d02-2e065bdc494f" />



•Add Client: Onboard new customers into the database by specifying their Name and Email.

•Delete Client: Remove an existing customer record using their unique Client ID.

•Edit Name: Update a customer's profile name via their Client ID.

•Edit Email: Update a customer's contact information via their Client ID.

•View All Clients: Fetch and render a complete table displaying all registered clients (ID, Name, Email) dynamically.

## 2. Products Management

<img width="1202" height="745" alt="image" src="https://github.com/user-attachments/assets/fbb45a9d-9668-4107-83d7-b308f06c57c2" />

•Add Product: Insert new inventory items into the warehouse by entering the Name, Price, and available Stock.

•Delete Product: Permanently remove an item from the catalog using its Product ID.

•Edit Name / Price / Stock: Granular control tools to modify a specific product's attributes using its identifier.

•View Products: Display a live status table showing Quantity/Stock, Price, ID, and Name for all warehouse items.

## 3.Create Order

<img width="1224" height="733" alt="image" src="https://github.com/user-attachments/assets/746407e0-0b9c-4926-b298-9929ff6788ec" />

•Interactive Client & Product Selection: Open a dual-panel selector displaying live client and product tables side-by-side. Selecting records captures their specific IDs automatically.

•Desired Quantity Inputs: Define how many items the client wants to purchase.

•Place Order: Process transactions dynamically. It verifies warehouse stock, creates an automated receipt, computes the total cost, updates database tables, and decreases the product stock automatically.

## 4. Bills Management

<img width="1031" height="712" alt="image" src="https://github.com/user-attachments/assets/a5e450e1-c2a2-4ba9-857d-882530759cc9" />


•Comprehensive Tracking: Displays tabular insights containing Order ID, Client Name, Product Name, Quantity purchased, and the computed Total Cost.



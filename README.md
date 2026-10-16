# Online Shop Management 

Desktop Java application for warehouse and sales management: clients, product inventory, orders and billing.

The application was developed using **Java** (backend logic), **Java Swing** (user interface), **FlatLaf** for styling the interface and **PostgreSQL** (for relational data persistence).

## Design and Architecture

### Package Design
<img width="695" height="475" alt="Image" src="https://github.com/user-attachments/assets/748d302e-0b51-481d-aad1-08f4c0226cf6" />

### UML Class Diagram
<img width="651" height="514" alt="Image" src="https://github.com/user-attachments/assets/495a0186-1d3e-42c6-be31-90d2ec955567" />

### Use Case Diagram
<img width="510" height="706" alt="Image" src="https://github.com/user-attachments/assets/3b94f6d0-fcea-4771-bca8-b464a4ff6bfb" />




## Requirements to run the application

- Java 17 or later with GUI support (e.g. `openjdk-17-jdk`, not a `-headless` package)
- PostgreSQL running on `localhost:5432` (database `postgres`, user `postgres`)
- Maven (only needed if you change the code or the database password)

**Important:** the database password is set in `ConnectionFactory.java` (`PASS`) and it must match the password of the `postgres` user from the PostgreSQL installed on your computer. If it doesn't match, the application will start, but you won't be able to add or view any data. After changing the password, rebuild the jar (see "Build" below).



## Run the application

```bash
# Clone the repository
git clone https://github.com/kantorarthur/online-shop

# Enter the main directory of the repository
cd online-shop

# Execute the program
java -jar runApplication.jar
```

If you get `java.awt.HeadlessException`, a headless Java is being used. Select a full one:

```bash
sudo update-alternatives --config java   # choose Java 17 or 21, not a headless-only version
java -version
```

## Build

Use this after changing the database password or the code:

```bash
# Install Maven (Ubuntu/Debian)
sudo apt install maven

# Enter the directory of the repository
cd ~/online-shop

# Build the jar
mvn clean package

# Run the newly built jar
java -jar target/runApplication.jar
```


## Short video presentation of the application
https://github.com/user-attachments/assets/9ca84e36-dd53-4059-ba3b-23773fe645c3

## Features

## 1. Client Management

<img width="1042" height="733" alt="image" src="https://github.com/user-attachments/assets/c761875e-4e6d-48eb-8d02-2e065bdc494f" />



•Add Client: Onboard new customers into the database by specifying their Name and Email.

•Delete Client: Remove an existing customer record using their unique Client ID.

•Edit Name: Update a customer's profile name via their Client ID.

•Edit Email: Update a customer's contact information via their Client ID.

•View All Clients: Fetch and render a complete table displaying all registered clients (ID, Name, Email) dynamically.

## 2. Product Management

<img width="1202" height="745" alt="image" src="https://github.com/user-attachments/assets/fbb45a9d-9668-4107-83d7-b308f06c57c2" />

•Add Product: Insert new inventory items into the warehouse by entering the Name, Price, and available Stock.

•Delete Product: Permanently remove an item from the catalog using its Product ID.

•Edit Name / Price / Stock: Modify a product's name, price or stock using its Product ID.

•View Products: Display a live status table showing Quantity/Stock, Price, ID, and Name for all warehouse items.

## 3. Create Order

<img width="1224" height="733" alt="image" src="https://github.com/user-attachments/assets/746407e0-0b9c-4926-b298-9929ff6788ec" />

•Interactive Client & Product Selection: Open a dual-panel selector displaying live client and product tables side-by-side. Selecting records captures their specific IDs automatically.

•Desired Quantity Inputs: Define how many items the client wants to purchase.

•Place Order: Verifies stock, computes the total cost, generates the bill and updates the product stock.”

## 4. Bill Management

<img width="1031" height="712" alt="image" src="https://github.com/user-attachments/assets/a5e450e1-c2a2-4ba9-857d-882530759cc9" />


•Displays a table with Order ID, Client Name, Product Name, Quantity purchased, and the computed Total Cost.



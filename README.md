# MySphere - Personal Expense Tracker

MySphere is a personal finance management desktop application designed to help users monitor their daily income and expenses with ease[cite: 45]. Built entirely in Java using Swing, this application bridges the gap between learning Object-Oriented Programming (OOP) syntax and building actual, functional software[cite: 45].

## ✨ Features

* **User Authentication:** Secure Login and Sign-Up system. Enforces password length (minimum 8 characters) and checks for duplicate usernames[cite: 45].
* **Multi-User Support:** Each user has their own isolated transaction database saved as a CSV file (e.g., `username_transactions.csv`), ensuring privacy and data separation[cite: 45].
* **Dynamic Dashboard:** A smart home screen that greets the user based on the time of day (Morning/Afternoon/Evening) and displays the total balance, total income, and total spent[cite: 45].
* **Transaction Management:** Users can seamlessly add, edit, and delete income and expense records[cite: 45].
* **Color-Coded History:** A scrollable recent transactions list that visually distinguishes income (Green) from expenses (Red)[cite: 45].
* **Developer About Page:** A dedicated "About" page featuring the development team with clickable GitHub profile links and dynamic circular avatars[cite: 45].

## 🧠 OOP Concepts Implemented

This project practically demonstrates the four core pillars of Object-Oriented Programming:

* **Abstraction & Inheritance:** Utilizes an abstract base class `Transaction` which is extended by `Income` and `Expense` subclasses[cite: 45].
* **Polymorphism:** The `calculateImpact()` method is overridden in the subclasses; `Income` adds to the total balance, while `Expense` returns a negative value to subtract from it[cite: 45].
* **Encapsulation:** Sensitive transaction details (`amount`, `date`, `description`) are kept private and can only be accessed via specific getter methods[cite: 45].
* **Interfaces:** Implements an `Exportable` interface dictating a `generateCSV()` method to format transaction data for file storage[cite: 45].

## 📂 Project Structure

The project is cleanly divided into packages separating the backend logic from the frontend UI[cite: 45]:

* **`raw_java/` (Backend Logic):**
  * `Transaction.java`, `Income.java`, `Expense.java`: Core data models[cite: 45].
  * `TransactionManager.java`: Handles reading, writing, updating, and deleting records from the CSV files[cite: 45].
  * `Exportable.java`: Interface for CSV data formatting[cite: 45].
* **`ui_java/` (Frontend GUI):**
  * `Auth_UI.java`: The entry point of the application containing Login and Sign-Up screens[cite: 45].
  * `App_UI.java`: The main dashboard containing the navigation and layout logic[cite: 45].
  * `AddTransactionPanel.java` & `EditTransactions_UI.java`: Forms for data entry[cite: 45].
  * `Style.java`: Centralized styling (fonts, colors, custom buttons) for UI consistency[cite: 45].
* **`user_transaction/`:** Directory where individual user CSV files are stored[cite: 45].
* **`users.csv`:** Central database storing registered user credentials[cite: 45].

## 🚀 How to Run

1. Ensure you have the **Java Development Kit (JDK)** installed on your machine.
2. Clone or download the repository.
3. Compile the Java files.
4. Run the **`ui_java.Auth_UI`** class. This is the main entry point of the application[cite: 45].
5. Create a new account or log in with existing credentials to start tracking your expenses!

## 👨‍💻 Developed By

This project was developed as a Java Swing theory project by[cite: 45]:
* **Md Parvez Mosharraf Bhuiyan**
* **Rejoan Hasan Mugdho**
* **Tanzim Mohammad Enayetullah**
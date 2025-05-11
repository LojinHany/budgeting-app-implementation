# Nazamha Budgeting Application

A simple Java-based command-line budgeting tool that helps users sign up/sign in, manage categories, track expenses and incomes, perform transactions, set reminders, and analyze their spending against budgets. Data persists between sessions via Java serialization.

---

## 📂 Directory Structure

```
Budget/                       # Project root
├── financialManagement/      # Core budget and transaction logic
│   ├── Account.java
│   ├── BudgetAnalysisManager.java
│   ├── Category.java
│   ├── Expense.java
│   ├── ReminderManager.java
│   └── Transaction.java
├── persistenceLayer/         # Simple serialized storage
│   └── UltraSimpleUserStorage.java
├── usermanagement/           # User signup, sign-in, profile
│   ├── User.java
│   ├── UserAccount.java
│   └── UserServices.java
├── App.java                  # Main application entry point
└── *.ser                     # Serialized data files:
    ├── categories_list.ser
    ├── expenses_list.ser
    ├── incomes_list.ser
    ├── pin_list.ser
    ├── transactions_list.ser
    └── user_list.ser
```

---

## 🚀 Features

* **Authentication**: Sign up with name, email, phone, secure password; sign in to an existing account.
* **Categories**: Create named budget categories (e.g., "groceries", "rent").
* **Expenses**: Log expenses by category with push-door duplicate checks and budget warnings.
* **Incomes**: Record incoming funds to increase account balance.
* **Transactions**: Unified add/withdraw flow affecting both category budgets (for expenses) and account balances.
* **Budget Analysis**: View per-category budgets vs. actual spending; highlights over/under budget.
* **Tracking**: List out all past transactions, incomes, and expenses via simple console tables.
* **Reminders**: Schedule time-based reminders to prompt users about upcoming payments or tasks.
* **Settings & Security**: Change password, update email, set a 4‑digit PIN, and access help & support.

---

## ⚙️ Prerequisites

* Java 8 or higher
* A terminal or command prompt

---

## 🛠️ Setup & Running

1. **Clone or download** this repository to your local machine.
2. **Compile** all `.java` files (ensuring your current directory is `Budget/`):

   ```bash
   javac financialManagement/*.java persistenceLayer/*.java usermanagement/*.java App.java
   ```
3. **Run** the application:

   ```bash
   java App
   ```
4. **Follow** on-screen prompts to sign up or sign in, then interact via the main menu.

---

## 💾 Data Persistence

All user data, categories, expenses, transactions, incomes, and PINs are serialized to `.ser` files in the project root:

* `user_list.ser` & `pin_list.ser` for user credentials and PINs
* `categories_list.ser`, `expenses_list.ser`, `transactions_list.ser`, `incomes_list.ser` for financial data

This approach requires no external database or configuration; simply rerunning `java App` reloads your saved state.

---

## 📝 Usage Example

```text
Welcome to Nazamha!
1. SignUp
2. SignIn
→ 1
Enter your name: Alice
Enter your email: alice@example.com
... (complete sign-up)

Main Menu:
1- Add New Category
2- Add New Expense
3- Do Transaction
... etc.
```

---

## 🤝 Contributing

Contributions are welcome! Feel free to open issues or submit pull requests for new features, bug fixes, or enhancements.



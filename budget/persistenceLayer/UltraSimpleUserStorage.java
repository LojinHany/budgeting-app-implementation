package persistenceLayer;
import financialManagement.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import usermanagement.User;

public class UltraSimpleUserStorage {
    private static final String FILENAME = "users_list.ser";
    private static final String PIN_FILENAME = "pin_list.ser";
    private static final String EXPENSES_FILE = "expenses_list.ser";
    private static final String TRANSACTIONS_FILE = "transactions_list.ser";
    private static final String INCOMES_FILE = "incomes_list.ser";
    private static final String CATEGORIES_FILE = "categories_list.ser";

    // to save users
    public static void saveUsers(List<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(users);
        }
    }

    // to load users
    @SuppressWarnings("unchecked")
    public static List<User> loadUsers() throws IOException, ClassNotFoundException {
        File f = new File(FILENAME);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<User>) ois.readObject();
        }
    }

    // to save pins to a different file
    public static void saveUserPins(Map<String, String> userPins) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream(PIN_FILENAME)))) {
            oos.writeObject(userPins);
        }
    }

    // to load pins to a different file
    @SuppressWarnings("unchecked")
    public static Map<String, String> loadUserPins() throws IOException, ClassNotFoundException {
        File f = new File(PIN_FILENAME);
        if (!f.exists()) return new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (Map<String, String>) ois.readObject();
        }
    }
     // === EXPENSES ===
    public static void saveExpenses(List<Expense> expenses) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXPENSES_FILE))) {
            oos.writeObject(expenses);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Expense> loadExpenses() throws IOException, ClassNotFoundException {
        File f = new File(EXPENSES_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Expense>) ois.readObject();
        }
    }

    // === TRANSACTIONS ===
    public static void saveTransactions(List<Transaction> transactions) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTIONS_FILE))) {
            oos.writeObject(transactions);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Transaction> loadTransactions() throws IOException, ClassNotFoundException {
        File f = new File(TRANSACTIONS_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Transaction>) ois.readObject();
        }
    }

    // === INCOMES ===
    public static void saveIncomes(List<Transaction> incomes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INCOMES_FILE))) {
            oos.writeObject(incomes);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Transaction> loadIncomes() throws IOException, ClassNotFoundException {
        File f = new File(INCOMES_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Transaction>) ois.readObject();
        }
    }

    // === CATEGORIES ===
    public static void saveCategories(List<Category> categories) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CATEGORIES_FILE))) {
            oos.writeObject(categories);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Category> loadCategories() throws IOException, ClassNotFoundException {
        File f = new File(CATEGORIES_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Category>) ois.readObject();
        }
    }
}


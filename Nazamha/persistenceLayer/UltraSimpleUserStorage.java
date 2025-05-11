package persistenceLayer;

import financialManagement.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import usermanagement.User;

/**
 * This class provides static methods for saving and loading various types of data,
 * including users, pins, expenses, transactions, incomes, and categories.
 * The data is saved and loaded using serialization to and from files.
 */
public class UltraSimpleUserStorage {
    private static final String FILENAME = "users_list.ser";
    private static final String PIN_FILENAME = "pin_list.ser";
    private static final String EXPENSES_FILE = "expenses_list.ser";
    private static final String TRANSACTIONS_FILE = "transactions_list.ser";
    private static final String INCOMES_FILE = "incomes_list.ser";
    private static final String CATEGORIES_FILE = "categories_list.ser";

    /**
     * Saves a list of users to a file.
     *
     * @param users The list of users to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public static void saveUsers(List<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(users);
        }
    }

    /**
     * Loads the list of users from the file.
     *
     * @return A list of users loaded from the file.
     * @throws IOException            If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    @SuppressWarnings("unchecked")
    public static List<User> loadUsers() throws IOException, ClassNotFoundException {
        File f = new File(FILENAME);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<User>) ois.readObject();
        }
    }

    /**
     * Saves a map of user PINs to a file.
     *
     * @param userPins The map of user PINs to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public static void saveUserPins(Map<String, String> userPins) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream(PIN_FILENAME)))) {
            oos.writeObject(userPins);
        }
    }

    /**
     * Loads the map of user PINs from the file.
     *
     * @return A map of user PINs loaded from the file.
     * @throws IOException            If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> loadUserPins() throws IOException, ClassNotFoundException {
        File f = new File(PIN_FILENAME);
        if (!f.exists()) return new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (Map<String, String>) ois.readObject();
        }
    }

    // === EXPENSES ===

    /**
     * Saves a list of expenses to a file.
     *
     * @param expenses The list of expenses to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public static void saveExpenses(List<Expense> expenses) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXPENSES_FILE))) {
            oos.writeObject(expenses);
        }
    }

    /**
     * Loads the list of expenses from the file.
     *
     * @return A list of expenses loaded from the file.
     * @throws IOException            If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    @SuppressWarnings("unchecked")
    public static List<Expense> loadExpenses() throws IOException, ClassNotFoundException {
        File f = new File(EXPENSES_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Expense>) ois.readObject();
        }
    }

    // === TRANSACTIONS ===

    /**
     * Saves a list of transactions to a file.
     *
     * @param transactions The list of transactions to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public static void saveTransactions(List<Transaction> transactions) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTIONS_FILE))) {
            oos.writeObject(transactions);
        }
    }

    /**
     * Loads the list of transactions from the file.
     *
     * @return A list of transactions loaded from the file.
     * @throws IOException            If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    @SuppressWarnings("unchecked")
    public static List<Transaction> loadTransactions() throws IOException, ClassNotFoundException {
        File f = new File(TRANSACTIONS_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Transaction>) ois.readObject();
        }
    }

    // === INCOMES ===

    /**
     * Saves a list of incomes to a file.
     *
     * @param incomes The list of incomes to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public static void saveIncomes(List<Transaction> incomes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INCOMES_FILE))) {
            oos.writeObject(incomes);
        }
    }

    /**
     * Loads the list of incomes from the file.
     *
     * @return A list of incomes loaded from the file.
     * @throws IOException            If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    @SuppressWarnings("unchecked")
    public static List<Transaction> loadIncomes() throws IOException, ClassNotFoundException {
        File f = new File(INCOMES_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Transaction>) ois.readObject();
        }
    }

    // === CATEGORIES ===

    /**
     * Saves a list of categories to a file.
     *
     * @param categories The list of categories to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public static void saveCategories(List<Category> categories) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CATEGORIES_FILE))) {
            oos.writeObject(categories);
        }
    }

    /**
     * Loads the list of categories from the file.
     *
     * @return A list of categories loaded from the file.
     * @throws IOException            If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    @SuppressWarnings("unchecked")
    public static List<Category> loadCategories() throws IOException, ClassNotFoundException {
        File f = new File(CATEGORIES_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Category>) ois.readObject();
        }
    }
}

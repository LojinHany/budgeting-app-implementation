package persistenceLayer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import usermanagement.User;

public class UltraSimpleUserStorage {
    private static final String FILENAME = "users_list.ser";
    private static final String PIN_FILENAME = "pin_list.ser";

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
}

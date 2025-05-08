// In persistenceLayer/UltraSimpleUserStorage.java
package persistenceLayer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import usermanagement.User;

public class UltraSimpleUserStorage {
    private static final String FILENAME = "users_list.ser";

    public static void saveUsers(List<User> users) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(users);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<User> loadUsers() throws IOException, ClassNotFoundException {
        File f = new File(FILENAME);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<User>) ois.readObject();
        }
    }
}

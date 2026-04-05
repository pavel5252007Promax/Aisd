import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private TwoThreeTree tree;
    private Map<Integer, String> data;

    public PhoneBook() {
        tree = new TwoThreeTree();
        data = new HashMap<>();
    }

    public boolean addContact(int phoneNumber, String name) {
        if (tree.search(phoneNumber)) {
            return false;
        }
        tree.insert(phoneNumber);
        data.put(phoneNumber, name);
        return true;
    }

    public String findContact(int phoneNumber) {
        if (tree.search(phoneNumber)) {
            return data.get(phoneNumber);
        }
        return null;
    }

    public boolean deleteContact(int phoneNumber) {
        if (!tree.search(phoneNumber)) {
            return false;
        }
        tree.delete(phoneNumber);
        data.remove(phoneNumber);
        return true;
    }

    public int size() {
        return tree.size();
    }

    public void printAll() {
        for (Map.Entry<Integer, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

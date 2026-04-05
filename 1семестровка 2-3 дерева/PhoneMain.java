public class PhoneMain {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        System.out.println("Добавление контактов:");
        phoneBook.addContact(111111, "Alice");
        phoneBook.addContact(222222, "Bob");
        phoneBook.addContact(333333, "Charlie");
        phoneBook.addContact(444444, "David");
        phoneBook.addContact(555555, "Eve");

        System.out.println("Поиск 333333: " + phoneBook.findContact(333333)); // Charlie
        System.out.println("Поиск 999999: " + phoneBook.findContact(999999)); // null

        System.out.println("Удаление 222222: " + phoneBook.deleteContact(222222));
        System.out.println("Поиск 222222 после удаления: " + phoneBook.findContact(222222));

        System.out.println("Добавление дубликата 111111: " + phoneBook.addContact(111111, "Anna"));

        System.out.println("Все контакты:");
        phoneBook.printAll();

        System.out.println("Размер: " + phoneBook.size());
    }
}

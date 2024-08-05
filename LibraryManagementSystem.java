import java.io.*;
import java.util.*;

public class LibraryManagementSystem {
    protected List<Book> books;
    protected List<User> users;
    protected Map<Book, User> checkouts;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        checkouts = new HashMap<>();
        loadLibrary();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean checkoutBook(String title, String userName) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                for (User user : users) {
                    if (user.getName().equalsIgnoreCase(userName)) {
                        book.setAvailable(false);
                        checkouts.put(book, user);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                book.setAvailable(true);
                checkouts.remove(book);
                return true;
            }
        }
        return false;
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void listUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void listCheckouts() {
        for (Map.Entry<Book, User> entry : checkouts.entrySet()) {
            System.out.println("Book: " + entry.getKey() + " | User: " + entry.getValue());
        }
    }

    public void saveLibrary() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("library.dat"))) {
            oos.writeObject(books);
            oos.writeObject(users);
            oos.writeObject(checkouts);
        } catch (IOException e) {
            System.out.println("Error saving library data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadLibrary() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("library.dat"))) {
            books = (List<Book>) ois.readObject();
            users = (List<User>) ois.readObject();
            checkouts = (Map<Book, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library data: " + e.getMessage());
        }
    }
}

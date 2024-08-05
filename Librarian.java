public class Librarian extends User {

    public Librarian(String name) {
        super(name);
    }

    public void addBook(LibraryManagementSystem library, String title, String author) {
        library.addBook(new Book(title, author));
        System.out.println("Book added.");
    }

    public void addUser(LibraryManagementSystem library, String name) {
        library.addUser(new User(name));
        System.out.println("User added.");
    }
}

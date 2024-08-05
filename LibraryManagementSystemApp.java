import java.util.Scanner;

public class LibraryManagementSystemApp {
    private static LibraryManagementSystem library = new LibraryManagementSystem();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Librarian librarian = new Librarian("Admin");

        String command;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Add Book (Librarian)");
            System.out.println("2. Add User (Librarian)");
            System.out.println("3. Checkout Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Books");
            System.out.println("6. List Users");
            System.out.println("7. List Checkouts");
            System.out.println("8. Exit");
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    addBook(librarian, scanner);
                    break;
                case "2":
                    addUser(librarian, scanner);
                    break;
                case "3":
                    checkoutBook(scanner);
                    break;
                case "4":
                    returnBook(scanner);
                    break;
                case "5":
                    library.listBooks();
                    break;
                case "6":
                    library.listUsers();
                    break;
                case "7":
                    library.listCheckouts();
                    break;
                case "8":
                    library.saveLibrary();
                    System.out.println("Exiting and saving data...");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        } while (!command.equals("8"));

        scanner.close();
    }

    private static void addBook(Librarian librarian, Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        librarian.addBook(library, title, author);
    }

    private static void addUser(Librarian librarian, Scanner scanner) {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        librarian.addUser(library, name);
    }

    private static void checkoutBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();
        if (library.checkoutBook(title, userName)) {
            System.out.println("Book checked out.");
        } else {
            System.out.println("Unable to checkout book. Check availability and user status.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        if (library.returnBook(title)) {
            System.out.println("Book returned.");
        } else {
            System.out.println("Unable to return book. Check if the book is checked out.");
        }
    }
}

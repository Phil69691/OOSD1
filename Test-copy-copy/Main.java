import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";
    private static final List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Ticketing System");

        boolean isAuthenticated = false;
        User currentUser = null;

        while (!isAuthenticated) {
            System.out.println("1. Login");
            System.out.println("2. Create an Account");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    currentUser = login(scanner);
                    if (currentUser != null) {
                        isAuthenticated = true;
                    }
                    break;
                case 2:
                    createUser(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }

        System.out.println("Welcome, " + currentUser.getUsername() + "!");
        System.out.println("Proceeding with the application logic...");

        
        List<Event> upcomingEvents = Event.getUpcomingEvents();
        System.out.println("\nUpcoming Events:");
        for (int i = 0; i < upcomingEvents.size(); i++) {
            System.out.println((i + 1) + ". " + upcomingEvents.get(i).getName());
        }

        // Select an event
        System.out.print("Select an event (enter event number): ");
        int eventChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (eventChoice >= 1 && eventChoice <= upcomingEvents.size()) {
            Event selectedEvent = upcomingEvents.get(eventChoice - 1);
            System.out.println("You selected: " + selectedEvent.getName() + ", Date: " + selectedEvent.getDate());

            
            selectedEvent.displaySeatingChart();

            
            System.out.print("Enter number of seats to reserve: ");
            int numSeats = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            for (int i = 0; i < numSeats; i++) {
                System.out.print("Enter row and column for seat " + (i + 1) + ": ");
                int row = scanner.nextInt();
                int column = scanner.nextInt();
                scanner.nextLine(); 
                if (selectedEvent.reserveSeat(row - 1, column - 1)) {
                    System.out.println("Seat (" + row + ", " + column + ") reserved successfully.");
                } else {
                    System.out.println("Seat (" + row + ", " + column + ") is already taken or invalid.");
                }
            }

            
            double totalAmount = numSeats * 10;
            System.out.println("Total Amount: £" + totalAmount);
            System.out.println("Payment processed successfully.");
        } else {
            System.out.println("Invalid event choice.");
        }

        scanner.close();
    }

    private static User login(Scanner scanner) {
        System.out.println("\nLogin");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("Admin login successful!\n");
            return new User(ADMIN_USERNAME, ADMIN_PASSWORD);
        }

        
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!\n");
                return user;
            }
        }

        System.out.println("Invalid username or password. Please try again.\n");
        return null;
    }

    private static void createUser(Scanner scanner) {
        System.out.println("\nCreate an Account");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.add(new User(username, password));
        System.out.println("Account created successfully.\n");
    }
}
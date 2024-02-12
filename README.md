# OOSD1

import java.util.ArrayList;
import java.util.List;
public class Event {
 private String name;
 private String date;
 private String venue;
 private int seatingCapacity;
 private int availableSeats;
 private boolean[][] seatingChart;
 public Event(String name, String date, String venue, int seatingCapacity) {
 this.name = name;
 this.date = date;
 this.venue = venue;
 this.seatingCapacity = seatingCapacity;
 this.availableSeats = seatingCapacity;
 this.seatingChart = new boolean[10][10];
 initializeSeatingChart();
 }
 private void initializeSeatingChart() {
 for (int i = 0; i < seatingChart.length; i++) {
 for (int j = 0; j < seatingChart[i].length; j++) {
 seatingChart[i][j] = true; // All seats are initially available
 }
 }
 }
 public static List<Event> getUpcomingEvents() {
 List<Event> upcomingEvents = new ArrayList<>();

 upcomingEvents.add(new Event("Concert", "2024-02-10", "Bucks Centre",
100));
 upcomingEvents.add(new Event("Theater Play", "2024-02-15", "Bucks
Centre", 150));
 upcomingEvents.add(new Event("Comedy Show", "2024-02-20", "Bucks
Centre", 120));
 return upcomingEvents;
 }
 public void displaySeatingChart() {

 System.out.print(" ");
 for (int i = 1; i <= seatingChart[0].length; i++) {
 System.out.print(i + " ");
 }
 System.out.println();

 for (int i = 0; i < seatingChart.length; i++) {
 System.out.print((i + 1) + " "); // Row numbers
 for (int j = 0; j < seatingChart[i].length; j++) {
 if (seatingChart[i][j]) {
 System.out.print("O "); // Available seat
 } else {
 System.out.print("X "); // Reserved seat
 }
 }
 System.out.println();
 }
 }
 public boolean reserveSeat(int row, int column) {
 if (row >= 0 && row < seatingChart.length && column >= 0 && column <
seatingChart[0].length) {
 if (seatingChart[row][column]) {
 seatingChart[row][column] = false;
 availableSeats--;
 return true;
 }
 }
 return false;
 }
 public String getName() {
 return name;
 }
 public String getDate() {
 return date;
 }
}
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
 System.out.println("You selected: " + selectedEvent.getName() + ", Date:
" + selectedEvent.getDate());

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
 System.out.println("Seat (" + row + ", " + column + ") reserved
successfully.");
 } else {
 System.out.println("Seat (" + row + ", " + column + ") is already
taken or invalid.");
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

 if (ADMIN_USERNAME.equals(username) &&
ADMIN_PASSWORD.equals(password)) {
 System.out.println("Admin login successful!\n");
 return new User(ADMIN_USERNAME, ADMIN_PASSWORD);
 }

 for (User user : users) {
 if (user.getUsername().equals(username) &&
user.getPassword().equals(password)) {
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
class Ticket {
 private static int ticketIdCounter = 0;
 private int ticketId;
 private Event event;
 private Customer customer;
 private double price;
 public Ticket(Event event, Customer customer, double price) {
 ticketIdCounter++;
 this.ticketId = ticketIdCounter;
 this.event = event;
 this.customer = customer;
 this.price = price;
 }
 public void applyDiscount(double discount) {
 price -= (price * discount);
 }
 public String generateTicket() {
 return String.format("Ticket ID: %d%nEvent: %s%nCustomer: %s%nPrice:
£%.2f",
 ticketId, event.getName(), customer.getName(), price);
 }
}
class Customer {
 private int customerId;
 private String name;
 private String address;
 private String creditCard;
 public Customer(int customerId, String name, String address, String
creditCard) {
 this.customerId = customerId;
 this.name = name;
 this.address = address;
 this.creditCard = creditCard;
 }
 public boolean validateCreditCard() {

 return true;
 }
 public void updateCustomerDetails(String name, String address, String
creditCard) {
 if (name != null) {
 this.name = name;
 }
 if (address != null) {
 this.address = address;
 }
 if (creditCard != null) {
 this.creditCard = creditCard;
 }
 }
 public String getName() {
 return name;
 }
}
Pseudo cod

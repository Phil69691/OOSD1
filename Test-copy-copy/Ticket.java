// Ticket class
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
        return String.format("Ticket ID: %d%nEvent: %s%nCustomer: %s%nPrice: £%.2f",
                             ticketId, event.getName(), customer.getName(), price);
    }
}


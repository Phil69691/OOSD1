
class Customer {
    private int customerId;
    private String name;
    private String address;
    private String creditCard;

    public Customer(int customerId, String name, String address, String creditCard) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.creditCard = creditCard;
    }

    public boolean validateCreditCard() {
        
        return true;
    }

    public void updateCustomerDetails(String name, String address, String creditCard) {
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

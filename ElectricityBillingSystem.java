import java.util.*;

class Customer {
    private String name;
    private String address;
    private int meterId;
    
    public Customer(String name, String address, int meterId) {
        this.name = name;
        this.address = address;
        this.meterId = meterId;
    }

    public String getName() {
        return name;
    }
    
    public String getAddresss() {
        return address;
    }   
    
    public int getMeterId() {
        return meterId;
    }

    public void showDetails(){
        System.out.println("Meter Id: "+getMeterId()+", Name: " +getName()+ ", Address: "+ getAddresss());
    }
}

class Bill {
    private Customer cust;
    private int unitsConsumed;
    private double billAmount;
    
    public Bill(Customer cust, int unitsConsumed) {
        this.cust = cust;
        this.unitsConsumed = unitsConsumed;
    }

    public void calculateBill() {
        // Billing calculation logic 
        if(unitsConsumed < 100){
            billAmount = unitsConsumed * 4.5;
        }else{
            billAmount = (100 * 4.5) + (unitsConsumed-100) * 9;
        }
    }

    public double getBillAmount() {
        return billAmount;
    }

    public Customer getUser() {
        return cust;
    }

}

public class ElectricityBillingSystem {
    public static void main(String[] args) {
        Map<Integer, Customer> customers=new HashMap<>();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n\nWelcome to Electricity Billing System!\n1 - New Customer Registration \n2 - Calculate Bill\n3 - View All Customers\n4 - Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Register a New Customer: \nEnter Your Name: ");
                    String name=sc.next();
                    sc.nextLine();
                    System.out.print("Enter Your Address: ");
                    String address=sc.nextLine();
                    System.out.print("Enter Meter id: ");
                    int meter=sc.nextInt();
                    customers.put(meter, new Customer(name, address, meter));
                    System.out.println("Customer created successfully!");
                    break;

                case 2:
                    System.out.print("Bill Generation\nPlease Enter Meter Id:");
                    int m = sc.nextInt();
                    if(customers.containsKey(m)){
                        System.out.print("Please Enter Units consumed for "+ customers.get(m).getName() + " : ");
                        Bill billUser1 = new Bill(customers.get(m), sc.nextInt());
                        billUser1.calculateBill();
                        System.out.println("Bill Generated for "+ customers.get(m).getName() +": Rs." + billUser1.getBillAmount());
                    }else{
                        System.out.println("No Records Found for meter id = " + m);
                    }
                    break;

                case 3:
                    System.out.println("View all customers");
                    for(Map.Entry<Integer, Customer> u : customers.entrySet()){
                        u.getValue().showDetails();
                    }
                    break;

                case 4:
                    System.out.println("Exiting the system...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Please enter valid choice.");
                    break;
            }
        }
    }
}
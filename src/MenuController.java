import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MenuController {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n----- WELCOME TO CARGO COMPANY -----");
            System.out.println("1. ENTER YOUR ACCOUNT");
            System.out.println("2. CREATE NEW ACCOUNT");
            System.out.println("3. EXIT");
            System.out.print("ENTER YOUR OPTION: ");

            int option = readInt();

            switch (option) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    registerMenu();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.err.println("Wrong option!");
            }
        }
    }

    public void loginMenu() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Username: ");
        String tryUsername = scanner.nextLine().trim();
        System.out.print("Password: ");
        String tryPassword = scanner.nextLine().trim();

        boolean isFound = false;

        for (Customer c : AppData.customers) {
            if (c.getName().equals(tryUsername) && c.getPassword().equals(tryPassword)) {
                System.out.println("Welcome back, " + c.getName() + "!");
                userMenu(c);
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            System.err.println("Wrong password or username!");
        }
    }

    public void registerMenu() {
        System.out.println("\n--- REGISTER ---");
        System.out.print("Username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Phone number: ");
        String newNumber = scanner.nextLine();
        System.out.print("Password: ");
        String newPassword = scanner.nextLine();

        System.out.println("Available Banks: JPMorgan_Chase, Bank_Of_America, Citibank, US_Bank");
        System.out.print("Choose Bank (Type exactly): ");
        String bankStr = scanner.nextLine().trim();

        try {
            Banks newBank = Banks.valueOf(bankStr);
            Customer newCustomer = new Customer(newUsername, newEmail, newNumber, newPassword, new BankAccount(newBank), null);
            AppData.customers.add(newCustomer);
            System.out.println("Account created successfully!");
            userMenu(newCustomer);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Bank name! Please try again.");
        } catch (WrongDataPass e) {
            System.err.println("Error creating customer: " + e.getMessage());
        }
    }

    public void userMenu(Customer currentCustomer) {
        boolean inUserMenu = true;

        while (inUserMenu) {
            System.out.println("\n----- USER MENU (" + currentCustomer.getName() + ") -----");
            System.out.println("1. ORDER CARGO");
            System.out.println("2. SEE MY INFO (with Insurance)");
            System.out.println("3. BANK TRANSACTIONS");
            System.out.println("4. PAYMENT");
            System.out.println("5. LOGOUT");
            System.out.print("ENTER OPTION: ");

            int option = readInt();

            switch (option) {
                case 1:
                    orderCargo(currentCustomer);
                    break;
                case 2:
                    showCustomerInfo(currentCustomer);
                    break;
                case 3:
                    bankTransactions(currentCustomer);
                    break;
                case 4:
                    payment(currentCustomer);
                    break;
                case 5:
                    inUserMenu = false;
                    break;
                default:
                    System.err.println("Wrong option!");
            }
        }
    }

    public void showCustomerInfo(Customer customer) {
        System.out.println("\n--- CUSTOMER INFO ---");
        customer.printCustomerInfo();

        Cargo cargo = customer.getCargo();
        if (cargo != null) {
            System.out.println("\n--- CURRENT CARGO DETAILS ---");
            System.out.println("Route: " + cargo.getSenderCity().getCity() + " -> " + cargo.getRecieverCity().getCity());
            System.out.printf("Base Cost: %.2f$\n", cargo.calculateTotalCargoCost());
            Vehicle v = cargo.getVehicle();
            System.out.println("Carried by: " + v.getClass().getSimpleName() + " (" + v.getLicensePlateNumber() + ")");

            System.out.println("--- INSURANCE POLICY ---");
            cargo.printInsurancePolicy();
        } else {
            System.out.println("\n[No Active Cargo]");
        }
    }

    public void orderCargo(Customer currentCustomer) {
        System.out.println("\n--- NEW ORDER ---");
        System.out.print("Shipping Type (StandardShipping, ExpressShipping, ColdChainShipping): ");
        String typeStr = scanner.next();

        ShippingTypes shippingType;
        try {
            shippingType = ShippingTypes.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Type! Defaulting to Standard.");
            shippingType = ShippingTypes.StandardShipping;
        }

        System.out.print("Type Sender City (Moscow, Berlin, Paris, Madrid, Istanbul, London): ");
        String senderStr = scanner.next().trim();
        City senderCity = findCity(senderStr);

        System.out.print("Type Receiver City (Moscow, Berlin, Paris, Madrid, Istanbul, London): ");
        String receiverStr = scanner.next().trim();
        City receiverCity = findCity(receiverStr);

        if (senderCity == null || receiverCity == null) {
            System.err.println("Error: One of the cities not found!");
            return;
        }

        try {
            System.out.print("Enter Cargo Weight: ");
            int weight = readInt();
            System.out.print("Enter Cargo Value ($): ");
            int value = readInt();

            Vehicle selectedVehicle = null;
            for (Vehicle v : AppData.vehicles) {
                if (v.canVehicleCarry(weight)) {
                    selectedVehicle = v;
                    break;
                }
            }

            if (selectedVehicle != null) {
                Cargo newCargo = new Cargo(weight, value, senderCity, receiverCity, shippingType, selectedVehicle);
                currentCustomer.setCargo(newCargo);
                AppData.cargos.add(newCargo);
                System.out.println("Cargo ordered successfully!");
                System.out.println("Distance: " + (int)newCargo.calculateDistance() + " km");
            } else {
                System.err.println("No suitable vehicle found for this weight!");
            }
        } catch (Exception e) {
            System.err.println("Error ordering cargo: " + e.getMessage());
        }
    }

    private void bankTransactions(Customer currentCustomer) {
        boolean inBankTransaction = true;
        while (inBankTransaction) {
            System.out.println("\n----- BANK (" + currentCustomer.getName() + ") -----");
            System.out.println("1. DEPOSIT MONEY");
            System.out.println("2. REFUND");
            System.out.println("3. BACK");
            System.out.print("ENTER OPTION: ");

            int option = readInt();

            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    currentCustomer.getBankAccount().depositMoney(amount);
                    System.out.println("New balance: " + currentCustomer.getBankAccount().getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to refund: ");
                    double refund = scanner.nextDouble();
                    scanner.nextLine();
                    if(currentCustomer.getBankAccount().refund(refund)){
                        System.out.println("Refund successful. New balance: " + currentCustomer.getBankAccount().getBalance());
                    } else {
                        System.err.println("Not enough balance!");
                    }
                    break;
                case 3:
                    inBankTransaction = false;
                    break;
                default:
                    System.err.println("Wrong option!");
            }
        }
    }

    private void payment(Customer currentCustomer) {
        if (currentCustomer.getCargo() == null) {
            System.err.println("You have no cargo to pay for!");
            return;
        }

        boolean inPayment = true;
        while (inPayment) {
            System.out.println("\n----- PAYMENT -----");
            System.out.println("Balance: " +currentCustomer.getBankAccount().getBalance());
            System.out.println("Cargo Cost: " +  currentCustomer.getCargo().calculateTotalCargoCost());
            System.out.println("1. PAY");
            System.out.println("2. EXIT");
            System.out.print("ENTER OPTION: ");

            int option = readInt();

            switch (option) {
                case 1:
                    System.out.print("Enter Payment Type (CreditCard, DigitalWallet): ");
                    String typeStr = scanner.next();
                    PaymentTypes paymentType;
                    try {
                        paymentType = PaymentTypes.valueOf(typeStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid Type! Defaulting to CreditCard.");
                        paymentType = PaymentTypes.CreditCard;
                    }

                    Payment payment = new Payment(paymentType, currentCustomer);

                    if (payment.authorize()) {
                        Cargo shippedCargo = currentCustomer.getCargo();

                        System.out.println("\n PAYMENT SUCCESSFUL!");
                        System.out.println("--------------------------------------------------");
                        System.out.println("   YOUR CARGO IS ON THE WAY! ");
                        System.out.println("--------------------------------------------------");
                        System.out.println("From: " + shippedCargo.getSenderCity().getCity());
                        System.out.println("To:   " + shippedCargo.getRecieverCity().getCity());

                        Vehicle v = shippedCargo.getVehicle();
                        System.out.println("Vehicle: " + v.getClass().getSimpleName() + " [" + v.getLicensePlateNumber() + "]");

                        System.out.println("Estimated Arrival: " + shippedCargo.calculateEstimatedTime().toString().substring(0, 10));
                        System.out.println("--------------------------------------------------");

                        currentCustomer.setCargo(null);

                        inPayment = false;
                    }
                    break;
                case 2:
                    inPayment = false;
                    break;
                default:
                    System.err.println("Wrong option!");
            }
        }
    }

    private City findCity(String name) {
        for (City c : AppData.cities) {
            if (c.getCity().name().equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    private int readInt() {
        try {
            int val = scanner.nextInt();
            scanner.nextLine();
            return val;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}
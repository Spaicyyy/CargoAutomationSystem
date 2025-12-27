public class Payment{
    private Customer customer;

    private PaymentTypes paymentType;

    public Payment(PaymentTypes paymentType , Customer customer){
        setPaymentType(paymentType);
        setCustomer(customer);
    }

    public boolean authorize(){
        if(customer.cargo.calculateTotalPrice() > customer.bankAccount.getBalance()){
            System.err.println("Not enough balance!");
            return false ;
        }else{
            pay();
            printReceipt();
            return true;
        }
    }

    public double pay(){
       customer.bankAccount.withdraw(customer.cargo.calculateTotalPrice());
        return customer.bankAccount.getBalance() ;
    }

    public void printReceipt(){
        System.out.println("Cargo:"+ customer.cargo.senderCity.getCity()+ " --> " + customer.cargo.recieverCity.getCity()+ " with distance:"+(int)customer.cargo.calculateDistance()+" was successfully payed! with the cost of "+customer.cargo.calculateTotalPrice()+"$ and payed with:"+getPaymentType()+" !!!");
    }


    //-----Getter and Setter
    public void setPaymentType(PaymentTypes paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentTypes getPaymentType() {
        return paymentType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}

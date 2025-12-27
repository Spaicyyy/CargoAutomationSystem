public class Customer extends User{
    BankAccount bankAccount;
    Cargo cargo;

    public Customer(String name , String email , String phoneNumber , String password ,BankAccount bankAccount,Cargo cargo) throws WrongDataPass{
         super(name, email, phoneNumber, password);
         setBankAccount(bankAccount);
         setCargo(cargo);
    }

    public void printCustomerInfo(){
        System.out.println("Customer's name:"+name+
                            "\nEmail:"+email+
                            "\nPhone number:"+phoneNumber+
                            "\nBank Account information: " +
                            "\nBank:" +bankAccount.getBank()+
                            "\nAccount id:"+bankAccount.getAccountId()+
                            "\nBalance:"+bankAccount.getBalance());
    }

    //Getter Setter
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }
}


public class BankAccount implements Refund {
    private Banks bank ;
    private double balance ;

    private final int accountId;

    private static int numberOfAccounts = 0 ;

    public BankAccount(Banks bank) throws WrongDataPass{
        setBank(bank);
        balance = 0;
        numberOfAccounts++;
        accountId = numberOfAccounts;
    }

    //Methodlar
    public void depositMoney(double amount){
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance = this.balance - amount;
    }

    @Override
    public boolean refund(double amount) {
        if (amount > balance) {
            return false;
        } else {
            balance += amount;
            return true;
        }
    }


    //Getter Setter
    public void setBank(Banks bank) {
        this.bank = bank;
    }

    public Banks getBank() {
        return bank;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountId() {
        return accountId;
    }
}

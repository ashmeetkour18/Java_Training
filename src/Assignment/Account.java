package Assignment;

public class Account
{
    private double balance;
    private String name;
    private final long acctNum;
    private static int numAccounts=0;


    //----------------------------------------------
    //Constructor -- initializes balance and owner; generates random
    //account number
    //----------------------------------------------
    public Account(double initBal, String owner)
    {
        numAccounts++;
        balance = initBal;
        name = owner;
        acctNum = (int) (Math.random() * Integer.MAX_VALUE);
    }

    //----------------------------------------------
    //Constructor -- initializes owner as given and balance to 0.
    //Generates random account number
    //----------------------------------------------
    public Account(String owner)
    {
        numAccounts++;
        balance = 0;
        name = owner;
        acctNum = (int) (Math.random() * Integer.MAX_VALUE);
    }

    public static Account consolidate(Account account1, Account account2) {
        if (account1.name.equalsIgnoreCase(account2.name) && account1.getAcctNumber() != account2.getAcctNumber()) {
            double bal = account1.getBalance() + account2.getBalance();
            String name1=account1.name;
            account1.close();
            account2.close();
            account1.name=name1+" newOpened";

            return new Account(bal, account1.name);
        }
        return null;
    }

    public void close(){
        this.balance=0;
        numAccounts--;
    this.name+=" CLOSED";
}
    //----------------------------------------------
    // Checks to see if balance is sufficient for withdrawal.
    // If so, decrements balance by amount; if not, prints message.
    //----------------------------------------------
    public void withdraw(double amount)
    {
        if (balance >= amount)
            balance -= amount;
        else
            System.out.println("Insufficient funds");

    }

    //----------------------------------------------
    // Checks to see if balance is sufficient for withdrawal.
    // If so, decrements balance by amount; if not, prints message.
    // Also deducts fee from account.
    //----------------------------------------------
    public void withdraw(double amount, double fee)
    {
        if (balance >= amount)
        {
            balance -= amount;
            balance -= fee;
        }
        else
            System.out.println("Insufficient funds");

    }

    //----------------------------------------------
    // Adds deposit amount to balance.
    //----------------------------------------------
    public void deposit(double amount)
    {
        balance += amount;
    }

    public static int getNumAccounts() {
        return numAccounts;
    }
    //----------------------------------------------
    // Returns balance.
    //----------------------------------------------
    public double getBalance()
    {
        return balance;
    }

    //----------------------------------------------
    // Returns account number
    //----------------------------------------------
    public double getAcctNumber()
    {
        return acctNum;
    }


    //----------------------------------------------
    // Returns a string containing the name, acct number, and balance.
    //----------------------------------------------
    public String toString()
    {
        return "Name: " + name +
                "\nAcct #: " + acctNum +
                "\nBalance: " + balance;
    }
}

package Assignment;

import java.util.Scanner;
public class TestAccounts1
{
    public static void main(String[] args)
    {
        Account testAcct;
        Scanner scan = new Scanner(System.in);
        System.out.println("How many accounts would you like to create?"); int num =
            scan.nextInt();
        for (int i=1; i<=num; i++)
        {
            testAcct = new Account(100, "Name" + i);
            System.out.println("\nCreated account " + testAcct);

            // Your code here: call method to print out how many accounts have been created so far
        }
        Account account = new Account(100.0 , "Name7");
        System.out.println("\nCreated account \"" + account);
        Account account2 = new Account(100.0 , "Name7");
        System.out.println("\nCreated account \"" + account2);
        System.out.println(Account.consolidate(account,account2));
        System.out.println(" Total Accounts = "+Account.getNumAccounts());
        //account2.close();
        System.out.println(" Total Accounts = "+Account.getNumAccounts());
           }
}

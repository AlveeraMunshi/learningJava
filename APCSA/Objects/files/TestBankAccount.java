package APCSA.Objects.files;

public class TestBankAccount
{
  public static void main (String[]args)
  {
    BankAccount account = new BankAccount("Alveera", 12042004, 9876123.54); // Create bank account
    System.out.println(account);
    account.deposit(200.0);
    System.out.println(account);
    account.withdraw(200.0);
	  System.out.println(account);
  }
}

package APCSA.Objects.files.Bank;

public class BankAccount
{
  private String name;
  private int accountnum;
  private double balance;

  public BankAccount()
  {
    name = "";
    accountnum = 0;
    balance = 0.0;
  }
  public BankAccount(String name, int accountnum, double balance)
  {
    this.name = name;
    this.accountnum = accountnum;
    this.balance = balance;
  }
  //Edit balance
  public double deposit(double amount)
	{
		balance = balance+amount;
    return balance;
	}
  public double withdraw(double amount)
	{
    if (amount < balance)
      balance =  balance-amount;
    else
    {
      System.out.print("You don't have enough funds to withdraw specified amount");
    }
    return balance;
	}
	public String toString()
	{
		return this.name+" "+this.accountnum+" "+this.balance;
	}
  public static void main (String[]args)
  {
  }
}

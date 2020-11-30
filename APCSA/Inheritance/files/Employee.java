package APCSA.Inheritance.files;

/* Employee.java
 * A class to represent behavior common to all employees */
class Employee{
	private String name;
	public Employee(String name){
		this.name = name;
	}
	public int getHours(){
		return 40;	// works 40 hours/week
	}
	public double getSalary(){
		return 40000.0; // $40,000.00/year
	}
	public int getVacationDays(){
		return 10;	// 2 weeks' paid vacation
	}
	public String getVacationForm(){
		return "yellow"; // use the yellow form
	}
	public String toString(){
		return String.format("Name=%s,Hours=%d,Salary=$%.2f,Vacation Days=%d",name,getHours(),getSalary(),getVacationDays());
	}
	public static void main (String[]args)
	{

	}
}

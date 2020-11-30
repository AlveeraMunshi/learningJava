package APCSA.Inheritance.files;

/** Janitor.java
 * A class to represent marketers */
class Janitor extends Employee {
	public Janitor(String name){
		super(name);
	}
	public int getHours(){
		return super.getHours() * 2;
	}
	public double getSalary(){
		return super.getSalary() - 10000;
	}
	public int getVacationDays(){
		return super.getVacationDays() / 2;
	}
	public void clean(){
		System.out.println("Workin' for the man!");
	}
}

package APCSA.Inheritance.files;

/** Marketer.java
 * A class to represent marketers */
class Marketer extends Employee {
	public Marketer(String name){
		super(name);
	}
	public double getSalary(){
		return super.getSalary() + 10000;
	}
	public void advertise(){
		System.out.println("Act now, while supplies last!");
	}
}

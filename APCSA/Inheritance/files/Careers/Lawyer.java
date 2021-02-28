package APCSA.Inheritance.files.Careers;

/** Lawyer.java
 * A class to represent Lawyers */
class Lawyer extends Employee{
	public Lawyer(String name){
		super(name);
	}
	public int getVacationDays(){
		return super.getVacationDays() + 5; // 4 weeks vaacation
	}
	public String getVacationForm(){
		return "pink";
	}
	public void sue(){
		System.out.println("I'll see you in court!");
	}
}

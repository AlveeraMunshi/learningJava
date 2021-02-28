package APCSA.Inheritance.files.Careers;

/** HarvardLawyer.java
 * A class to represent Lawyers */
class HarvardLawyer extends Lawyer{
	public HarvardLawyer(String name){
		super(name);
	}
	public double getSalary(){
		return super.getSalary() * 1.2;
	}
	public int getVacationDays(){
		return super.getVacationDays() + 3;
	}
	public String getVacationForm(){
		return super.getVacationForm() + super.getVacationForm() + super.getVacationForm() + super.getVacationForm();
	}
	public void sue(){
		System.out.println("I'll see you in court!");
	}
}

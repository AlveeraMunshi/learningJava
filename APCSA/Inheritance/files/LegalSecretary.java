package APCSA.Inheritance.files;

/** LegalSecretary.java
 * A class to reprsent legal secretaries */
class LegalSecretary extends Secretary{
	public LegalSecretary(String name){
		super(name);
	}
	public double getSalary(){
		return super.getSalary() + 5000.0; // $45,000.00/year
	}
	public void fileLegalBriefs(){
		System.out.println("I could file all day!");
	}
}

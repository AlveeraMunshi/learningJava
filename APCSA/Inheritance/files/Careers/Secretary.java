package APCSA.Inheritance.files.Careers;

/** Secretary.java
 * A class to represent secretaries */
class Secretary extends Employee {
	public Secretary(String name){
		super(name);
	}
	public void takeDictation(String text){
		System.out.println("Taking dictation of text: " + text);
	}
}

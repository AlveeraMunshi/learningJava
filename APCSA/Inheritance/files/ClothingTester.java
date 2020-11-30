package APCSA.Inheritance.files;

class ClothingTester{
	public static void main (String[]args)
	{
    Jeans c1 = new Jeans("medium");
		Tshirt c2 = new Tshirt("small", "red", "cotton");
		Sweatshirt c3 = new Sweatshirt("large", "cyan", true);
		System.out.println(c1.getSize());
		System.out.println(c2.getSize());
		System.out.println(c3.getSize());
		System.out.println(c1.getColor());
		System.out.println(c2.getColor());
		System.out.println(c3.getColor());
		System.out.println(c2.getFabric());
		System.out.println(c3.getHasHood());
	}
}

package APCSA.Objects.files;

import java.lang.Math;

public class Fraction
{
  int numerator;
  int denominator;

  public Fraction(int numerator, int denominator)
  {
    this.numerator = numerator;
    this.denominator = denominator;
  }
  public int getNumerator()
  {
    return numerator;
  }
  public int getDenominator()
  {
    return denominator;
  }
  public void addFraction(Fraction other)
	{
		Fraction newFract = FractionMath.add(this,other);// in this case, "this" refers to the current object
		this.numerator = newFract.numerator;
		this.denominator =  newFract.denominator;
	}
	public void multiplyFraction(Fraction other)
	{
		Fraction newFract = FractionMath.multiply(this,other); // in this case, "this" refers to the current object
		this.numerator = newFract.numerator;
		this.denominator = newFract.denominator;
	}
  public String toString()
  {
    return numerator + "/" + denominator;
  }
  public static void main (String[]args)
  {
  }
}

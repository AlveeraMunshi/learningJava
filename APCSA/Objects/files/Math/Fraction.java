package APCSA.Objects.files.Math;

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
		Fraction newFract = FractionMath.add(this,other);
		this.numerator = newFract.numerator;
		this.denominator =  newFract.denominator;
	}
	public void multiplyFraction(Fraction other)
	{
		Fraction newFract = FractionMath.multiply(this,other);
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

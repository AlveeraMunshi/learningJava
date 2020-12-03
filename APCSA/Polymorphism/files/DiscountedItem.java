package APCSA.Polymorphism.files;

// DiscountedItem inherit from Item
public class DiscountedItem extends Item
{
    private double discount;

    public DiscountedItem(String name, double price, Double discount)
    {
      super(name, price);
      this.discount = discount;
    }
    public double getDiscount()
    {
      return discount;
    }
    public void setDiscount(Double discount)
    {
      this.discount = discount;
    }
    public String toString()
    {
      return super.toString() + " (-" + super.valueToString(discount) + ")";
    }
}

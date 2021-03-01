package APCSA.Comparable.files;

public class Business implements Comparable<Business>
{
  private String name;
  private String location;
  private String phone;
  private double rating;
  public Business(String name, String location, String phone, double rating)
  {
    this.name = name;
    this.location = location;
    this.phone = phone;
    this.rating = rating;
  }
  public String getName()
  {
    return name;
  }
  public String getLocation()
  {
    return location;
  }
  public String getPhone()
  {
    return phone;
  }
  public double getRating()
  {
    return rating;
  }
  public String toString()
  {
    return "Name: " + name + " Location: " + location + " Phone Number: " + phone + " Rating: " + rating + "\n";
  }
  public int compareTo(Business b)
  {
    if (rating < b.getRating())
      return 1;
    else if (rating > b.getRating())
      return -1;
    else
    {
      if (this.name.compareTo(b.getName()) > 0)
        return 1;
      else if (this.name.compareTo(b.getName()) < 0)
        return -1;
      else
      {
        if (this.location.compareTo(b.getLocation()) > 0)
          return 1;
        else if (this.location.compareTo(b.getLocation()) < 0)
          return -1;
        else
        {
          if (this.phone.compareTo(b.getPhone()) > 0)
            return 1;
          else if (this.phone.compareTo(b.getPhone()) < 0)
            return -1;
          else
            return 0;
        }
      }
    }
  }
}

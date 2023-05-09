package Graphs;

public class City {
    private String name;
    private int id;
    public City(String name)
    {
        this.name = name;
        id = name.hashCode();
    }
    public String getName()
    {
        return name;
    }
    public int hashCode()
    {
        return id;
    }
    public String toString()
    {
        return name;
    }
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        City other = (City) o;
        return this.hashCode()==other.hashCode();
    }
}

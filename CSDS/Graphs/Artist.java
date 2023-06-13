package Graphs;

public class Artist {
    String name;
    int hashCode;
    public Artist(String name)
    {
        this.name = name;
        this.hashCode = name.hashCode();
    }
    public String getName()
    {
        return name;
    }
    public int hashCode()
    {
        return hashCode;
    }
    public String toString()
    {
        return name;
    }
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
            return false;
        Artist a = (Artist)o;
        return a.hashCode() == this.hashCode();
    }
}

package Graphs;

public class Artist {
    String name;
    int id;
    public Artist(String name)
    {
        this.name = name;
        this.id = name.hashCode();
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
        if (o == null || !(o instanceof Artist))
            return false;
        Artist a = (Artist)o;
        return a.hashCode() == this.hashCode();
    }
}

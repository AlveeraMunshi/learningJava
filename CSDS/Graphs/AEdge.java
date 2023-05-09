package Graphs;

public class AEdge {
    Artist artist;
    Artist variable;
    int id;
    public AEdge(Artist artist, Artist variable)
    {
        this.artist = artist;
        this.variable = variable;
        this.id = artist.hashCode() + variable.hashCode();
    }
    public Artist getArtist()
    {
        return artist;
    }
    public Artist getVariable()
    {
        return variable;
    }
    public int getId()
    {
        return id;
    }
    public String toString()
    {
        return artist + " is similar to " + variable;
    }
}

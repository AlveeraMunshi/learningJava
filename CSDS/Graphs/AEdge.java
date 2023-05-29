package Graphs;

public class AEdge {
    Artist artist;
    Artist similar;
    int id;
    public AEdge(Artist artist, Artist similar)
    {
        this.artist = artist;
        this.similar = similar;
        this.id = artist.hashCode() + similar.hashCode();
    }
    public Artist getArtist()
    {
        return artist;
    }
    public Artist getVariable()
    {
        return similar;
    }
    public int hashCode()
    {
        return id;
    }
    public String toString()
    {
        return artist + " is similar to " + similar;
    }
    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof AEdge))
            return false;
        AEdge e = (AEdge)o;
        return e.hashCode() == this.hashCode();
    }
}

package Graphs;

import java.util.HashSet;

public class AGraph {
    HashSet<Artist> artists;
    HashSet<AEdge> edges;
    public AGraph()
    {
        artists = new HashSet<Artist>();
        edges = new HashSet<AEdge>();
    }
    public void addArtist(Artist a)
    {
        artists.add(a);
    }
    public void addEdge(AEdge e)
    {
        edges.add(e);
    }
    public HashSet<Artist> getArtists()
    {
        return artists;
    }
    public HashSet<AEdge> getEdges()
    {
        return edges;
    }
}

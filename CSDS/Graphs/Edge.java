package Graphs;

public class Edge {
    private City start, destination;
    private int dist, id;
    public Edge(City start, City destination, int dist)
    {
        this.start = start;
        this.destination = destination;
        this.dist = dist;
        id = start.hashCode() + destination.hashCode();
    }
    public City getStart()
    {
        return start;
    }
    public City getDestination()
    {
        return destination;
    }
    public int getDist()
    {
        return dist;
    }
    public int hashCode()
    {
        return id;
    }
    public String toString()
    {
        return start + " to " + destination + ": " + dist;
    }
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        Edge other = (Edge) o;
        return this.hashCode()==other.hashCode();
    }
}

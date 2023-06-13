package Graphs;

import java.io.*;
import java.util.*;

public class ArtistRunner {
    HashMap<Artist, HashSet<AEdge>> artistMap;
    Artist start, end;
    AGraph graph;
    Stack<Artist> currPath;
    HashSet<Artist> visited;
    public ArtistRunner()
    {
        artistMap = new HashMap<Artist, HashSet<AEdge>>();
        graph = new AGraph();
        
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Graphs/SimilarArtists.txt");
        try 
        {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            while ((text = input.readLine()) != null)
            {
                String[] info = text.split(", "); // split the line into two artists
                Artist a1 = new Artist(info[0]);
                Artist a2 = new Artist(info[1]);
                AEdge e1 = new AEdge(a1, a2);
                AEdge e2 = new AEdge(a2, a1);
                graph.addArtist(a1);
                graph.addArtist(a2);
                graph.addEdge(e1);
                graph.addEdge(e2);
                if (artistMap.containsKey(a1))
                {
                    HashSet<AEdge> temp = artistMap.get(a1); // get the set of edges
                    temp.add(e1); // add the new edge
                    artistMap.put(a1, temp); // put the set back into the map
                }
                else
                {
                    HashSet<AEdge> temp = new HashSet<AEdge>(); // create a new set of edges
                    temp.add(e1); // add the new edge
                    artistMap.put(a1, temp); // put the set into the map
                }
                if (artistMap.containsKey(a2))
                {
                    HashSet<AEdge> temp = artistMap.get(a2); // get the set of edges
                    temp.add(e2); // add the new edge
                    artistMap.put(a2, temp); // put the set back into the map
                }
                else
                {
                    HashSet<AEdge> temp = new HashSet<AEdge>(); // create a new set of edges
                    temp.add(e2); // add the new edge
                    artistMap.put(a2, temp); // put the set into the map
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        System.out.println("Edges - Connecting artists with similar");
        for (AEdge e : graph.getEdges())
            System.out.println("\t" + e);
        System.out.println("Artists - All artists");
        for (Artist startA : graph.getArtists()) // for each artist
        {
            System.out.println(startA);
            for (Artist endA : graph.getArtists()) // for each artist
            {
                if (!endA.equals(startA)) // if the artists are not the same
                {
                    // reset the path and visited
                    currPath = new Stack<Artist>();
                    visited = new HashSet<Artist>();
                    // run the dft, prints path
                    dft(startA, endA);
                }
            }
            System.out.println();
        }
    }
    public void dft(Artist currArtist, Artist dest)
    {
        currPath.push(currArtist);
        visited.add(currArtist);
        if (currArtist.equals(dest))
        {
            printCurrentPath();
        }
        else
        {
            for (AEdge e : graph.getEdges())
            {
                Artist tempartist, tempsimilar;
                tempartist = e.getArtist();
                tempsimilar = e.getVariable();
                if (visited.contains(tempartist) && !visited.contains(tempsimilar))
                {
                    dft(tempsimilar, dest);
                }
                if (!visited.contains(tempartist) && visited.contains(tempsimilar))
                {
                    dft(tempartist, dest);
                }
            }
        }
    }
    public void printCurrentPath()
    {
        String output = "\t";
        while (!currPath.isEmpty())
        {
            output = currPath.pop() + output + " -> ";
            if (!currPath.isEmpty())
                output = " -> " + output;
        }
        System.out.print("\t" + output);
        System.out.println();
    }
    public static void main(String[] args)
    {
        ArtistRunner ar = new ArtistRunner();
    }
}

package Graphs;

import java.io.*;
import java.util.*;

public class DijkstraRunner {
    HashMap<City, HashSet<Edge>> cityMap;
    HashSet<City> cities;
    HashSet<Edge> edges;
    City start, end;
    public DijkstraRunner()
    {
        cities = new HashSet<City>();
        ArrayList<String> cityList = new ArrayList<String>();
        edges = new HashSet<Edge>();
        cityMap = new HashMap<City, HashSet<Edge>>();
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Graphs/citydists.txt");
        try 
        {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text = "";

            while((text=input.readLine())!=null) //while there is still text to read
            {
                String[] info = text.split(","); //split the text by commas
                City c1 = new City(info[0]); //city 1
                City c2 = new City(info[1]); //city 2
                int dist = Integer.parseInt(info[2]); //dist
                if(!cityList.contains(c1.getName())) //if the city is not already in the list
                {
                    cityList.add(c1.getName()); //add it
                }
                if(!cityList.contains(c2.getName())) //if the city is not already in the list
                {
                    cityList.add(c2.getName()); //add it
                }
                //add the city to the set of cities
                cities.add(c1); 
                cities.add(c2);
                //create the edges and add to set of edges
                Edge e1 = new Edge(c1, c2, dist);
                Edge e2 = new Edge(c2, c1, dist);
                edges.add(e1);
                edges.add(e2);
                //add the edges to the hashmap
                if(!cityMap.containsKey(c1))
                {
                    cityMap.put(c1, new HashSet<Edge>());
                }
                if(!cityMap.containsKey(c2))
                {
                    cityMap.put(c2, new HashSet<Edge>());
                }
                //add the edges to the hashmap
                cityMap.get(c1).add(e1);
                cityMap.get(c2).add(e2);
            }
            System.out.println("Cities: ");
            for (City c: cities)
            {
                System.out.println(c);
            }
            System.out.println("\n\nEdges: ");
            for (Edge e: edges)
            {
                System.out.println(e);
            }
            System.out.println("\n\n");
            for (int x = 0; x < cityList.size(); x++)
            {
                for (int y = x+1; y < cityList.size(); y++)
                {
                    for (City city : cities)
                    {
                        if (city.getName().equals(cityList.get(x)))
                        {
                            start = city;
                        }
                        if (city.getName().equals(cityList.get(y)))
                        {
                            end = city;
                        }
                    }
                    Graph graph = new Graph(cities, edges);
                    DijkstrasAlgo dijkstra = new DijkstrasAlgo(graph);
                    dijkstra.createTravelsPaths(start);
                    ArrayList<City> shortestPath = dijkstra.getShortestPath(end);
                    int dist = 0;
                    System.out.println("Shortest path from " + start.getName() + " to " + end.getName() + ": ");
                    
                    for (int i = 0; i < shortestPath.size()-1; i++)
                    {
                        City c1 = shortestPath.get(i);
                        City c2 = shortestPath.get(i+1);
                        System.out.println(c1.getName() + " to " + c2.getName());
                        for (Edge e: cityMap.get(c1))
                        {
                            if(e.getStart().equals(c1) && e.getDestination().equals(c2) || e.getStart().equals(c2) && e.getDestination().equals(c1))
                                dist += e.getDist();
                            
                        }
                    }
                    System.out.println("Distance between: " + dist + " miles\n\n");

                }
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        DijkstraRunner app = new DijkstraRunner();
    }
}

package prereqchecker;

import java.util.*;

/**
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    static ArrayList<String> taken; // array of course IDs
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            //javac -d bin src/prereqchecker/*.java
            //java -cp bin prereqchecker.Eligible adjlist.in eligible.in eligible.out
            return;
        }

        //read in AdjList
        AdjList.setInputFile(args[0]);
        AdjList.setOutputFile("adjlist.out");
        AdjList.readInputWriteOutput();
        
        //set eligible input/output
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);
        // create eligible output
        readInputWriteOutput(AdjList.courses, AdjList.prereqs);
    }
    public static void readInputWriteOutput(String[] courses, String[][] prereqs)
    {
        Graph g = new Graph(courses, prereqs);
        taken = new ArrayList<String>();
        int c = StdIn.readInt(); // number of completed courses
        StdIn.readLine(); // read the rest of the line
        for (int i = 0; i < c; i++)
        {
            taken.add(StdIn.readLine()); //each line has a course id
        }
        addIndirects(g, taken); // all indirect pre reqs have to be taken too
        for (Course co : g.getAdjList())  //check all courses
        {
            //System.out.println("Course: " + co);
            //System.out.println("Eligible: " + isEligible(g, co.getId()));
            //System.out.println("Taken: " + isTaken(g, co.getId()));
            if (isEligible(g, co.getId()) && !isTaken(g, co.getId())) // if eligible and not taken
            {
                StdOut.println(co);
            }
        }
    }
    public static void addIndirects(Graph g, ArrayList<String> taken)
    {
        Queue<String> q = new LinkedList<String>();
        for (int i = 0; i < taken.size(); i++)
        {
            q.add(taken.get(i));
        }
        while (!q.isEmpty())
        {
            Course course = g.findCourse(q.remove());
            if (course != null)
            {
                for (String prereq : course.getPrereqs())
                {
                    if (!isTaken(g, prereq))
                    {
                        taken.add(prereq);
                        q.add(prereq);
                    }
                }
            }
        }
    }
    public static boolean isEligible(Graph g, String course)
    {
        ArrayList<String> prereqs = g.findCourse(course).getPrereqs();
        Queue<String> q = new LinkedList<String>();
        for (String preq : prereqs)
        {
            q.add(preq);
        }
        while (!q.isEmpty())
        {
            String current = q.remove();
            if (!isTaken(g, current))
            {
                return false;
            }
            for (String preq : g.findCourse(current).getPrereqs()) // find prereqs of prereqs
            {
                q.add(preq);
            }
        }
        return true;
    }
    public static boolean isTaken(Graph g, String course)
    {
        for (String takenCourse : taken)
        {
            if (takenCourse.equals(g.findCourse(course).getId()))
            {
                return true;
            }
        }
        return false;
    }
}

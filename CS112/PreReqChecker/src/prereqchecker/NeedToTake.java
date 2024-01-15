package prereqchecker;

import java.util.*;

/**
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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    static ArrayList<String> taken; // array of course IDs
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }
        //javac -d bin src/prereqchecker/*.java
        //java -cp bin prereqchecker.NeedToTake adjlist.in needtotake.in needtotake.out

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
    public static void readInputWriteOutput(String[] courses, String[][] prereqs) {
        Graph g = new Graph(courses, prereqs);
        taken = new ArrayList<>();
        String target = StdIn.readLine(); // target course
        int c = StdIn.readInt(); // number of completed courses
        StdIn.readLine(); // read the rest of the line
        for (int i = 0; i < c; i++) {
            taken.add(StdIn.readLine()); //each line has a course id
        }
        addIndirects(g, taken); // all indirect pre reqs have to be taken too
        ArrayList<String> targetreqs = g.findCourse(target).getPrereqs(); // all direct prereqs
        addIndirects(g, targetreqs); // all indirect prereqs
        for (String prereq : targetreqs) {
            if (!inList(g, taken, prereq)) {  // if prereq is not taken
                StdOut.println(prereq); // print it
            }
        }
    }
    public static void addIndirects(Graph g, ArrayList<String> list) {
        Queue<String> q = new LinkedList<String>();
        for (int i = 0; i < list.size(); i++)
            q.add(list.get(i));
        while (!q.isEmpty()) {
            Course course = g.findCourse(q.remove());
            if (course != null)
                for (String prereq : course.getPrereqs())
                    if (!inList(g, list, prereq)) {
                        list.add(prereq);
                        q.add(prereq);
                    }
        }
    }
    public static boolean inList(Graph g, ArrayList<String> list, String course) {
        for (String coursen : list) {
            if (coursen.equals(g.findCourse(course).getId())) {
                return true;
            }
        }
        return false;
    }
}

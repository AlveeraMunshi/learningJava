package prereqchecker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
        //javac -d bin src/prereqchecker/*.java
        //java -cp bin prereqchecker.ValidPrereq adjlist.in validprereq.in validprereq.out

        //read in AdjList
        AdjList.setInputFile(args[0]);
        AdjList.setOutputFile("adjlist.out");
        AdjList.readInputWriteOutput();
        
        //read in ValidPreReqs, output to output file
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);
        // create output
        readInputWriteOutput(AdjList.courses, AdjList.prereqs);
    }
    public static void setInputFile(String inputFile) {
        StdIn.setFile(inputFile);
    }
    public static void setOutputFile(String outputFile) {
        StdOut.setFile(outputFile);
    }
    public static void readInputWriteOutput(String[] courses, String[][] prereqs) {
        String advancedCourse = StdIn.readLine(); // advanced course
        String prereq = StdIn.readLine(); // direct prereq
        // check if adding pre req creates a cycle
        //create graph
        Graph g = new Graph(courses, prereqs);
        // if prereq ancestor of advanced course, then cycle
        if (isAncestor(g, advancedCourse, prereq))
            StdOut.println("NO");
        else
            StdOut.println("YES");
    }
    public static boolean isAncestor(Graph g, String advancedCourse, String prereq) {
        Queue<String> q = new LinkedList<>();
        q.add(prereq);
        while (!q.isEmpty()) {
            String current = q.remove();
            if (current.equals(advancedCourse)) {
                return true;
            }
            for (String s : g.findCourse(current).getPrereqs()) {
                q.add(s);
            }
        }
        return false;
    }
}

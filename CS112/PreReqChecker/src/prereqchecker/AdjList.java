package prereqchecker;

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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    static String[] courses; // array of course IDs
    static String[][] prereqs; // array of prereq connections
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            //javac -d bin src/prereqchecker/*.java
            //Execute: java -cp bin prereqchecker.AdjList adjlist.in adjlist.out
            return;
        }
        //set input and output files
        setInputFile(args[0]);
        setOutputFile(args[1]);

        // read in the graph from the input file
        // create output
        readInputWriteOutput();
        
    }
    public static void setInputFile(String inputFile) {
        StdIn.setFile(inputFile);
    }
    public static void setOutputFile(String outputFile) {
        StdOut.setFile(outputFile);
    }
    public static void readInputWriteOutput() {
        int a = StdIn.readInt(); // number of courses (vertices)
        StdIn.readLine(); // skip the rest of the line
        courses = new String[a]; // array of course IDs
        for (int i = 0; i < a; i++) {
            courses[i] = StdIn.readLine(); // course ID
        }
        int b = StdIn.readInt(); // number of prereq connections (edges)
        StdIn.readLine(); // skip the rest of the line
        prereqs = new String[b][2]; // array of prereq connections
        for (int i = 0; i < b; i++) {
            prereqs[i][0] = StdIn.readString(); // course id
            prereqs[i][1] = StdIn.readString(); // direct prereq id
            StdIn.readLine(); // skip the rest of the line
        }
        for (int i = 0; i < a; i++) { // go through courses
            StdOut.print(courses[i] + " ");
            for (int j = 0; j < b; j++) { // go through prereqs
                if (courses[i].equals(prereqs[j][0])) { // if course has a prereq
                    StdOut.print(prereqs[j][1] + " ");
                }
            }
            StdOut.println();
        }
    }
    
    public String[] getCourses() {
        return courses;
    }
    public String[][] getPrereqs() {
        return prereqs;
    }
}

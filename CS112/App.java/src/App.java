public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        String arr[]; //declaration
        arr = new String[10]; //allocation of memory
        arr[0] = "112"; //0 is the index
        //indexes reference the memory location
        //arrays contain a bunch of references
        int a[] = new int[9]; //default value is 0
        double b[] = new double[9]; //default value is 0.0
        arr[1].toString(); //null pointer exception
        //b[1] = a[1]; //read first then write, both point to same reference

        //runtime stack - a location where static memory is allocated
        //  each running thread has a private stakc, holding local variables and partial results
        //  bottom of stack is main, first thing called in a running thread
        //  top of stack is the current method being executed
        //  after completing all the methods. stack will be empty. then JVM will destroy the stack and terminate the thread
        //heap - dynamic memory allocation
        //  shared among all JVM threads
        //  run-time data area from which memory for all objects and arrays is allocated
        //  heap storage for objects is reclaimed by an automoatic storage management system known as a garbage collector --> get rid of used but not refernced memory
        //0xFF 
        
    }
}

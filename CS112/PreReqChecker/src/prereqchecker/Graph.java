package prereqchecker;

import java.util.*;

public class Graph {
    private ArrayList<Course> adjalist;
    public Graph(String[] courses, String[][] prereqs) {
        adjalist = new ArrayList<>(); // array of course objects
        for (String n : courses) { // go through course names
            adjalist.add(new Course(n)); // create course object
        }
        for (int i = 0; i < adjalist.size(); i++) // go through courses
        {
            for (int j = 0; j < prereqs.length; j++) // go through prereqs
            {
                if (prereqs[j][0].equals(adjalist.get(i).getId())) // if course has prereq
                {
                    addPrereq(i, prereqs[j][1]);
                    addPostreq(findCourseLocation(prereqs[j][1]), adjalist.get(i).getId());
                }
            }
        }
    }
    public void addPrereq(int courseloc, String prereq)
    {
        adjalist.get(courseloc).addPrereq(prereq);
    }
    public void addPostreq(int courseloc, String postreq)
    {
        adjalist.get(courseloc).addPostreq(postreq);
    }
    public int findCourseLocation(String id) {
        for (int i = 0; i < adjalist.size(); i++) {
            if (id.equals(adjalist.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
    public Course findCourse(String id) {
        for (int i = 0; i < adjalist.size(); i++) {
            if (id.equals(adjalist.get(i).getId())) {
                return adjalist.get(i);
            }
        }
        return null;
    }
    public void printGraph() {
        for (int i = 0; i < adjalist.size(); i++) {
            StdOut.print(adjalist.get(i).getId() + " ");
            for (int j = 0; j < adjalist.get(i).getPrereqs().size(); j++) {
                StdOut.print(adjalist.get(i).getPrereqs().get(j) + " ");
            }
            StdOut.println();
        }
    }
    public ArrayList<Course> getAdjList() {
        return adjalist;
    }
    public int getSize()
    {
        return adjalist.size();
    }
}


package prereqchecker;

import java.util.*;

public class Course {
    private String id;
    private ArrayList<String> prereqs;
    private ArrayList<String> postreqs;
    public Course(String id)
    {
        this.id = id;
        this.prereqs = new ArrayList<String>();
        this.postreqs = new ArrayList<String>();
    }
    public String getId() {
        return id;
    }
    public ArrayList<String> getPrereqs() {
        return prereqs;
    }
    public ArrayList<String> getPostreqs() {
        return postreqs;
    }
    public void addPrereq(String prereq) {
        prereqs.add(prereq);
    }
    public void addPostreq(String postreq) {
        postreqs.add(postreq);
    }
    public String toString()
    {
        return id;
    }
}

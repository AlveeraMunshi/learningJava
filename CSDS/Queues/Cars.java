package Queues;

import java.io.*;
import java.util.*;

public class Cars {
    public Cars()
    {
        Stack<Car> s = new Stack<>();
        Queue<Car> q = new LinkedList<Car>();
        PriorityQueue<Car> pq = new PriorityQueue<Car>();
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Queues/CarData.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            text=reader.readLine();
            while ((text = reader.readLine()) != null)
            {
                String[] pieces = text.split("\t");
                int id = Integer.parseInt(pieces[0]);
                int mpg = Integer.parseInt(pieces[1]);
                int engsize = Integer.parseInt(pieces[2]);
                int hp = Integer.parseInt(pieces[3]);
                int weight = Integer.parseInt(pieces[4]);
                int acc = Integer.parseInt(pieces[5]);
                int origin = Integer.parseInt(pieces[6]);
                int cyl = Integer.parseInt(pieces[7]);
                Car car = new Car(id, mpg, engsize, hp, weight, acc, origin, cyl);
                s.push(car);
                q.add(car);
                pq.add(car);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        int size = pq.size();
        System.out.println("Stack");
        System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%s\n", "Acceleration", "MPG", "HP", "Engine Size", "Weight", "Cylinders", "ID");
        for (int x = 0; x < size; x++)
        {
            Car scar = s.pop();
            System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%s\n", scar.getAcc(), scar.getMPG(), scar.getHP(), scar.getEngSize(), scar.getWeight(), scar.getCyl(), scar.getID());
        }
        System.out.println("Queue");
        System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%s\n", "Acceleration", "MPG", "HP", "Engine Size", "Weight", "Cylinders", "ID");
        for (int x = 0; x < size; x++)
        {
            Car qcar = q.poll();
            System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%s\n", qcar.getAcc(), qcar.getMPG(), qcar.getHP(), qcar.getEngSize(), qcar.getWeight(), qcar.getCyl(), qcar.getID());
        }
        System.out.println("Priority Queue");
        System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%s\n", "Acceleration", "MPG", "HP", "Engine Size", "Weight", "Cylinders", "ID");
        for (int x = 0; x < size; x++)
        {
            Car pqcar = pq.poll();
            System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%s\n", pqcar.getAcc(), pqcar.getMPG(), pqcar.getHP(), pqcar.getEngSize(), pqcar.getWeight(), pqcar.getCyl(), pqcar.getID());
        }
    }
    public static void main (String[]args)
    {
        Cars app = new Cars();
    }
    public class Car implements Comparable<Car>
    {
        private int id, mpg, engsize, hp, weight, acc, origin, cyl;
        public Car(int id, int mpg, int engsize, int hp, int weight, int acc, int origin, int cyl)
        {
            this.id = id;
            this.mpg = mpg;
            this.engsize = engsize;
            this.hp = hp;
            this.weight = weight;
            this.acc = acc;
            this.origin = origin;
            this.cyl = cyl;
        }
        public int getID()
        {
            return id;
        }
        public int getMPG()
        {
            return mpg;
        }
        public int getEngSize()
        {
            return engsize;
        }
        public int getHP()
        {
            return hp;
        }
        public int getWeight()
        {
            return weight;
        }
        public int getAcc()
        {
            return acc;
        }
        public int getOrigin()
        {
            return origin;
        }
        public int getCyl()
        {
            return cyl;
        }
        public String toString()
        {
            return id + " " + mpg + " " + engsize + " " + hp + " " + weight + " " + acc + " " + origin + " " + cyl;
        }
       
        public int compareTo(Car other)
        {
            if (acc != other.getAcc())
                return (acc-other.getAcc()) *-1;

            if (mpg != other.getMPG())
                return (mpg-other.getMPG()) *-1;

            if (hp != other.getHP())
                return (hp-other.getHP())*-1;

            if (engsize != other.getEngSize())
                return (engsize-other.getEngSize())*-1;

            if (weight != other.getWeight())
                return (weight-other.getWeight())*-1;

            if (cyl != other.getCyl())
                return (cyl-other.getCyl())*-1;
            
            return (id-other.getID())*-1;
        }
    }   
}

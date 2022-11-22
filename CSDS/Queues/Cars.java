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
            while ((text = reader.readLine()) != null)
            {
                String[] pieces = text.split(" ");
                String make = pieces[0];
                String model = pieces[1];
                int year = Integer.parseInt(pieces[2]);
                int price = Integer.parseInt(pieces[3]);
                Car car = new Car(make, model, year, price);
                s.push(car);
                q.add(car);
                pq.add(car);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        for (int x = 0; x < q.size(); x++)
        {
            System.out.format("%-20s%-20s%s\n", s.pop(), q.poll(), pq.poll());
        }
    }
    public static void main (String[]args)
    {
        Cars app = new Cars();
    }
    public class Car implements Comparable<Car>
    {
        private String make;
        private String model;
        private int year;
        private int price;
        public Car(String make, String model, int year, int price)
        {
            this.make = make;
            this.model = model;
            this.year = year;
            this.price = price;
        }
        public String getMake()
        {
            return make;
        }
        public String getModel()
        {
            return model;
        }
        public int getYear()
        {
            return year;
        }
        public int getPrice()
        {
            return price;
        }
        public int compareTo(Car other)
        {
            return make.compareTo(other.getMake());
        }
    }   
}

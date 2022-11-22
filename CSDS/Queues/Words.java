package Queues;

import java.io.*;
import java.util.*;

public class Words {
    public Words()
    {
        Queue<String> q = new LinkedList<String>();
        PriorityQueue<String> pq = new PriorityQueue<>();
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Queues/WordInput.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
            {
                text = text.replaceAll(";/'\"\\:\\(\\)", "");
                String[] pieces = text.split(" ");
                for (int x = 0; x < pieces.length; x++)
                {
                    String word = pieces[x];
                    q.add(word);
                    pq.add(word);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.format("%-20s%s\n", "Queue", "Priority Queue");
        for (int x = 0; x < q.size(); x++)
        {
            System.out.format("%-20s%s\n", q.poll(), pq.poll());
        }
    }
    public static void main (String[]args)
    {
        Words app = new Words();
    }
    public class Word implements Comparable<Word>{
        private String word;
        private int length;
        public Word(String word)
        {
            this.word = word;
            this.length = word.length();
        }
        public String toString()
        {
            return word;
        }
        public int getLength()
        {
            return length;
        }
        public int compareTo(Word other)
        {
            return word.toString().compareTo(other.toString()); //multiply by -1 to make descending
        }
    }
}
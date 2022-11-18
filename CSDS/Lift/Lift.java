package Lift;

import java.io.*;
import java.util.ArrayList;

public class Lift{

	int totalPassengers;
	public Lift()
	{
		File name = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Lift/TheLiftFile.txt");
		try{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			while((text=input.readLine()) != null){
				String[] pieces = text.split(" ");
				int floors = Integer.parseInt(pieces[1]);
				int[][] queues = new int[floors][0];

				for(int r=0; r<floors; r++)
				{
					text = input.readLine();
					if(!text.equals(""))
					{
						pieces = text.split(",");
						int[] floorQueue = new int[pieces.length];

						for(int c=0; c<pieces.length; c++)
							floorQueue[c] = Integer.parseInt(pieces[c]);
						queues[r] = floorQueue;
					}
				}
				text = input.readLine();
				pieces = text.split(" ");
				int cap = Integer.parseInt(pieces[1]);
				totalPassengers = 0;

				int[] stops = liftOperation(queues,cap);

				//print output
				System.out.print("Floors Visited: ");
				for(int r=0; r<stops.length; r++)
				{
					System.out.print(stops[r]);
					if(r<stops.length-1)
						System.out.print(", ");
				}
				System.out.println();

			}
		}
		catch(IOException e)
		{
			System.err.println("Fule does not exist");
		}
	}

	public int[] liftOperation(final int[][] queues, final int capacity) 
	{
		ArrayList<ArrayList<Person>> queuesCopy = new ArrayList<ArrayList<Person>>();
		for(int i=0; i<queues.length; i++)
		{
			ArrayList<Person> queue = new ArrayList<Person>();
			for (int j=0; j<queues[i].length; j++)
			{
				Person person = new Person(i, queues[i][j]);
				queue.add(person);
				totalPassengers++;
			}
			queuesCopy.add(queue);
		}
		//System.out.println(queuesCopy);
		Lifter lift = new Lifter(queuesCopy, capacity);
		while(totalPassengers>0 || lift.hasPassengers())
		{
			lift.stop();
			lift.move();
		}
		return lift.getStops();
	}

	public class Lifter
	{
		private ArrayList<ArrayList<Person>> queues;
		private int capacity;
		private int queueSize;
		private int currentFloor;
		private boolean goingUp;
		private ArrayList<Integer> stops;
		private ArrayList<Person> passengers;

		public Lifter(ArrayList<ArrayList<Person>> queues, int capacity) 
		{
			this.queues = queues;
			this.capacity = capacity;
			currentFloor = 0;
			goingUp = true;
			passengers = new ArrayList<Person>();
			stops = new ArrayList<Integer>();
			stops.add(0);
		}

		public String toString()
		{
			return String.format("%-10s%-9s%-6s%-16s%-12s", queueSize, capacity, currentFloor, goingUp, passengers, stops);
		}

		public boolean hasPassengers()
		{
			return passengers.size() > 0;
		}
		public int[] getStops()
		{
			stops.add(0);
			for(int x=0; x<stops.size()-1; x++)
			{
				if(stops.get(x)==stops.get(x+1))
				{
					stops.remove(x);
					x--;
				}
			}
			int[] arr = new int[stops.size()];
			for(int i=0; i<arr.length; i++)
				arr[i] = stops.get(i);
			return arr;
		}

		//go up and down
		public void move()
		{
			//up
			if(goingUp)
			{
				currentFloor++;
				if(currentFloor == queues.size()-1)
					goingUp = !goingUp;
			}
			//down
			else
			{
				currentFloor--;
				if(currentFloor==0)
					goingUp = !goingUp;
			}
		}

		//let people off or on
		public void stop()
		{
			for(int i=0; i<passengers.size(); i++)
			{
				Person person = passengers.get(i);
				person.setCurrent(currentFloor);
				//getting off
				if(person.isGettingOff())
				{
					passengers.remove(i);
					i--;
					stops.add(currentFloor);
				}
			}
			for(int i=0; i<queues.get(currentFloor).size(); i++)
			{
				Person person = queues.get(currentFloor).get(i);
				//getting on
				if((goingUp && person.isGoingUp()) || (!goingUp && person.isGoingDown()))
				{
					if(passengers.size() < capacity)
					{
						passengers.add(queues.get(currentFloor).remove(i));
						totalPassengers--;
						i--;
					}
					stops.add(currentFloor);
				}
			}
		}
	}

	public static class Person
	{
		private int currentFloor;
		private int targetFloor;
		public Person(int currentFloor, int targetFloor)
		{
			this.currentFloor = currentFloor;
			this.targetFloor = targetFloor;
		}

		//person goes up if their floor is higher
		public boolean isGoingUp() 
		{
			return targetFloor > currentFloor;
		}

		//person goes down if their floor is lower
		public boolean isGoingDown() 
		{
			return targetFloor < currentFloor;
		}

		//person gets off at their floor
		public boolean isGettingOff() 
		{
			return targetFloor == currentFloor;
		}

		//current location storage
		public void setCurrent(int current)
		{
			currentFloor = current;
		}

		//print person info (current and intended)
		public String toString()
		{
			return "Curr: " + currentFloor + "\tDest: " +targetFloor+ "\n";
		}

	}


	public static void main(String[]args)
	{
		Lift app = new Lift();
	}
}

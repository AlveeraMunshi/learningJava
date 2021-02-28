package APCSA.Inheritance.files.Computers;

class Computer
{
	int screenSize; //Inches of monitor space
	int memory; //GB of ram
	double batteryLife; //Hours of battery life
	boolean monitor; //Whether or not a monitor is included

	public Computer(){
		screenSize = 0;
		memory = 0;
		batteryLife = 0;
		monitor = false;
	}
	public int getScreenSize()
	{
		return screenSize;
	}
	public int getMemory()
	{
		return memory;
	}
	public double getBatteryLife()
	{
		return batteryLife;
	}
	public boolean monitor()
	{
		return monitor;
	}
	public void setScreenSize(int screenSize)
	{
		this.screenSize = screenSize;
	}
	public void setMemory(int memory)
	{
		this.memory = memory;
	}
	public void setBatteryLife(double batteryLife)
	{
		this.batteryLife = batteryLife;
	}
	public void setScreenSize(boolean monitor)
	{
		this.monitor = monitor;
	}
	public static void main (String[]args)
	{

	}
}

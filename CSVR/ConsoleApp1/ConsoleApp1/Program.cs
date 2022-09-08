// See https://aka.ms/new-console-template for more information
using System.Net;

Console.WriteLine("Name: ");
name = Console.ReadLine(); //Takes input

//Comment

/* multi
 * line
 * comment
 * */

string foo = "Hello"; //String is lowercase now
string bar = "How are you?";
int x = 5;

Console.WriteLine(foo);

double x = 81;

Console.Write(Math.Sqrt(x));

// Prints: 9
//Math is basically the same as java

string str2 = "This is C# Program xsdd_$#%";

// string converted to Upper case 
string upperstr2 = str2.ToUpper();

//upperstr2 contains "THIS IS C# PROGRAM XSDD_$#%"

string str = "Divyesh";

// Finding the index of character  
// which is present in string and 
// this will show the value 5 
int index1 = str.IndexOf('s');

Console.WriteLine("The Index Value of character 's' is " + index1);
//The Index Value of character 's' is 5
string myString = "Divyesh";
string test1 = myString.Substring(2);

//String method are almost identical, just first word is also caps

// Get values from this string with string indexing
string value = "Dot Net Perls";

//variable first contains letter D 
char first = value[0];

//Second contains letter o
char second = value[1];

//last contains letter s
char last = value[value.Length - 1];

//C# has same escape characters as java, \t \n \"


package Maps;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import java.text.NumberFormat;

public class BroadwayMaps {
    public BroadwayMaps()
    {
        ArrayList<Show> shows = new ArrayList<>();
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Maps/Broadway2022.csv");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            text=reader.readLine(); //skip first line
            while ((text = reader.readLine()) != null)
            {
                String[] pieces = text.split(",");
                String date = pieces[0];
                String name = pieces[1];
                String type = pieces[2];
                String theatre = pieces[3];
                long gross = Long.parseLong(pieces[4]);
                int attendance = Integer.parseInt(pieces[5]);
                double percentcapacity = Double.parseDouble(pieces[6]);
                Show show = new Show(date, name, type, theatre, gross, attendance, percentcapacity);
                shows.add(show);
            }
        } catch (IOException e) {
        }
        grossByMonth(shows);
        grossByType(shows);
        attendanceByType(shows);
        grossByShowPerWeek(shows);
        attendanceByShowPerWeek(shows);
        attendanceByTheatre(shows);
    }
    public void printShows(ArrayList<Show> shows)
    {
        for (Show show : shows)
        {
            System.out.println(show);
        }
    }
    public void grossByMonth(ArrayList<Show> shows)
    {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        TreeMap<Integer, Long> grossByMonth = new TreeMap<>(); //String key = month, Long alue = gross
        for (Show show : shows) //for each show
        {
            String month = show.date.substring(0, 2); //month is first two digits of date
            if (grossByMonth.containsKey(show.month)) //if month is already in map
            {
                long gross = grossByMonth.get(show.month); //get current gross
                gross += show.gross; //add show's gross to current gross
                grossByMonth.put(show.month, gross); //put new gross in map
            }
            else
            {
                grossByMonth.put(show.month, show.gross); //put show's gross in map
            }
        }
        for (Integer month : grossByMonth.keySet()) //for each month (key of our map)
        {
            System.out.println(month + " " + nf.format(grossByMonth.get(month))); //print month and gross
        }
    }
    public void grossByType(ArrayList<Show> shows)
    {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        TreeMap<String, Long> grossByType = new TreeMap<>(); //String key = type, Long value = gross
        for (Show show : shows) //for each show
        {
            if (grossByType.containsKey(show.type)) //if type is already in map
            {
                long gross = grossByType.get(show.type); //get current gross
                gross += show.gross; //add show's gross to current gross
                grossByType.put(show.type, gross); //put new gross in map
            }
            else
            {
                grossByType.put(show.type, show.gross); //put show's gross in map
            }
        }
        for (String type : grossByType.keySet()) //for each type (key of our map)
        {
            System.out.println(type + " " + nf.format(grossByType.get(type))); //print type and gross
        }
    }
    public void attendanceByType(ArrayList<Show> shows)
    {
        TreeMap<String, Integer> attendanceByType = new TreeMap<>(); //String key = type, Integer value = attendance
        for (Show show : shows) //for each show
        {
            if (attendanceByType.containsKey(show.type)) //if type is already in map
            {
                int attendance = attendanceByType.get(show.type); //get current attendance
                attendance += show.attendance; //add show's attendance to current attendance
                attendanceByType.put(show.type, attendance); //put new attendance in map
            }
            else
            {
                attendanceByType.put(show.type, show.attendance); //put show's attendance in map
            }
        }
        for (String type : attendanceByType.keySet()) //for each type (key of our map)
        {
            System.out.println(type + " " + attendanceByType.get(type)); //print type and attendance
        }
    }
    public void grossByShowPerWeek(ArrayList<Show> shows) //uses same logic as grossByMonth, just every row is used
    {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        TreeMap<String, Long> grossByShowPerWeek = new TreeMap<>(); //String key = show, Long value = gross
        for (Show show : shows) //for each show
        {
            if (grossByShowPerWeek.containsKey(show.name)) //if show is already in map
            {
                long gross = grossByShowPerWeek.get(show.name); //get current gross
                gross += show.gross; //add show's gross to current gross
                grossByShowPerWeek.put(show.name, gross); //put new gross in map
            }
            else
            {
                grossByShowPerWeek.put(show.name, show.gross); //put show's gross in map
            }
        }
        for (String show : grossByShowPerWeek.keySet()) //for each show (key of our map)
        {
            System.out.println(show + " " + nf.format(grossByShowPerWeek.get(show))); //print show and gross
        }
    }
    public void attendanceByShowPerWeek(ArrayList<Show> shows) //uses same logic as grossByShowPerWeek, just every row is used
    {
        TreeMap<String, Integer> attendanceByShowPerWeek = new TreeMap<>(); //String key = show, Integer value = attendance
        for (Show show : shows) //for each show
        {
            if (attendanceByShowPerWeek.containsKey(show.name)) //if show is already in map
            {
                int attendance = attendanceByShowPerWeek.get(show.name); //get current attendance
                attendance += show.attendance; //add show's attendance to current attendance
                attendanceByShowPerWeek.put(show.name, attendance); //put new attendance in map
            }
            else
            {
                attendanceByShowPerWeek.put(show.name, show.attendance); //put show's attendance in map
            }
        }
        for (String show : attendanceByShowPerWeek.keySet()) //for each show (key of our map)
        {
            System.out.println(show + " " + attendanceByShowPerWeek.get(show)); //print show and attendance
        }
    }
    public void attendanceByTheatre(ArrayList<Show> shows)
    {
        TreeMap<String, Integer> attendanceByTheatre = new TreeMap<>(); //String key = theatre, Integer value = attendance
        for (Show show : shows) //for each show
        {
            if (attendanceByTheatre.containsKey(show.theatre)) //if theatre is already in map
            {
                int attendance = attendanceByTheatre.get(show.theatre); //get current attendance
                attendance += show.attendance; //add show's attendance to current attendance
                attendanceByTheatre.put(show.theatre, attendance); //put new attendance in map
            }
            else
            {
                attendanceByTheatre.put(show.theatre, show.attendance); //put show's attendance in map
            }
        }
        for (String theatre : attendanceByTheatre.keySet()) //for each theatre (key of our map)
        {
            System.out.println(theatre + " " + attendanceByTheatre.get(theatre)); //print theatre and attendance
        }
    }
    public void attendanceByMonth(ArrayList<Show> shows)
    {
        TreeMap<Integer, Integer> attendanceByMonth = new TreeMap<>(); //String key = month, Integer value = attendance
        for (Show show : shows) //for each show
        {
            if (attendanceByMonth.containsKey(show.month)) //if month is already in map
            {
                int attendance = attendanceByMonth.get(show.month); //get current attendance
                attendance += show.attendance; //add show's attendance to current attendance
                attendanceByMonth.put(show.month, attendance); //put new attendance in map
            }
            else
            {
                attendanceByMonth.put(show.month, show.attendance); //put show's attendance in map
            }
        }
        for (Integer month : attendanceByMonth.keySet()) //for each month (key of our map)
        {
            System.out.println(month + " " + attendanceByMonth.get(month)); //print month and attendance
        }
    }
    public void showsByTypePerWeek(ArrayList<Show> shows)
    {
        TreeMap<String, Integer> showsByTypePerWeek = new TreeMap<>(); //String key = type, Integer value = number of shows
        for (Show show : shows) //for each show
        {
            if (showsByTypePerWeek.containsKey(show.type)) //if type is already in map
            {
                int showsInType = showsByTypePerWeek.get(show.type); //get current number of shows
                showsInType++; //add 1 to current number of shows
                showsByTypePerWeek.put(show.type, showsInType); //put new number of shows in map
            }
            else
            {
                showsByTypePerWeek.put(show.type, 1); //put 1 in map
            }
        }
    }
    public class Show
    {
        String date, name, type, theatre;
        long gross;
        int attendance, month, day;
        double percentcapacity;
        public Show(String date, String name, String type, String theatre, long gross, int attendance, double percentcapacity)
        {
            this.date = date;
            this.name = name;
            this.type = type;
            this.theatre = theatre;
            this.gross = gross;
            this.attendance = attendance;
            this.percentcapacity = percentcapacity;
            month = Integer.parseInt(date.substring(0, date.indexOf("/")));
            day = Integer.parseInt(date.substring(date.indexOf("/") + 1, date.lastIndexOf("/")));
        }
    }
    public static void main(String[] args)
    {
        BroadwayMaps b = new BroadwayMaps();
    }
}

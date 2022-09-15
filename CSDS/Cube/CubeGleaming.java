package Cube;

import java.io.*;
import java.util.ArrayList;

public class CubeGleaming {
    public CubeGleaming()
    {

        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Cube/CubeInput.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
            {
                int up = 1;
                int north = 2;
                int west = 3;
                int east = 4;
                int south = 5;
                int down = 6;
                String[] pieces = text.split("");
                int currentkey = 0;
                while (currentkey < pieces.length)
                {
                    String currentletter = pieces[currentkey];
                    System.out.print(currentletter);
                    int ogup = up;
                    int ognorth = north;
                    int ogwest = west;
                    int ogeast = east;
                    int ogsouth = south;
                    int ogdown = down;
                    switch(currentletter)
                    {
                        case "N":
                            up = ogsouth;
                            north = ogup;
                            south = ogdown;
                            down = ognorth;
                            break;
                        case "S":
                            up = ognorth;
                            north = ogdown;
                            south = ogup;
                            down = ogsouth;
                            break;
                        case "W":
                            up = ogwest;
                            east = ogup;
                            west = ogdown;
                            down = ogeast;
                            break;
                        case "E":
                            up = ogeast;
                            east = ogdown;
                            west = ogup;
                            ogdown = ogwest;
                            break;
                    }
                    currentkey++;
                }
                System.out.println("Up: " + up);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public static void main(String[]args)
    {
        CubeGleaming app = new CubeGleaming();
    }
}

package model;

import util.JSONReader;

import java.util.ArrayList;
import java.util.Hashtable;

public class Keyboard
{
    private ArrayList<ArrayList<Key>> keys;

    public Keyboard(ArrayList<ArrayList<Key>> keys)
    {
        this.keys = keys;
    }



    private static String[] getFingerAndHand(int line, int column)
    {
        String[] res = new String[2];

        if (line == 0)
        {
            if (column < 3)
            {
                res[0] = "Pinky";
                res[1] = "false";
            }
            else if (column == 3)
            {
                res[0] = "Ring Finger";
                res[1] = "false";
            }
            else if (column == 4)
            {
                res[0] = "Middle Finger";
                res[1] = "false";
            }
            else if (column == 5 || column == 6)
            {
                res[0] = "Index Finger";
                res[1] = "false";
            }
            else if (column == 7 || column == 8)
            {
                res[0] = "Index Finger";
                res[1] = "true";
            }
            else if (column == 9)
            {
                res[0] = "Middle Finger";
                res[1] = "true";
            }
            else if (column == 10)
            {
                res[0] = "Ring Finger";
                res[1] = "true";
            }
            else
            {
                res[0] = "Pinky";
                res[1] = "true";
            }
        }
        else if (line == 1 || line == 2)
        {
            if (column == 0)
            {
                res[0] = "Pinky";
                res[1] = "false";
            }
            else if (column == 1)
            {
                res[0] = "Ring Finger";
                res[1] = "false";
            }
            else if (column == 2)
            {
                res[0] = "Middle Finger";
                res[1] = "false";
            }
            else if (column == 3 || column == 4)
            {
                res[0] = "Index Finger";
                res[1] = "false";
            }
            else if (column == 5 || column == 6)
            {
                res[0] = "Index Finger";
                res[1] = "true";
            }
            else if (column == 7)
            {
                res[0] = "Middle Finger";
                res[1] = "true";
            }
            else if (column == 8)
            {
                res[0] = "Ring Finger";
                res[1] = "true";
            }
            else
            {
                res[0] = "Pinky";
                res[1] = "true";
            }
        }
        else if (line == 3)
        {
            if (column < 2)
            {
                res[0] = "Pinky";
                res[1] = "false";
            }
            else if (column == 2)
            {
                res[0] = "Ring Finger";
                res[1] = "false";
            }
            else if (column == 3)
            {
                res[0] = "Middle Finger";
                res[1] = "false";
            }
            else if (column == 4 || column == 5)
            {
                res[0] = "Index Finger";
                res[1] = "false";
            }
            else if (column == 6 || column == 7)
            {
                res[0] = "Index Finger";
                res[1] = "true";
            }
            else if (column == 8)
            {
                res[0] = "Middle Finger";
                res[1] = "true";
            }
            else if (column == 9)
            {
                res[0] = "Ring Finger";
                res[1] = "true";
            }
            else
            {
                res[0] = "Pinky";
                res[1] = "true";
            }
        }

        return res;
    }

    private static boolean isTrue(String s)
    {
        return s.compareTo("true") == 0;
    }


    private static ArrayList<ArrayList<Key>> keyboardFromHT(Hashtable<String, String[][][]> ht)
    {
        if (ht == null)
        {
            return null;
        }

        ArrayList<ArrayList<Key>> res = new ArrayList<>();

        // Il faut avoir que un seul key
        for (String key : ht.keySet())
        {
            String[][][] rows = ht.get(key);
            for (int i = 0; i < rows.length; i++)
            {
                String[][] row = rows[i];
                ArrayList<Key> rowAL = new ArrayList<>();
                for (int j = 0; j < row.length; j++)
                {
                    for (int k = 0; k < row[j].length; k++)
                    {
                        if (row[j][k] != null)
                        {
                            // Kid named finger
                            String[] kidney_failure = getFingerAndHand(j, k); // to get finger and hand
                            String finger = kidney_failure[0];
                            boolean rightHand = isTrue(kidney_failure[1]);


                            boolean isShifted = j == 1;
                            boolean isAltGr = j == 2;


                            Key toAdd = new Key(row[j][k], j, k, finger, rightHand, isShifted, isAltGr);
                            rowAL.add(toAdd);
                        }
                    }
                    res.add(rowAL);
                }
            }
        }

        return res;
    }



    public static Keyboard keyboardFromJSON(String filePath)
    {
        Hashtable<String, String[][][]> ht = JSONReader.readKeyboardHTfromJSON(filePath);
        if (ht == null)
        {
            return null;
        }

        return new Keyboard(keyboardFromHT(ht));
    }
}

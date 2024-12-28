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



    private static String[] getFingerAndHand(int i, int j)
    {
        String[] res = new String[2];

        if (i == 0)
        {
            if (j < 3)
            {
                res[0] = "Pinky";
                res[1] = "true";
            }
            else if (j == 3)
            {
                res[0] = "Ring Finger";
                res[1] = "true";
            }


        }

        return res;
    }


    private static ArrayList<Key> sortHT(Hashtable<String, String[][][]> ht)
    {
        ArrayList<ArrayList<Key>> res = new ArrayList<>();

        for (String key : ht.keySet())
        {
            String[][][] rows = ht.get(key);
            for (int i = 0; i < rows.length; i++)
            {
                String[][] row = rows[i];
                ArrayList<Key> rowAL = new ArrayList<>();
                for (int j = 0; j < row.length; j++)
                {
                    String finger = "";
                    boolean rightHand = false;


                    boolean isShifted = false;
                    boolean isAltGr = false;

                    if (i == 0)
                    {
                        if (j < 3)
                        {
                            finger = "Pinky";
                            rightHand = true;
                        }
                        else if (j == 3)
                        {
                            finger = "Ring Finger";
                            rightHand = true;
                        }

                    }

                    Key toAdd = new Key(row[j][0], i, j, finger, rightHand, isShifted, isAltGr);
                    rowAL.add(toAdd);
                }

            }


        }

        return null;
    }


    public static Keyboard keyboardFromHT(Hashtable<String[], Integer> ht)
    {


        return null;
    }

    public static Keyboard keyboardFromJSON(String filePath)
    {
        Hashtable<String[], Integer> ht = JSONReader.readHashtableFromJSON(filePath);
        if (ht == null)
        {
            return null;
        }

        return keyboardFromHT(ht);
    }
}

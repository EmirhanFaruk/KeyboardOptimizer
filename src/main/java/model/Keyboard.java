package model;

import util.JSONReader;

import java.util.ArrayList;
import java.util.Hashtable;

public class Keyboard
{
    private final ArrayList<ArrayList<Key>> keys;

    public Keyboard(ArrayList<ArrayList<Key>> keys)
    {
        this.keys = keys;
    }


    /**
     * Gets the finger and the hand of given coordinates of the key.
     * @param line line
     * @param column column
     * @return result in: {finger_name, isRight}
     */
    private static String[] getFingerAndHand(int line, int column)
    {
        String[] res = new String[2];
        res[0] = null;
        res[1] = null;

        if (line == 0 || line == 3)
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
            if (column == 0 || column == 1)
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
        else if (line == 4)
        {
            // TODO: How to know if space key is pressed by right or left?
            if (column < 4)
            {
                res[0] = "Thumb";
                res[1] = "false";
            }
            else
            {
                res[0] = "Thumb";
                res[1] = "true";
            }
        }

        return res;
    }

    /**
     * Checks if string is equals to true (idk if this func is needed lol).
     * @param s string
     * @return result
     */
    private static boolean isTrue(String s)
    {
        return s.compareTo("true") == 0;
    }


    /**
     * Turns hashtable into a keyboard object.
     * @param ht the hashtable
     * @return result
     */
    private static ArrayList<ArrayList<Key>> keyboardFromHT(Hashtable<String, String[][][]> ht)
    {
        if (ht == null)
        {
            return null;
        }

        ArrayList<ArrayList<Key>> res = new ArrayList<>();
        ArrayList<Key> normal = new ArrayList<>();
        ArrayList<Key> shift = new ArrayList<>();
        ArrayList<Key> altgr = new ArrayList<>();

        // Il faut avoir que un seul key
        for (String key : ht.keySet())
        {
            String[][][] rows = ht.get(key);
            for (int i = 0; i < rows.length; i++)
            {
                String[][] row = rows[i];
                for (int j = 0; j < row.length; j++)
                {
                    for (int k = 0; k < row[j].length; k++)
                    {
                        if (row[j][k] != null)
                        {
                            // Kid named finger
                            String[] kidney_failure = getFingerAndHand(j, k); // to get finger and hand
                            if (kidney_failure[0] != null)
                            {
                                String finger = kidney_failure[0];
                                boolean rightHand = isTrue(kidney_failure[1]);


                                boolean isShifted = j == 1;
                                boolean isAltGr = j == 2;

                                Key toAdd = new Key(row[j][k], j, k, finger, rightHand, isShifted, isAltGr);

                                if ( !(isShifted || isAltGr) )
                                {
                                    normal.add(toAdd);
                                }
                                else if (isShifted)
                                {
                                    shift.add(toAdd);
                                }
                                else
                                {
                                    altgr.add(toAdd);
                                }
                            }
                        }
                    }
                }
            }
        }

        res.add(normal);
        res.add(shift);
        res.add(altgr);

        return res;
    }


    /**
     * Gets the keyboard from the given path(it has to be a JSON file)
     * @param filePath path of the file
     * @return a brand-new keyboard from the given file
     */
    public static Keyboard keyboardFromJSON(String filePath)
    {
        Hashtable<String, String[][][]> ht = JSONReader.readKeyboardHTfromJSON(filePath);
        if (ht == null)
        {
            return null;
        }

        return new Keyboard(keyboardFromHT(ht));
    }


    /**
     * Calculates finger totals, right and left.
     * <p> first element is right and the other one is left
     * <p> 0 -> Index Finger
     * <p> 1 -> Middle Finger
     * <p> 2 -> Ring Finger
     * <p> 3 -> Pinky
     * <p> 4 -> Thumb
     * <p>
     * Ex: res[0][0] is the num of right index finger
     * @return result
     */
    private int[][] calculateFinger()
    {
        int[][] res = new int[5][2];
        String[] fingers = {"Index Finger", "Middle Finger", "Ring Finger", "Pinky", "Thumb"};

        for (ArrayList<Key> keyL : keys)
        {
            for (Key key : keyL)
            {
                if (key.isRightHand())
                {
                    for (int i = 0; i < fingers.length; i++)
                    {
                        if (fingers[i].equals(key.getFinger()))
                        {
                            res[i][0] += 1;
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < fingers.length; i++)
                    {
                        if (fingers[i].equals(key.getFinger()))
                        {
                            res[i][1] += 1;
                        }
                    }
                }
            }
        }

        return res;
    }


    /**
     * Calculates key right and left totals.
     * @return [0] -> right, [1] -> left
     */
    private int[] calculateSide()
    {
        int[] res = new int[2];
        for (ArrayList<Key> keyL : keys)
        {
            for (Key key : keyL)
            {
                if (key.isRightHand())
                {
                    res[0] += 1;
                }
                else
                {
                    res[1] += 1;
                }
            }
        }

        return res;
    }


    /**
     * Gets the string for the finger part of the toString func.
     * @return finger part
     */
    private String fingerGrToString()
    {
        String res = "";

        int[][] kid_named_finger = calculateFinger();

        res += "\nIndex finger right: " + kid_named_finger[0][0] + ", left: " + kid_named_finger[0][1] + "\n";
        res += "Middle finger right: " + kid_named_finger[1][0] + ", left: " + kid_named_finger[1][1] + "\n";
        res += "Ring finger right: " + kid_named_finger[2][0] + ", left: " + kid_named_finger[2][1] + "\n";
        res += "Pinky right: " + kid_named_finger[3][0] + ", left: " + kid_named_finger[3][1] + "\n";
        res += "Thumb right: " + kid_named_finger[4][0] + ", left: " + kid_named_finger[4][1] + "\n";

        return res;
    }


    /**
     * Gets the string for the finger part of the toString func.
     * @param keyL Key list(normal, shifted or altgr)
     * @param i index
     * @return result
     */
    private String keyGrToString(ArrayList<Key> keyL, int i)
    {
        String res = "";
        if (i == 0)
        {
            res += "Normal Keys (size: " + keyL.size() + "):\n";
            for (Key key : keyL)
            {
                res += key.toString() + "\n";
            }
        }
        else if (i == 1)
        {
            res += "Shift Keys (size: " + keyL.size() + "):\n";
            for (Key key : keyL)
            {
                res += key.toString() + "\n";
            }
        }
        else if (i == 2)
        {
            res += "AltGr Keys (size: " + keyL.size() + "):\n";
            for (Key key : keyL)
            {
                res += key.toString() + "\n";
            }
        }

        return res;
    }


    public String toString()
    {
        String res = "\n==================\nKeyboard.\n";
        int[] rl = calculateSide();
        res += "Right hand keys: " + rl[0] + ", ";
        res += "Left hand keys: " + rl[1] + ".\n";

        res += fingerGrToString();

        for (int i = 0; i < keys.size(); i++)
        {
            ArrayList<Key> keyL = keys.get(i);
            res += keyGrToString(keyL, i);
        }
        res += "\n===================\n";
        return res;
    }
}

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



    private static ArrayList<Key> sortHT(Hashtable<String[], Integer> ht)
    {

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

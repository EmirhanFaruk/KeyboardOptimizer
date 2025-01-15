package view;

import model.analyse.AnalyseFile;
import model.analyse.AnalyseFileChars;
import model.couple.Couple;

import java.util.ArrayList;
import java.util.Hashtable;

public class DisplayHT
{
    /**
     * Prints "sorted ht".
     * @param al Sorted hashtable
     */
    public static <K> void print(ArrayList<Couple<K>> al)
    {
        for (Couple<K> c : al)
        {
            System.out.println(c);
        }
    }



    /**
     * Prints the given HashTable. If given a text name,
     * it will be printed at the top of the HashTable as a "Header".
     * @param ht HashTable
     * @param text_name Text name
     * @param n N-gramme
     */
    public static void print(Hashtable<?, Integer> ht, boolean isList, String text_name, int n)
    {
        // Printing the header
        if (!(text_name == null || text_name.equals("")))
        {
            System.out.print("\nHashTable de " + text_name + " :\n");
            if (n != -1)
            {
                System.out.println(", en " + n + "-grammes :\n");
            }
            else
            {
                System.out.println("\n");
            }
        }

        if (ht != null)
        {
            // Sort the hashtable
            if (isList)
            {
                ArrayList<Couple<String[]>> sortedHT = AnalyseFile.sortHT((Hashtable<String[], Integer>) ht);
                // Print the sorted hashtable
                print(sortedHT);
            }
            else
            {
                ArrayList<Couple<String>> sortedHT = AnalyseFileChars.sortHT((Hashtable<String, Integer>) ht);
                // Print the sorted hashtable
                print(sortedHT);
            }
        }
        else
        {
            System.out.println("Hashtable est null");
        }
    }


    /**
     * Prints the given HashTable.
     * @param ht HashTable
     */
    public static <K> void print(Hashtable<K, Integer> ht, boolean isList)
    {
        print(ht, isList, "", -1);
    }


    /**
     * Prints the given HashTable. If given a text name,
     * it will be printed at the top of the HashTable as a "Header".
     * @param ht HashTable
     * @param text_name Text name
     */
    public static <K> void print(Hashtable<K, Integer> ht, boolean isList, String text_name)
    {
        print(ht, isList, text_name, -1);
    }
}

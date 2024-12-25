package view;

import model.AnalyseFile;
import model.Couple;

import java.util.ArrayList;
import java.util.Hashtable;

public class DisplayHT
{
    /**
     * Prints "sorted ht".
     * @param al Sorted hashtable
     */
    public static void printAL(ArrayList<Couple> al)
    {
        for (Couple c : al)
        {
            System.out.println(c);
        }
    }



    /**
     * Prints the given HashTable.
     * @param ht HashTable
     */
    public static void print(Hashtable<String[], Integer> ht)
    {
        if (ht != null)
        {
            ArrayList<Couple> sortedHT;

            // Sort the hashtable
            sortedHT = AnalyseFile.sortHT(ht);

            // Print the sorted hashtable
            printAL(sortedHT);
        }
        else
        {
            System.out.println("Hashtable est null");
        }
    }



    /**
     * Prints the given HashTable. If given a text name,
     * it will be printed at the top of the HashTable as a "Header".
     * @param ht HashTable
     * @param text_name Text name
     */
    public static void print(Hashtable<String[], Integer> ht, String text_name)
    {
        // Printing the header
        if (!(text_name == null || text_name.equals("")))
        {
            System.out.println("\nHashTable de " + text_name + " :\n");
        }

        // Printing the hashtable using another func
        print(ht);

        System.out.println();
    }

    /**
     * Prints the given HashTable. If given a text name,
     * it will be printed at the top of the HashTable as a "Header".
     * @param ht HashTable
     * @param text_name Text name
     * @param n N-gramme
     */
    public static void print(Hashtable<String[], Integer> ht, String text_name, int n)
    {
        // Printing the header
        if (!(text_name == null || text_name.equals("")))
        {
            System.out.println("\nHashTable de " + text_name + ", en " + n + "-grammes :\n");
        }

        // Printing the hashtable using another func
        print(ht);

        System.out.println();
    }
}

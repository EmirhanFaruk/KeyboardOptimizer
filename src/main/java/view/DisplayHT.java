package view;

import model.analyse.AnalyseFile;
import model.analyse.AnalyseFileChars;
import model.couple.StCouple;
import model.couple.StLCouple;

import java.util.ArrayList;
import java.util.Hashtable;

public class DisplayHT
{
    /**
     * Prints "sorted ht" of StLCouple.
     * @param al Sorted hashtable
     */
    public static void printALStL(ArrayList<StLCouple> al)
    {
        for (StLCouple c : al)
        {
            System.out.println(c);
        }
    }

    /**
     * Prints "sorted ht" of StCouple.
     * @param al Sorted hashtable
     */
    public static void printALSt(ArrayList<StCouple> al)
    {
        for (StCouple c : al)
        {
            System.out.println(c);
        }
    }



    /**
     * Prints the given HashTable of StLCouple.
     * @param ht HashTable
     */
    public static void printStL(Hashtable<String[], Integer> ht)
    {
        if (ht != null)
        {
            ArrayList<StLCouple> sortedHT;

            // Sort the hashtable
            sortedHT = AnalyseFile.sortHT(ht);

            // Print the sorted hashtable
            printALStL(sortedHT);
        }
        else
        {
            System.out.println("Hashtable est null");
        }
    }


    /**
     * Prints the given HashTable of StCouple.
     * @param ht HashTable
     */
    public static void printSt(Hashtable<String, Integer> ht)
    {
        if (ht != null)
        {
            ArrayList<StCouple> sortedHT;

            // Sort the hashtable
            sortedHT = AnalyseFileChars.sortHT(ht);

            // Print the sorted hashtable
            printALSt(sortedHT);
        }
        else
        {
            System.out.println("Hashtable est null");
        }
    }



    /**
     * Prints the given HashTable of StLCouple. If given a text name,
     * it will be printed at the top of the HashTable as a "Header".
     * @param ht HashTable
     * @param text_name Text name
     */
    public static void printAllStL(Hashtable<String[], Integer> ht, String text_name)
    {
        // Printing the header
        if (!(text_name == null || text_name.equals("")))
        {
            System.out.println("\nHashTable de " + text_name + " :\n");
        }

        // Printing the hashtable using another func
        printStL(ht);

        System.out.println();
    }


    /**
     * Prints the given HashTable of StCouple. If given a text name,
     * it will be printed at the top of the HashTable as a "Header".
     * @param ht HashTable
     * @param text_name Text name
     */
    public static void printAllSt(Hashtable<String, Integer> ht, String text_name)
    {
        // Printing the header
        if (!(text_name == null || text_name.equals("")))
        {
            System.out.println("\nHashTable de " + text_name + " :\n");
        }

        // Printing the hashtable using another func
        printSt(ht);

        System.out.println();
    }


    /**
     * Prints the given HashTable of StLCouple. If given a text name,
     * it will be printed at the top of the HashTable as a "Header".
     * @param ht HashTable
     * @param text_name Text name
     * @param n N-gramme
     */
    public static void printAllStLN(Hashtable<String[], Integer> ht, String text_name, int n)
    {
        // Printing the header
        if (!(text_name == null || text_name.equals("")))
        {
            System.out.println("\nHashTable de " + text_name + ", en " + n + "-grammes :\n");
        }

        // Printing the hashtable using another func
        printStL(ht);

        System.out.println();
    }



    /**
     * Prints the given HashTable of StCouple. If given a text name,
     * it will be printed at the top of the HashTable as a "Header".
     * @param ht HashTable
     * @param text_name Text name
     * @param n N-gramme
     */
    public static void printAllStN(Hashtable<String, Integer> ht, String text_name, int n)
    {
        // Printing the header
        if (!(text_name == null || text_name.equals("")))
        {
            System.out.println("\nHashTable de " + text_name + ", en " + n + "-grammes :\n");
        }

        // Printing the hashtable using another func
        printSt(ht);

        System.out.println();
    }
}

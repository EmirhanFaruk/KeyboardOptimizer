package view;

import java.util.Hashtable;

public class DisplayHT
{
    /**
     * Prints the given HashTable.
     * @param ht HashTable
     */
    public static void print(Hashtable<String, Integer> ht)
    {
        if (ht != null)
        {
            // Printing the key-value couples
            for (String key : ht.keySet())
            {
                System.out.println("_" + key + "_: " + ht.get(key));
            }
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
    public static void print(Hashtable<String, Integer> ht, String text_name)
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
    public static void print(Hashtable<String, Integer> ht, String text_name, int n)
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

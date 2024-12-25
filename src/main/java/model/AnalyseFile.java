package model;

import view.DisplayHT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;

public class AnalyseFile
{
    private static char[] two_key_combs =  {'î', 'â', 'ê', 'ô', 'û',
                                            };


    /**
     * Returns true if ch is uppercase.
     * @param ch the character
     * @return result
     */
    private static boolean isUC(char ch)
    {
        return Character.isUpperCase(ch);
    }


    /**
     * Checks if the combination is in the list depending on the n
     * @param ch character to look for
     * @param n n-gramme
     * @return result
     */
    private static boolean inCh(char ch, int n)
    {
        if (n < )
        for (int i = 0; i < list.length; i++)
        {
            if (list[i] == ch)
            {
                return true;
            }
        }

        return false;
    }



    /**
     * Returns a list of keys, depending on comb.
     * @param comb n-long string
     * @param n n-gramme
     * @return result. null if failed somehow
     */
    private static String[] processString(String comb, int n)
    {
        String[] res = new String[n];
        int index = 0;

        for (int i = 0; i < n; i++)
        {
            char c = comb.charAt(i);
            if(isUC(c))
            {

            }
            if (inCh(c, n))
            {

            }
        }

        return res;
    }



    /**
     * Returns the hashtable of the given text, containing the occurence number
     * of the character combinations of given n.
     * @param text Given text
     * @param ht HashTable. If null, new one will be returned after being processed.
     *           If not, the process will be added to the given one.
     * @param n The n of N-gramme
     * @return The result
     */
    public static Hashtable<String[], Integer> getHTofText(String text, Hashtable<String[], Integer> ht, int n)
    {
        // If no hashtable given, make one
        if (ht == null)
        {
            ht = new Hashtable<>();
        }

        // If n is bigger than the length of the text, then impossible to get the hashtable
        int length = text.length();
        if (length - 1 < n)
        {
            // Might change this to an exception later
            System.out.println("n plus grand que la longueur de la texte. Renvoie de null...");
            return null;
        }

        // Getting the hashtable.
        // Maybe better if used threads for a bigger text?
        for (int i = 0; i < length - n; i++)
        {
            // Getting the char combination
            String comb = text.substring(i, i + n);

            // If it exists, we increment its value by 1
            if (ht.containsKey(comb))
            {
                int val = ht.get(comb);
                ht.put(comb, val + 1);
            }
            else
            {
                // If not, just add it to the hashtable
                ht.put(comb, 1);
            }
        }

        return ht;
    }


    /**
     * Calls getHTofText(text, null, n)
     * @param text Given text
     * @param n The n of N-gramme
     * @return The result
     */
    public static Hashtable<String[], Integer> getHTofText(String text, int n)
    {
        return getHTofText(text, null, n);
    }


    /**
     * Sorts a hashtable, puts result in an ArrayList.
     * @param ht Hashtable to sort
     * @return Sorted in ArrayList
     */
    public static ArrayList<Couple> sortHT(Hashtable<String[], Integer> ht)
    {
        // If ht is null, return null
        if (ht == null)
        {
            return null;
        }

        ArrayList<Couple> couples = new ArrayList<>();

        // Add the elements to the list
        for (String[] key : ht.keySet())
        {
            couples.add(new Couple(key, ht.get(key)));
        }

        // Sort the list using the func in Couple
        couples.sort(Couple::compareTo);

        // Reverse the list to get the biggest values at the top
        couples.sort(Comparator.reverseOrder());

        return couples;
    }




    public static void test()
    {
        for (int i = 1; i < 4; i++)
        {
            System.out.println("================================================");
            Hashtable<String[], Integer> ht = AnalyseFile.getHTofText(ReadFile.text, i);
            DisplayHT.print(ht, "Bee Movie Script",i);
        }
    }
}

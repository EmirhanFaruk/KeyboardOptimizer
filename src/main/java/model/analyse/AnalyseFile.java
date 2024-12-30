package model.analyse;

import model.ReadFile;
import model.couple.StLCouple;
import util.JSONWriter;
import view.DisplayHT;

import java.util.*;

public class AnalyseFile
{
    private static final char[] two_key_combs_hat =  {'î', 'â', 'ê', 'ô', 'û'};

    private static final char[] three_key_combs_dp = {'ï', 'ä', 'ë'};


    private static boolean mem(char ch, char[] list)
    {
        for (char chl : list)
        {
            if (chl == ch)
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Tourner une caractere d'un clavier a une combinaison s'il y en a
     * @param ch caractere a regarder
     * @return resultat
     */
    private static String[] getCh(char ch)
    {
        // Honestly I hate this but for now, this should work
        if (mem(ch, two_key_combs_hat))
        {
            // {'î', 'â', 'ê', 'ô', 'û'}
            return new String[]{"^", ch + ""};
        }
        if (mem(ch, three_key_combs_dp))
        {
            // {'ï', 'ä', 'ë'}
            return new String[]{"Shift", "^", ch + ""};
        }
        if (ch >= 48 && ch <= 57)
        {
            // Numbers
            return new String[]{"Shift", ch + ""};
        }
        if (ch >= 65 && ch <= 90)
        {
            // Uppercase letters
            return new String[]{"Shift", ch + ""};
        }
        if (ch >= 97 && ch <= 122)
        {
            // Lowercase letters
            return new String[]{ch + ""};
        }
        if (ch == ' ')
        {
            return new String[]{"Space"};
        }
        if (ch == '\n')
        {
            return new String[]{"Enter"};
        }
        // Special Characters using Shift
        if (ch == '?')
        {
            return new String[]{"Shift", ","};
        }
        if (ch == '.')
        {
            return new String[]{"Shift", ";"};
        }
        if (ch == '/')
        {
            return new String[]{"Shift", ":"};
        }
        if (ch == '>')
        {
            return new String[]{"Shift", "<"};
        }
        if (ch == '+')
        {
            return new String[]{"Shift", "+"};
        }
        if (ch == '£')
        {
            return new String[]{"Shift", "$"};
        }
        if (ch == '%')
        {
            return new String[]{"Shift", "ù"};
        }
        if (ch == 'µ')
        {
            return new String[]{"Shift", "*"};
        }
        if (ch == '°')
        {
            return new String[]{"Shift", ")"};
        }

        // Special characters using AltGr
        if (ch == '#')
        {
            return new String[]{"AltGr", "\""};
        }
        if (ch == '{')
        {
            return new String[]{"AltGr", "'"};
        }
        if (ch == '[')
        {
            return new String[]{"AltGr", "("};
        }
        if (ch == '|')
        {
            return new String[]{"AltGr", "-"};
        }
        if (ch == '\\')
        {
            return new String[]{"AltGr", "_"};
        }
        if (ch == '@')
        {
            return new String[]{"AltGr", "à"};
        }
        if (ch == ']')
        {
            return new String[]{"AltGr", ")"};
        }
        if (ch == '}')
        {
            return new String[]{"AltGr", "="};
        }


        // If nothing matches, return the character
        return new String[]{ch + ""};
    }


    /**
     * Checks if the combination can be in the list depending on the remaining length.
     * If lenght exceeds, null is returned.
     * @param ch character to look for
     * @param len remaining length
     * @return result
     */
    private static String[] inCh(char ch, int len)
    {
        String[] res = getCh(ch);
        if (len >= res.length)
        {
            return res;
        }

        return null;
    }


    /**
     * Concatenates 2 lists.
     * @param l1 list 1
     * @param l2 list 2
     * @return result
     */
    private static String[] addToList(String[] l1, String[] l2)
    {
        String[] res = new String[l1.length + l2.length];
        int i = 0;
        for (; i < l1.length; i++)
        {
            res[i] = l1[i];
        }
        for (int j = 0; j < l2.length; j++)
        {
            res[i++] = l2[j];
        }

        return res;
    }

    /**
     * Returns a list of keys, depending on comb.
     * @param comb n-long string
     * @param n n-gramme
     * @return result. null if failed somehow
     */
    private static String[] processString(String comb, int n)
    {
        String[] res = new String[]{};
        int index = 0;

        for (int i = 0; i < n; i++)
        {
            char c = comb.charAt(i);
            String[] temp = inCh(c, n - index);
            if (temp != null)
            {
                res = addToList(res, temp);
                index += temp.length;
            }
            else
            {
                return null;
            }
        }

        return res;
    }


    /**
     * Checks if the elements of two given lists are the same.
     * @param l1 list 1
     * @param l2 list 2
     * @return result
     */
    private static boolean sameList(String[] l1, String[] l2)
    {
        if (l1.length == l2.length)
        {
            for (int i = 0; i < l1.length; i++)
            {
                if ( !( l1[i].equals(l2[i]) ) )
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }


    /**
     * Check if the hashtable contains the key(given list).
     * Returns its value if exists, -1 if not.
     * @param ht hashtable
     * @param list given list
     * @return result
     */
    private static int findNIncrement(Hashtable<String[], Integer> ht, String[] list)
    {
        for (String[] key : ht.keySet())
        {
            if (sameList(list, key))
            {
                int val = ht.get(key);
                ht.put(key, val + 1);
                return val + 1;
            }
        }

        return -1;
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

            // Getting the key combinations
            String[] comp_a_comb = processString(comb, n);

            if (comp_a_comb != null)
            {
                // If it exists, we increment its value by 1
                int val = findNIncrement(ht, comp_a_comb);
                if (val == -1)
                {
                    // If not, just add it to the hashtable
                    ht.put(comp_a_comb, 1);
                }
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
    public static ArrayList<StLCouple> sortHT(Hashtable<String[], Integer> ht)
    {
        // If ht is null, return null
        if (ht == null)
        {
            return null;
        }

        ArrayList<StLCouple> couples = new ArrayList<>();

        // Add the elements to the list
        for (String[] key : ht.keySet())
        {
            couples.add(new StLCouple(key, ht.get(key)));
        }

        // Sort the list using the func in Couple
        couples.sort(StLCouple::compareTo);

        // Reverse the list to get the biggest values at the top
        couples.sort(Comparator.reverseOrder());

        return couples;
    }




    public static void test() {
        Map<String, Hashtable<String[], Integer>> allNGrammes = new HashMap<>();

        for (int i = 1; i < 4; i++) {
            System.out.println("================================================");
            Hashtable<String[], Integer> ht = AnalyseFile.getHTofText(ReadFile.text, i);
            DisplayHT.printAllStLN(ht, "Bee Movie Script", i);

            allNGrammes.put(i + "-grammes", ht);
        }

        JSONWriter.saveAllNGrammesAsJSON(allNGrammes, "ngrammes.json");
    }
}

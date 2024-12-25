package model;

public class Couple implements Comparable<Couple>
{
    private final String[] key;
    private final int value;
    Couple (String[] key, int value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Couple c)
    {
        if (value > c.value)
        {
            return 1;
        }
        else if (value < c.value)
        {
            return -1;
        }
        return 0;
    }


    @Override
    public java.lang.String toString()
    {
        String res = "[ ";

        for (int i = 0; i < key.length; i++)
        {
            if (i == key.length - 1)
            {
                res = res + key[i];
            }
            else
            {
                res = res + key[i] + " ";
            }
        }

        res = res + " ] -> " + value;
        return res;
    }
}

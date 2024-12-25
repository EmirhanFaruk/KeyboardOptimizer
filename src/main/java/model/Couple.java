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
        return "_" + key + "_ -> " + value;
    }
}

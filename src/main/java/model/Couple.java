package model;

public class Couple<String, Integer> implements Comparable
{
    private final String key;
    private final int value;
    Couple (String key, int value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof Couple)
        {
            Couple c = (Couple) o;
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
        else
        {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public java.lang.String toString()
    {
        return "_" + key + "_ -> " + value;
    }
}

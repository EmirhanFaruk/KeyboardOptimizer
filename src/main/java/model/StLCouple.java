package model;

public class StLCouple implements Comparable
{
    private final String[] key;
    private final int value;
    StLCouple (String[] key, int value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof StLCouple)
        {
            StLCouple c = (StLCouple) o;
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

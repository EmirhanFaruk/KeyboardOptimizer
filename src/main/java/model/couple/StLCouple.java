package model.couple;

public record StLCouple(String[] key, int value) implements Comparable
{
    @Override
    public int compareTo(Object o)
    {
        if (o instanceof StLCouple c)
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

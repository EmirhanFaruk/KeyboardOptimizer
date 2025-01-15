package model.couple;

public record Couple<K>(K key, int value) implements Comparable<Couple<K>>
{
    public int compareTo(Couple<K> c)
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
    public String toString()
    {
        if (key instanceof String[])
        {
            String res = "[ ";

            for (int i = 0; i < ((String[]) key).length; i++)
            {
                if (i == ((String[]) key).length - 1)
                {
                    res = res + ((String[]) key)[i];
                }
                else
                {
                    res = res + ((String[]) key)[i] + " ";
                }
            }

            res = res + " ] -> " + value;
            return res;

        }
        else
        {
            return "_" + key + "_ -> " + value;
        }
    }
}
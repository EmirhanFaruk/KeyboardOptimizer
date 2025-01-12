package model.couple;

public record StCouple(String key, int value) implements Comparable
{

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof StCouple)
        {
            StCouple c = (StCouple) o;
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

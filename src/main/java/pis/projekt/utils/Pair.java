package pis.projekt.utils;

public class Pair
{
    private int first;
    private int second;

    public Pair(int value1, int value2)
    {
        this.first = value1;
        this.second = value2;
    }

    public int first() {
        return first;
    }

    public void setFirst(int value) {
        this.first = value;
    }

    public int second() {
        return second;
    }

    public void setSecond(int value) {
        this.second = value;
    }
}

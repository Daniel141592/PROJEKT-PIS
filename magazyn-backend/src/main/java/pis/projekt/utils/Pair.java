package pis.projekt.utils;

public class Pair
{
    public int first;
    public int second;

    public Pair()
    {
        this.first = 0;
        this.second = 0;
    }

    public Pair(int value1, int value2)
    {
        this.first = value1;
        this.second = value2;
    }

    public void setFirst(int value) {
        this.first = value;
    }

    public static boolean isSame(Pair pair1, Pair pair2){
        return pair1.first == pair2.first && pair1.second == pair2.second ? true : false;
    }


    public void setSecond(int value) {
        this.second = value;
    }
}

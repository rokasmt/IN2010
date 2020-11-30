class Oppgave3
{
    public static void main (String[] args)
    {
        int [] a ={-2, -4, 3, -1, 5, 6, -7, -2, 4, -3, 2};
        System.out.println("summen av dellista er " + listeMedstorsteSum(a));
    }

    static int listeMedstorsteSum (int a[])
    {
        int maks = 0;
        int maksEnda = Integer.MIN_VALUE;
        int size = a.length;

        for (int i = 0; i < size; i++)
        {
            maks = maks + a[i];
            if (maksEnda < maks)
                maksEnda = maks;
            if (maks < 0)
                maks = 0;
        }
        return maksEnda;
    }
}

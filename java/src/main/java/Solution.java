/*
 * $Id$
 */

public class Solution
{

    public static void main(String[] args)
    {
        int[] a = { 4, 13, 10, 21, 20 }; // 1
        System.out.println(moves(a));

        int[] b = { 5, 8, 5, 11, 4, 6 }; // 2
        System.out.println(moves(b));

    }

    static int moves(int[] a)
    {
        int noOfMoves = 0;

        int firstOddFromLeftIdx = 0;
        int firstEvenFromRightIdx = a.length - 1;

        while (firstOddFromLeftIdx < firstEvenFromRightIdx)
        {
            while (!eImpar(a[firstOddFromLeftIdx]) && (firstOddFromLeftIdx < firstEvenFromRightIdx))
            {
                firstOddFromLeftIdx++;
            }
            

            while (eImpar(a[firstEvenFromRightIdx]) && (firstOddFromLeftIdx < firstEvenFromRightIdx))
            {
                firstEvenFromRightIdx--;
            }

            if (firstOddFromLeftIdx < firstEvenFromRightIdx)
            {
                noOfMoves++;
                firstOddFromLeftIdx++;
                firstEvenFromRightIdx--;
            }
        }
        return noOfMoves;
    }

    static boolean eImpar(int nr)
    {
        if (nr % 2 == 1)
        {
            return true;
        }
        return false;
    }

}

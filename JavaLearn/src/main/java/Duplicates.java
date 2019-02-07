/*
 * $Id$
 */

public class Duplicates
{

    public static void main(String[] args)
    {
        int[] numbers = { 1, 1, 2, 3, 3, 3, 3 };
        System.out.println(countDuplicates(numbers));
    }

    static int countDuplicates(int[] numbers)
    {
        if (numbers == null) return 0;

        int noOfDuplicates = 0;
        int foundDuplicatesIdx = 0;
        int[] foundDuplicates = new int[numbers.length / 2];
        int currIndx = 0;
        while (currIndx < numbers.length)
        {
            int currentNo = numbers[currIndx];

            if (currentNo < 1 || currentNo > 1000)
            {
                continue;
            }

            for (int i = currIndx + 1; i < numbers.length; i++)
            {
                if (currentNo == numbers[i])
                {
                    boolean alreadyFoundAsDuplicate = false;
                    for (int foundDuplicate : foundDuplicates)
                    {
                        if (foundDuplicate == currentNo)
                        {
                            alreadyFoundAsDuplicate = true;
                            break;
                        }
                    }

                    if (!alreadyFoundAsDuplicate)
                    {
                        noOfDuplicates++;
                        foundDuplicates[foundDuplicatesIdx] = currentNo;
                        foundDuplicatesIdx++;
                    }
                    else
                    {
                        currIndx++;
                        break;
                    }

                }
            }
            currIndx++;
        }
        return noOfDuplicates;
    }
}

/*
 * $Id$
 */
package jni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JniTest
{
    static BufferedReader sReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
    {
       do{
            System.out.println("Welcome to calculator");
            System.out.println("provide first operand: ");
            int firstOp = 0, secondOp = 0;
            String operator = null;
            try
            {
                String strFirstOp = sReader.readLine();
                firstOp = Integer.valueOf(strFirstOp);

                System.out.println("provide second operand: ");

                String strSecondOp = sReader.readLine();
                secondOp = Integer.valueOf(strSecondOp);

                System.out.println("provide operator: ");
                operator = sReader.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }

            Calculator calc = new Calculator(firstOp, secondOp, operator);
            System.out.println("Result is : " + calc.getResult());
        }
        while(true);
    }
}

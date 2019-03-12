/*
 * $Id$
 */
package jni;

public class Calculator
{
    static
    {
        System.loadLibrary("calc");
    }

    private Integer mFirstOperand;

    private Integer mSecondOperand;

    private String  mOperator;

    private native int calculate(int firstOp, int secondOp, String operator);

    public Calculator(int firstOp, int secondOp, String operator)
    {
        mFirstOperand = firstOp;
        mSecondOperand = secondOp;
        mOperator = operator;
    }

    public Integer getResult()
    {
        return calculate(mFirstOperand, mSecondOperand, mOperator);
    }
}

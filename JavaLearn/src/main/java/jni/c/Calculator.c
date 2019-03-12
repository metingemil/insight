#include "stdio.h"
#include "jni_Calculator.h"

int performOperation(int, int,char);
int performAddition(int,int);
int performSubstraction(int,int);
int performMultiplication(int,int);

JNIEXPORT jint JNICALL Java_jni_Calculator_calculate
  (JNIEnv *env, jobject obj, jint operator1, jint operator2, jstring operation)
{
    printf("entering C code - Java_jni_Calculator_calculate\n");
    char* opr = (*env)->GetStringUTFChars(env,operation,0);
    printf("operation = %c\n", *opr);

    return performOperation(operator1, operator2, *opr);
}

int performOperation(int op1, int op2, char op)
{
    printf("performOperation method : op1 = %d, op2 = %d, operation = %c\n", op1, op2, op);
    int returnVal = 0;
    switch (op) {
        case '+': 
            returnVal = performAddition(op1, op2);
            break;
        case '-':
            returnVal = performSubstraction(op1, op2);
            break;
        case '*':
            returnVal = performMultiplication(op1, op2);
            break;
        default: 
            printf("unimplemented method!\n");
            break;
    }
    return returnVal;
}

int performAddition(int op1, int op2)
{
    printf("performAddition method : op1 = %d, op2 = %d\n", op1, op2);
    return op1 + op2;
}

int performSubstraction(int op1, int op2)
{
    printf("performSubstraction method : op1 = %d, op2 = %d\n", op1, op2);
    return op1 - op2;
}

int performMultiplication(int op1, int op2)
{
    printf("performMultiplication method : op1 = %d, op2 = %d\n", op1, op2);
    return op1 * op2;
}

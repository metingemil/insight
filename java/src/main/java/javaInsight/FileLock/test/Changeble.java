/*
 * Copyright Keysight Technologies 2018
 */
package javaInsight.FileLock.test;

/***************************************************************************
 * Provides a method that changes an input type I into an output type O.
 * 
 * @param <I> input type
 * @param <O> output type
 */
public interface Changeble<I, O>
{
    /***************************************************************************
     * Changes an input type I into an output type O.
     * 
     * @param in : input type
     * 
     * @return output type O
     */
    public O changeTo(I in);
}

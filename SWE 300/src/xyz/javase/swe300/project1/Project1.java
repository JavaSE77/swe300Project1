package xyz.javase.swe300.project1;

import java.io.IOException;
import java.util.Scanner;

/**
 * Programming Challenge:  LEQEX
 * @author merlin
 * 
 * Contributers: 
 * Makaylie Lucas
 * Russell Stevens
 */
public class Project1
{

	/*
	 * Instance variables are used in more then one method.
	 * All instance variables only used in one method have been removed and
	 * reduced to local variables
	 * */
    private int[] scannerInputData;
    
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        new Project1().run();
    }

    /**
     * 
     * */
    void run()
    {
    	System.out.println("Starting");
        Scanner scanner = new Scanner(System.in);
        //this input tells the program how many triangles we are going to calculate
        int numOfSubArrays = scanner.nextInt();
        for (int i = 0; i < numOfSubArrays; i++)
        {

            scannerInputData = readInProblem(scanner, scanner.nextInt());
            System.out.println(max(scannerInputData));
        }
    }
    

    /**
     * @param int currentLevel - current level to check
     * @param int currentElements - current elements to check
     * 
     * This is the main driver of the program. This takes in the numbers and trys to
     * stack them. 
     * https://www.codechef.com/problems/LEQEX
     * 
     * @return returns true if the element can be paired
     * */
    boolean works(int currentLevel, int currentNumElements)
    {
        boolean[] isPaired = new boolean[currentNumElements];
        int currentElement = 0;
        int currentPair = 0;
        while (currentPair < maxPossible(currentNumElements)) {

        try
        {
            isPaired[currentElement] = true;
            int nextElement = next(isPaired, currentElement);
            	while (scannerInputData[currentLevel + currentElement] != scannerInputData[currentLevel + nextElement])
            	{
            		nextElement = next(isPaired, nextElement);
            	}
            	
            currentPair++;
            isPaired[nextElement] = true;
            currentElement = next(isPaired, currentElement);
        } catch (NoElementToPair exception)
        {
        	/*
        	 * This exception is only thrown when the element of the subarray that is being paired
        	 * has no possible pair. For example, with the input 1, 5, 1, the input 5 has no element to pair
        	 * with and this exception is thrown.
        	 * */
            return false;
        }
    }
        return true;
    }

    /**
     * @param Scanner scanner - (System.in)
     * @param int numElements - number of elements in subarray
     * 
     * Takes the number of elements and reads in the numbers from the input
     * scanner according to the number of elements expected and converts
     * them into an array of ints
     * 
     * @return int[] subArray - list of values read in from the scanner. 
     * Size is equal to numElements
     * */
    int[] readInProblem(Scanner scanner, int numElements)
    {
        int[] subArray = new int[numElements];
        
        for (int i = 0; i < numElements; i++) 
        {
        	subArray[i] = scanner.nextInt();
        }
        	
        return subArray;
    }

    /**
     * @param int[] subArray - elements of the sub array received from readInProblem
     * 
     * @return int maxLevel - returns the max level of the stack
     * */
    int max(int[] subArray)
    {
        int maxLevel = 0;
        int currentLevel = 0;
        while (maxPossible(subArray.length - currentLevel) > maxLevel)
        {
            int currentMax = currentLevelMax(subArray, currentLevel);
            if (currentMax > maxLevel) {
            	maxLevel = currentMax;
            }
            currentLevel++;
           
        }
        return maxLevel;
    }

    /**
     * @param int[] subArray - the data in the subArray currently being used
     * @param int currentLevel - the current level the program is working on
     * 
     * @return int maxLevel - returns the new max level after calculation
     * */
    int currentLevelMax(int[] subArray, int currentLevel)
    {
        int maxLevel = 0;
        for (int currentPairs = 1; currentPairs <= maxPossible(subArray.length - currentLevel); currentPairs++) {

        	if (works(currentLevel, currentPairs*2+1)) {
        		maxLevel = currentPairs;
        	}
        }
        
        return maxLevel;
    }

    /**
     * @param int numElements - Number of elements in the array
     * 
     * Takes the number of elements in the array, subtracts one, and devides by 2 to give
     * the max number of pairs you could have with the given number of elements
     * 
     * @return int pairs - max number of pairs
     * */
    int maxPossible(int numElements)
    {
        int pairs = (numElements - 1) / 2;
        return pairs;
    }


    /**
     * @param boolean[] isPaired - will be true for every value in the index that has a paired value
     * @param int nextElement - element to be checked for pair
     * 
     * @return int nextElement - returns the next element to be paired.
     * */
    int next(boolean[] isPaired, int nextElement) throws NoElementToPair
    {
        nextElement = nextElement+1;
        while ((nextElement < isPaired.length) && isPaired[nextElement]) {
            nextElement++;
        }
        
        if (nextElement == isPaired.length) {
        	throw new NoElementToPair();
        }
        
        return nextElement;
    }

    private class NoElementToPair extends Exception
    {
    	/*
    	 * This exception is only thrown when the element of the subarray that is being paired
    	 * has no possible pair. For example, with the input 1, 5, 1, the input 5 has no element to pair
    	 * with and this exception is thrown.
    	 * */

		/**
		 * generic ID.
		 */
		private static final long serialVersionUID = -5100966632854247986L;
    }
}

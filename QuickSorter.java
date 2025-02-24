package edu.iastate.cs2280.hw2;

import java.io.FileNotFoundException;


import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

import edu.iastate.cs2280.hw2.Point;


/**
 *  
 * @Bavly_Fayed
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		super.algorithm = "quicksort";

	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		quickSortRec(0, this.points.length - 1);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if(first >= last) {
			return;
		}
		int pivot = partition(first,last);
		quickSortRec(first, pivot - 1);
		quickSortRec(pivot + 1, last);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		int pivot = last;
		int i = first - 1;

		for(int j = first; j < last; j++) {
			if(points[j].compareTo(points[pivot]) <= 0)
			{
				i++;
				swap(i,j);
			}
		}
		swap(i+1,last);
		return i + 1;
	}	
			
	// Other private methods if needed ...
}

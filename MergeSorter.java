package edu.iastate.cs2280.hw2;

import java.io.FileNotFoundException;


import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

import edu.iastate.cs2280.hw2.*;

/**
 *  
 * @Bavly_Fayed

 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		super.algorithm = "mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int len = pts.length;
		if(len <= 1) {
			return;
		}
		int mid = len/2;
		Point[] left = new Point[mid];
		Point[] right = new Point[len-mid];
		int j = 0;
		for (int i=0; i < len; i++){
			if(i < mid) {
				left[i] = pts[i];
			}else{
				right[j] = pts[i];
				j++;
			}
		}
		mergeSortRec(left);
		mergeSortRec(right);
		merge(left, right, pts);
	}

	
	// Other private methods if needed ...
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also
	 * set the instance variables algorithm in the superclass.
	 *
	 * @param left left sub-array
	 * @param right right sub-array
	 * @param array the array that must be merged into
	 */
	private void merge(Point[] left, Point[] right, Point[] array){
		int l=0;
		int r=0;
		int i=0;

		while(l < left.length && r < right.length){
			if(left[l].compareTo(right[r]) < 0) {
				array[i] = left[l];
				i++;
				l++;
			}
			else{
				array[i] = right[r];
				i++;
				r++;
			}
		}
		while(l < left.length){
			array[i] = left[l];
			i++;
			l++;
		}
		while(r < right.length){
			array[i] = right[r];
			i++;
			r++;
		}
	}
}
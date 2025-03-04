package edu.iastate.cs2280.hw2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * 
 * @Bavly_Fayed 
 *
 */







/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
        if (pts == null || pts.length == 0) {
            throw new IllegalArgumentException();
        }
		sortingAlgorithm = algo;
        points = new Point[pts.length];
		for(int i=0; i<points.length; i++){
			points[i] = pts[i];
		}
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
        sortingAlgorithm = algo;
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
		try {
			Scanner scnr = new Scanner(new FileInputStream(inputFileName));
			while (scnr.hasNextInt()) {
				x.add(scnr.nextInt());
				if (scnr.hasNextInt()) {
					y.add(scnr.nextInt());
				} else {
					throw new InputMismatchException();
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		}
		points = new Point[x.size()];
		for(int i=0; i< points.length; i++){
			points[i] = new Point(x.get(i), y.get(i));
		}
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		AbstractSorter aSorter; 
		
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		
		if (sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		}else if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}else if (sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}else {
			aSorter = new QuickSorter(points);
		}

		int corX = 0;
		int corY = 0;
		
		long startTime = System.nanoTime();
		for (int i = 0; i < 2; i++) {
			aSorter.setComparator(i);
			aSorter.sort();
			if (i == 0) {
				corX = aSorter.getMedian().getX();
			}
			if (i == 1) {
				corY = aSorter.getMedian().getY();
				medianCoordinatePoint = new Point(corX, corY);
			}
		}

		long endTime = System.nanoTime();
		scanTime = endTime - startTime;
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String stat = String.format("%-17s %-10d %-10d", this.sortingAlgorithm, this.points.length, this.scanTime);
		return stat;
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{ 
		String str = "";
		for (int i = 0; i < points.length; i++) {
			str = str + points[i].toString() + "\n";
		}
		return str;
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		try {
			String outputFileName="";

			if (sortingAlgorithm.equals(Algorithm.SelectionSort)) {
				outputFileName = "SelectionSort.txt";
			}else if (sortingAlgorithm.equals(Algorithm.InsertionSort)) {
				outputFileName = "InsertionSort.txt";	
			}else if (sortingAlgorithm.equals(Algorithm.MergeSort)) {
				outputFileName = "MergeSort.txt";
			}else {
				outputFileName = "QuickSort.txt";
			}

			System.out.println(outputFileName);
			PrintWriter p = new PrintWriter(outputFileName);
			p.println(toString());
		}
		catch (FileNotFoundException e)
		{
			throw new FileNotFoundException();
		}
    }		
}

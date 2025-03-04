package edu.iastate.cs2280.hw2;

/**
 *  
 * @Bavly_Fayed

 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;


import java.util.Scanner;


import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		PointScanner[] scanners = new PointScanner[4]; 
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
		int answer = 1;
		int trials = 1;
		Scanner input = new Scanner(System.in);
		Random random = new Random();

		System.out.println("Performances of Four Sorting Algorithms in Point Scanning" +"\n");
		System.out.println("keys: 1 (random values) | 2 (file input) | 3 (exit)");

		while(true) {
			System.out.print("Trial " + trials + ": ");
			answer = input.nextInt();
			
			if (answer != 1 && answer != 2) {
				break;
			}
			else {
				if (answer == 1){
					System.out.print("Enter number of random values: ");
					int numRandPoints = input.nextInt();
					Point[] points = generateRandomPoints(numRandPoints, random);
					scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
					scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
					scanners[2] = new PointScanner(points, Algorithm.MergeSort);
					scanners[3] = new PointScanner(points, Algorithm.QuickSort);
				}
				else if(answer ==2) {
					System.out.println("Points from a file");
					System.out.print("File name: ");
					String file = input.next();
					scanners[0] = new PointScanner(file, Algorithm.SelectionSort);
					scanners[1] = new PointScanner(file, Algorithm.InsertionSort);
					scanners[2] = new PointScanner(file, Algorithm.MergeSort);
					scanners[3] = new PointScanner(file, Algorithm.QuickSort);
				}
			}
			for (int i = 0; i < scanners.length; i++) {
				scanners[i].scan();
			}
			System.out.println("");
			System.out.printf("%-17s %-10s %-10s \n", "algorithm", "size", "time (ns)");
			System.out.println("--------------------------------------");

			for (int i = 0; i < scanners.length; i++) {
				System.out.println(scanners[i].stats());
			}
			System.out.println("--------------------------------------");
			System.out.println();
			trials++;
		}
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException{
		if (numPts < 1)
			throw new IllegalArgumentException("Number of points is less than 1.");
		else {
			Point[] points = new Point[numPts];
			for(int i = 0; i < numPts; i++) {
				int x = rand.nextInt(101) - 50;
				int y = rand.nextInt(101) - 50;
				Point p = new Point(x, y);
				points[i] = p;
			}
			return points;
		}
	}
}

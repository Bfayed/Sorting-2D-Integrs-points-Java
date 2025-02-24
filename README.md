In this project we are reading an input set of 2D integer points in a coordinate plane.
The goal is to find the median coordinate point, whose 𝑥-coordinate is equal to the median of
the 𝑥-coordinates of the input points and its 𝑦-coordinate is equal to the median of their 𝑦-
coordinates. Finding the median 𝑥- and 𝑦-coordinates is done by sorting the points separately
by the corresponding coordinate.
we need to read the input points four times, each time using one of the following four sorting
algorithms: selection sort, insertion sort, merge sort, and quicksort. Note that the same sorting
algorithm will be used in both rounds of sorting after the points have been read.
We make the following two assumptions:
a) All input points have integer coordinates ranging between −50
b)The input points may have duplicates.
The rectangular range [−50, 50] × [−50, 50] is big enough to contain 10,201 points with integer
coordinates. The input points will be either generated randomly or read from an input file.
Duplicates may be present. It is unnecessary to test your code on more than 10,201 points.

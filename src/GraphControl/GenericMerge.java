package GraphControl;

/**
 * This class defines the merge sort algorithm, should not be instantiated
 * 
 * @author -Modified By: Riley McGee
 * @author -Template by the 2XB3 staff.
 * @author -Code Structure by: Robert Sedgewick and Kevin Wayne
 */
public class GenericMerge {
	private static Comparable[] aux;

	/**
	 * merge sort using Comparable
	 * 
	 * @param x
	 *            - the input array containing times of jobs that need to be
	 *            sorted.
	 * @param n
	 *            - the size of the input array
	 */
	public static void sortMerge(Comparable<?>[] x, int n) {
		aux = new Comparable[n];
		sortMerge(x, 0, n - 1);
	}

	/**
	 * Recursive merge sort algorithm
	 * 
	 * @param x
	 *            - Array being sorted
	 * @param low
	 *            - Sub array index for lowest element
	 * @param high
	 *            -Sub array index for highest element
	 */

	private static void sortMerge(Comparable<?>[] x, int low, int high) {
		if (high <= low)
			return;
		int middle = low + (high - low) / 2;
		sortMerge(x, low, middle);
		sortMerge(x, middle + 1, high);
		mergeArrays(x, low, middle, high);
	}

	/**
	 * Merge two sub arrays algorithm, x from low to high will be sorted
	 * 
	 * @param x
	 *            - Array that contains sub arrays
	 * @param low
	 *            - Sub array one index for lowest element
	 * @param middle
	 *            - Sub array 2 lowest index - 1, sub array 1 highest index
	 * @param high
	 *            -Sub array two index for highest element
	 */
	private static void mergeArrays(Comparable<?>[] x, int low, int middle, int high) {
		int i = low, j = middle + 1;

		for (int k = low; k <= high; k++)
			aux[k] = x[k];

		for (int k = low; k <= high; k++) {
			if (i > middle)
				x[k] = aux[j++];
			else if (j > high)
				x[k] = aux[i++];
			else if (aux[j].compareTo(aux[i]) <= 0)
				x[k] = aux[j++];
			else
				x[k] = aux[i++];
		}
	}
}

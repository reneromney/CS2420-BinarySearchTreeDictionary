package assign9;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * Class for timing BST
 * @author Hanna Larsen & Romney Doria
 *uID: 		u0741837		u0592859
 *CADE:		hannal			doria			
 * Date Modified: 11/04/15
 */
public class BSTTiming {
	// Main for testing runtimes
	public static void main(String[] args) {

		// try computing T(N)/F(N), see if it converges
		DecimalFormat formatter = new DecimalFormat("0000E0");

		System.out
				.println("\nN\tT(N)\t|\tT(N)/log(N)\tT(N)/(NlogN)\tT(N)/N\t\tT(N)/(N^2)\tT(N)/N^3\t");
		System.out
				.println("-----------------------------------------------------------------------------------");

		// Loops 1000 times and use the average running time.
		int timesToLoop = 1000;

		// For each problem size n . . .
		for (int n = 1000; n <= 50000; n += 1000) {
			System.out.print(n + "\t");
			
			/**
			 * BST used for both Sorted and Random Order List BST
			 */
			//BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
			
			/**
			 * Java Treeset compared against BST
			 */
			
			TreeSet<Integer> ts = new TreeSet<Integer>();
			
			/**
			 * Setup for Sorted List into BST
			 */
			
			ArrayList<Integer> testList = new ArrayList<Integer>();
			for (int i = 0; i < n; i++) {
				testList.add(i);
			}
			
			/**
			 * Setup for Random Order into BST
			 */
			Collections.shuffle(testList);
			
			//bst.addAll(testList);
			ts.addAll(testList);
			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				for(int j = 0; j < n; j++)
					//bst.contains(j);
					ts.contains(j);
			}

			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (long i = 0; i < timesToLoop; i++) {
				for(int j = 0; j < n; j++){
				}
				
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			// divides by n b/c method is being called for each n, only want to 
			// get average time of one instance of n
			double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime))/n)
					/ timesToLoop;

			System.out.println(formatter.format(averageTime)	+ "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / (n*(Math.log10(n) / Math.log10(2)))) + "\t\t"
                    + formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));
		
		}

	}
	}

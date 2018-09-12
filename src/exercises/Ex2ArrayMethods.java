package exercises;

import java.util.Arrays;
import java.util.Random;

import static java.lang.StrictMath.round;
import static java.lang.System.*;

/*
 *  Methods with array params and/or return value. Implement methods.
 *
 *  See:
 *  - MathMethods
 *  - ArrayMethods
 */
public class Ex2ArrayMethods {

	public static void main(String[] args) {
		new Ex2ArrayMethods().program();
	}

	final static Random rand = new Random();

	void program() {
		int[] arr = {1, 2, 2, 5, 3, 2, 4, 2, 7};  // Hard coded test data

		// TODO uncomment one at a time and implement

		//Count occurrences of some element in arr
		out.println(count(arr, 2) == 4);      // There are four 2's
		out.println(count(arr, 7) == 1);

		// Generate array with 100 elements with 25% distribution of -1's and 1's (remaining will be 0)
		arr = generateDistribution(100, 0.25, 0.25);
		out.println(count(arr, 1) == 25);
		out.println(count(arr, -1) == 25);
		out.println(count(arr, 0) == 50);

		// Generate array with 14 elements with 40% 1's and 30% -1's
		arr = generateDistribution(14, 0.4, 0.3);
		out.println(count(arr, 1) == 6);
		out.println(count(arr, -1) == 4);

		for (int i = 0; i < 10; i++) {
			// Random reordering of arr, have to check by inspecting output
			shuffle(arr);
			//out.println(Arrays.toString(arr));  // Does it look random?
		}
	}


	// ---- Write methods below this ------------


	private void shuffle(int[] arr){
//
//		int one = count(arr, 1);
//		int negativeOne = count(arr, -1);
//		int zero = count(arr, 0);
//
//		int counter = 0;
//		while(counter >= arr.length) {
//
//			int choice = rand.nextInt(3);
//
//			switch (choice) {
//			case 0:
//				if(zero == 0) {
//					break;
//				}else {
//					arr[counter] = 0;
//					counter++;
//					zero--;
//				}
//				break;
//
//
//			case 1:
//				if(one == 0) {
//					break;
//				}else {
//					arr[counter] = 1;
//					counter++;
//					one--;
//				}
//				break;
//
//
//			case 2:
//				if(negativeOne == 0) {
//					break;
//				}else {
//					arr[counter] = -1;
//					counter++;
//					negativeOne--;
//				}
//				break;
//
//			default:
//				break;
//			}
//
//		}
//		out.println(Arrays.toString(arr));
	}



	private int[] generateDistribution(int arrLength, double dist1, double dist2){
		int[] arr = new int[arrLength];
		double test  = Math.round(arrLength*dist1);
		double test2 = Math.round(test + (arrLength*dist2));
		for (int i = 0; i < arrLength; i++) {
			if(i < test){
				arr[i] = 1;
			}else if(i < test2) {
				arr[i] = -1;
			}else {
				arr[i] = 0;
			}
		}
		return arr;
	}



	private int count(int[] arr, int i) {
		int amount = 0;
		for (int j = 0; j < arr.length; j++) {
			if(arr[j] == i) {
				amount++;
			}
		}
		return amount;
	}

}
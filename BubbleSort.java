import java.util.Arrays;
  
public class BubbleSort {
	//swap those two numbers here
	public static void swap(int[] arr, int from, int to){
		int temp = arr[from];
		arr[from] = arr[to];
		arr[to] = temp;
		//system.out.printf("Iteration %d: %s %n",swapNumber, Arrays.toString(arr)); //bring to front mobile
	}
  
	public static int[] bubbleSort(int[] numbers){ 
		//Print the unsorted array
		//System.out.printf("Unsorted array: %s%n", Arrays.toString(numbers)); 
		int sIndex = 0; 
		int nValues = numbers.length;
		int eIndex = nValues - 1;

		while (sIndex < eIndex){
			for (int index = eIndex; index > sIndex; index--){
				if (numbers[index] < numbers[index -1])
					swap(numbers, index, index - 1);
				System.out.println(Arrays.toString(numbers));
			}
			sIndex++;
		}
      	return numbers;
		//System.out.println("Solution: %s", Arrays.toString(arr)); //bring to front mobile
	}
	public static void main(String args[]){ // does not return and static means value stays the same
      	//int[] array = {20, 12, 45, 19, 91, 55};
      	//int[] array = {2, 1, 8, 3, 5, 7, 9};
      	int[] array = {5, 1, 8, 9, 4};
      	array = bubbleSort(array);
      	System.out.println(Arrays.toString(array));
      	
	} //[ 12, 19, 20, 45, 55, 91]
}
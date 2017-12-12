//Nisarg Antony - 800989573 - ITCS 6114
package AltSort;
import java.util.*;
import static java.lang.System.out;
public class Alternating_Sort{
	//Helper function to check whether a given value lies in a given array.
	public static boolean in_array(int key , int[] array){
		for(int x : array){
			if(x==key) return true;
		}
		return false;
	}
	//Helper function to check whether a value lies between two other values.
	public static boolean in_between(int first , int mid , int last){
		if((mid > first && mid < last) || (mid < first && mid >last)) return true;
		else return false;
	}
	//Helper function to append a value to the end of an array. Note : This function creates a new array.
	public static int[] concat(int []array , int n){
		int []narr = new int[array.length + 1];
		for(int i = 0 ; i<array.length ; i++){
			narr[i] = array[i];
		}
		narr[array.length] = n;
		return Arrays.copyOf(narr , narr.length);
	}
	//The main function that is responsible for Alternating Sort.
	public static void foo(int index , int[] array , ArrayList<int[]> list){
		//For an array having only one element.
		if(array.length == 1){
			out.println(Arrays.toString(array));
			return;
		}
		if(index<array.length){
		ArrayList<int[]> candidates = new ArrayList<>();
		int first , mid , last , asize;
		//Creating ArrayLists where each array is of size 2 and is alternatingly sorted.
		if(index == 1){
			for(int i = 0 ; i<array.length ; i++){
			for (int j = 0 ;  j<array.length ; j++){
				if(i<j) candidates.add(new int[]{array[i] , array[j]});
			}
			}
		}
		//Creating ArrayLists where each array is of size, greater than 2 and is alternatingly sorted.
		//This function works by appending a single element to an alternatingly sorted array such that the resulting array is also alternatingly sorted.
		//The arrays for all i=1,2,....n are stored in the candidates ArrayList.
		else{
			for(int i = 0 ; i<list.size() ; i++){
				for(int j=0 ; j<array.length ; j++){
					if(!in_array(array[j] , list.get(i))){
						asize = list.get(i).length;
						first = list.get(i)[asize-2];
						mid = list.get(i)[asize-1];
						last = array[j];
						//System.out.println("asize : " + asize + "    :     " + first + ""  + mid  + "" + last);
						if(!in_between(first , mid , last)){
							candidates.add(concat(list.get(i) , last));
							
						}
					}
				}
			}
		}
		//The function recursively calls itself.
		foo(++index , array , candidates);
		//Printing the original array, the alternatingly sorted arrays and the number of alternatingly sorted arrays.
		//Put index == index in the if condition if you want to see double alternatingly sorted arrays of all lengths
		if(index == array.length){
		out.println("Original array : \n");
		out.println(Arrays.toString(array));
		out.println("\nList of all Alternating Sorts of size " + index + " : \n");
		for(int[] xcan : candidates) out.println(Arrays.toString(xcan));
		out.println("\nNumber of Alternating Sorts of size " + index + " : " + candidates.size());
		}
		}
		else return ;
	}
	public static void main(String []args){
		//The original array.
		//Change the values in the array to get corresponding output
		//Note : If using other arrays, dont forget to update the name on the list.add() function as well as the main foo() function.
		//Function to fill the array with values in the range 1...n. Change the value of n for bigger or smaller arrays.
		int n = 4;
		int []array = new int[n];
		for(int i = 0 ; i<n ; i++) array[i] = i+1;
		//int []array = {1 , 1 , 2 , 5 , 16 , 61 , 272 , 1385 , 7936 , 50521};
		ArrayList<int[]> list = new ArrayList<>();
		list.add(Arrays.copyOf(array , array.length));
		foo(1 , array , list);
	}
}
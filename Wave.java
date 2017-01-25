import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/* Challenge: Create a program that takes an array of ints and aranges them such that
 * for each element i, both i+1 and i-1 are either greater than or less than i, 
 * effectively creating a wave effect when the array is graphed.
 * This challenge was part of a series of weekly challenges by ACM@SCU.
 */
public class Wave {
	
	public static void main(String[] args){

		//Declare array, initialize scanner
		ArrayList<Integer> array = new ArrayList<Integer>();
		Scanner IN = new Scanner(System.in);
		
		//Input
		int split = -1;
		while(split != 1 && split != 2){
			try{
				System.out.println("Enter array elements.");
				System.out.println("Enter \"1\" to enter single elements at a time.");
				System.out.println("Enter \"2\" to enter a single whitespace-delimited list.");
				split = IN.nextInt();
			}catch(InputMismatchException e){
				IN.next();
			}
		}
		
		//Single Entry
		if(split == 1){
			System.out.println("Enter array elements. Enter any nonnumeric item to proceed.");
			System.out.printf(">");
			int next = -1;
			while(next != 0){
				String s = IN.next();						//Process integers one at a time
				try{										//Catch non-integers
					next = Integer.parseInt(s);				
				}catch(NumberFormatException e){
					break;
				}
				array.add(next);
				System.out.printf(">");
			}
			
		//List entry
		}else{
			System.out.println("Enter array elements, separated by whitespace.");
			System.out.printf(">");
			IN.nextLine();						//Clear buffer
			String s = IN.nextLine();			//Read string
			String[] nums = s.split("\\s");		//Split by whitespace
			for(String str : nums){
				try{							//Catch non-integers
					array.add(Integer.parseInt(str));
				}catch(NumberFormatException e){
					System.out.println("Error: array element \"" + str + "\" is not an integer!");
				}
			}
		}
		//Sort array
		Collections.sort(array);
		
		//Swap each pain of items
		for(int j = 0; j < array.size()-1; j+=2)
		{
			int temp = array.get(j);
			array.set(j, array.get(j+1));
			array.set(j+1, temp);
		}
		
		//Print
		System.out.println(array);
		IN.close();
	}
}

package isFibonacci;

import java.util.ArrayList;
import java.util.Scanner;

public class isFibonacci {
	public static void main(String[] args) {
		
		//Initialization
		final Scanner IN = new Scanner(System.in);
		ArrayList<Long> sequence = new ArrayList<Long>();
		ArrayList<Long> inputs = new ArrayList<Long>();
		int numbers = IN.nextInt();
		
		//Populate sequence
		long current = 1;
		long max = Long.parseLong("10000000000");			//10^10
		sequence.add(0, (long) 0);
		sequence.add(1, current);

		while(Long.compare(max, current) > 0){
			current = (long)sequence.get(sequence.size() - 1) + sequence.get(sequence.size() - 2);
			sequence.add(current);
		}

		//Take inputs
		for(int i = 0; i < numbers; i++){
			inputs.add(IN.nextLong());
		}

		//Print results
		for(long l : inputs){
			System.out.println((sequence.contains(l)) ? "IsFibo" : "IsNotFibo");
		}

		IN.close();
	}
}

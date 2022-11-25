package com.victoree2.main;

import java.util.Scanner;

import com.victoree2.system.SystemEx;

public class ReadingRoom {
	
	public static String language="kor";
	public static boolean firstMain=false;
	public static void main(String[] args) {

//		String test[][] = { {"[1]" , "[2]"},
//				{"[3]", "[4]"}};
//		
//		Scanner scan = new Scanner(System.in);
//		String a = scan.nextLine();
//		for(int i =0 ; i < test.length ; i++) {
//			for(int j = 0 ; j < test[i].length ; j++) {
//				
//				test[i][j] = test[i][j].replace("[", "");
//				test[i][j] = test[i][j].replace("]", "");
//				if(test[i][j].equals(a))
//					System.out.print(test[i][j]+","+i+","+j);
//			}
//			System.out.println();
//		}
		
		SystemEx system = new SystemEx();
		system.run();
		

	}

}

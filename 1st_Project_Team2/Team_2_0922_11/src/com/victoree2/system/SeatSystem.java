package com.victoree2.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeatSystem {
	
	public String firstBraket = "[";
	private int indexX;
	private int indexY;
	public String lastBraket = "]";	
	
	private List<String[][]> roomNum = new ArrayList<String[][]>(); //다형성 //열람실 번호 roomNum
	
	public List<String[][]> getRoomNum() {
		return roomNum;
	}




	private String deskIndex1[][] = new String[6][9]; //좌석 번호 //세로열 [0~2], 가로열[0~6] 
	private String deskIndex2[][] = new String[4][6]; //좌석 번호 //세로열 [0~3], 가로열[0~5]
	private String deskIndex3[][] = new String[5][5]; //좌석 번호 //세로열 [0~4], 가로열[0~4]
	
	Scanner scan = new Scanner(System.in);
	
	void init() { //초기과 좌석 배열
		indexX = 0;
		
		//1열람실 좌석 배열 arr1
		for (int i = 0; i <deskIndex1.length; i++) {
			for (int j = 0; j <deskIndex1[i].length; j++) {
				deskIndex1[i][j] =firstBraket + "1"  + (String.valueOf(indexX)) + (String.valueOf(indexY++)) + lastBraket;
				//System.out.print(deskIndex1[i][j]);
			}
			indexY = 0;
			String.valueOf(indexX++);
			//System.out.println();
			
			if(indexX == (deskIndex1.length)) {
				indexX = 0;
			}
		}
		
		//2열람실 좌석 배열 arr2
		for (int i = 0; i <deskIndex2.length; i++) {
			for (int j = 0; j <deskIndex2[i].length; j++) {
				deskIndex2[i][j] =firstBraket + "2"  + (String.valueOf(indexX)) + (String.valueOf(indexY++)) + lastBraket;
				//System.out.print(deskIndex2[i][j]);
			}
			indexY = 0;
			String.valueOf(indexX++);
			//System.out.println();
			
			if(indexX == (deskIndex2.length)) {
				indexX = 0;
			}
		}
		
		//3열람실 좌석 배열 arr3
		for (int i = 0; i <deskIndex3.length; i++) {
			for (int j = 0; j <deskIndex3[i].length; j++) {
				deskIndex3[i][j] =firstBraket + "3"  + (String.valueOf(indexX)) + (String.valueOf(indexY++)) + lastBraket;
				//System.out.print(deskIndex3[i][j]);
			}
			indexX = 0;
			String.valueOf(indexX++);
			//System.out.println();
			
			if(indexX == (deskIndex3.length)) {
				indexX = 0;
			}
		}			
		  roomNum.add(deskIndex1);
		  roomNum.add(deskIndex2);
		  roomNum.add(deskIndex3);
	}

	


	public void makeRoom(int x, int y) {
		String temp[][] = new String[y][x];
		int room = 1;
		
		for(int i = 0 ; i < temp.length ; i++) {
			for(int j = 0 ; j < temp[i].length ; j++) {
				temp[i][j] = "[" + room +"]";
				room++;
			}
		}
		roomNum.add(temp);
	}
	
	public void print(int room) {
		String tmp[][] = roomNum.get(room-1);
		
		for(int i = 0 ; i < tmp.length ; i++) {
			for(int j = 0 ; j < tmp[i].length ; j++) {
				System.out.print(tmp[i][j]+"\t"); //자리 이쁘게 표현하기
			}
			System.out.println();
		}
	}
	
	
	public void update(int room) {
		boolean pnp = true; // [사용중] 좌석 선택시 다시 돌도록.
		
		while(pnp == true) {
			String seat = scan.nextLine();
			String tempSeat = "[" + seat + "]";
			String tmp[][] = roomNum.get(room-1);
			
			for(int i = 0 ; i<roomNum.size(); i++) {
				tmp = roomNum.get(i);
			}

			
			for(int i=0; i<tmp.length;i++ ) {
				for(int j=0; j<tmp[i].length;j++) {
					if(tmp[i][j].equals("[사용중]")) {
						pnp = true;
						System.out.println("이미 사용중인 자리입니다. 재입력하세요.");
					}
					if(tmp[i][j].equals(tempSeat)) {
						tmp[i][j] = "["+"사용중"+"]";
						this.indexX = i;
						this.indexY = j;
						pnp = false;
					}
				}
			}
		}
	}

	public int getIndexX() {
		return indexX;
	}

	public int getIndexY() {
		return indexY;
	}
	
	
	
}

package com.victoree2.system;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import com.victoree2.common.AccountData;
import com.victoree2.common.ReservationData;
import com.victoree2.common.ReturnMessage;
import com.victoree2.main.ReadingRoom;

public class ReservationSystem extends ReturnMessage{
	
	Scanner scan = new Scanner(System.in);
	ReadingRoom room = new ReadingRoom();
	AccountData account;
	ReadingRoomFactory factory = new ReadingRoomFactory();// 데이터 수정/삭제시 사용
	CalendarSystem cal = factory.getCalendarSystem();

	
	//파일 직렬화
	FileOutputStream fos = null;
	BufferedOutputStream bos = null;
	ObjectOutputStream out = null;
	
	//파일 역 직렬화
	FileInputStream fis = null;
	BufferedInputStream bis = null;
	ObjectInputStream in = null;
	
	
	String filename = "C:\\KOSA_IT\\login\\ReservationDB.txt";	
	File file = new File(filename);	
	
	private HashMap<String, ReservationData> reservation = new HashMap<String, ReservationData>();
	private ReservationData reservationData = new ReservationData();
	
	
	
	//결제 진행시 생성자를 들어올수 있게 정의한다.
	//여기서는 load부터 안할것?
	ReservationSystem(){
		///reservation
	}
	
//	public void save(AccountData account, 캘린더 객체,boolean 정기구독 구분값,int 각 가격 ,int 행, int 열)
	public void save(AccountData account, boolean payStatus, int price, int indexX, int indexY) { //직렬화
		reservationData.setId(account.getId());
		reservationData.setName(account.getName());
		reservationData.setStartDay(cal.DateString(Calendar.getInstance()));
		reservationData.setPayStatus(payStatus);
		reservationData.setPrice(price);
		reservationData.setIndexX(indexX);
		reservationData.setIndexY(indexY);
		
		
		reservation.put(account.getId(), reservationData);
		
		//캘린더값
		//reservationData.setStartDay(filename);
		//reservationData.setEndDay(filename);
		
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			out = new ObjectOutputStream(bos);
			out.writeObject(reservation);
			System.out.println("save success");
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				out.close();
				bos.close();
				fos.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	public void load() {
		//역직렬화
		try {
			// 파일이 없을경우 생성.
			if (!file.exists())
				file.createNewFile();
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			in = new ObjectInputStream(bis);
			reservation = (HashMap)in.readObject();	
	
		} catch (FileNotFoundException e) {
			//User 클래스 생성자때 파일 자체를 만들지만 폴더 접근 권한이나 다른 이상현상으로 파일 생성이 안될경우 메세지 출력
			System.out.println(message(room.language, "0014"));
		} catch (EOFException e) {
			System.out.println(message(room.language, "0015") + e.getMessage());
		} catch (IOException e) {
			System.out.println(message(room.language, "0016")+ e.getMessage());	
		}  catch (Exception e) {
			System.out.println(message(room.language, "0017") + e.getMessage());
		}finally {
			try {
				in.close();
				bis.close();
				fis.close();
			}catch (Exception e) {
			}
		}
	}
	
	
	public HashMap<String, ReservationData> getReservation() {
		return reservation;
	}
	
	
	//UserSystem에서 예약 가능하기때문에
	//해당의 클래스는 예약내역으로 바꿀것.
	
	//업데이트
	public void update(AccountData account){
		load();// 수정.
		this.reservation.remove(account.getId());
		//reservation.put(account.getId(), account);
		//save();
	}
	
}

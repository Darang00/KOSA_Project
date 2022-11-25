package com.victoree2.system;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import com.victoree2.common.AccountData;
import com.victoree2.common.ActionInterface;
import com.victoree2.common.ReservationData;
import com.victoree2.common.ReturnMessage;
import com.victoree2.main.ReadingRoom;

public class UserSystem extends ReturnMessage implements ActionInterface{
	// 변수
	private int paymentpoint = -1;  //아래 pay 배열값 포인터
	private boolean payStatus; // false = 정기권   true = 시간권
	private String paymentSeason[] = {"65,000", "99,000","190,000"};		//시즌권
	private String paymentSeasonCoupon[] = {"45,500","69,300","133,000"};	//쿠폰 시즌권
	private String paymentTime[] = {"3,000","5,000","8,000","10,000","12,000"};		//시간권
	private String paymentTimeCoupon[] = {"2,300","3,500","5,600","7,000","8,400"}; //쿠폰 시간권
	private String checkIn;
	private int key;
	private boolean coupon;		// false = 일반	  true = 쿠폰적용

	
	ReadingRoomFactory factory = new ReadingRoomFactory();// 데이터 수정/삭제시 사용
	AccountSystem user = factory.getUser();
	SeatSystem seatSys= factory.getSeatSystem();
	
	ReadingRoom room = new ReadingRoom();
	Scanner scan = new Scanner(System.in);
	AccountData userStatus;
	

	//결제내역
	ReservationSystem  reservationSys = factory.getReservationSystem();
	HashMap<String, ReservationData> reservationMap;// = reservationSys.getReservation();//구독내역
	ReservationData reservationData;
	//
	
	
	
	
	
	
	public UserSystem(AccountData userStatus) {
		this.userStatus = userStatus;
		checkIn = (userStatus.getCheckIn()) ? message(room.language,"0028") : message(room.language,"0027"); //입퇴실값 초기화.
		//test.init();
	}
	@Override
	public void run() {
		reservationSys.load();
		System.out.println(userStatus.getName()+message(room.language, "0019"));
		while ((key = selectMenu("0")) != 0) {
			switch (key) {
			case 1: //마이페이지
				myPage();
				if(room.firstMain == true) {
					room.firstMain = false;
					return;
				}	
				break;
			case 2: //정기권 예약
				payStatus = false;
				seasonReservation();
				break;
			case 3: //시간권 예약
				payStatus = true;
				timeReservation();
				break;
			case 4: //스터디룸 예약 보류....
				break;
			case 5: // 입/퇴실
				checkRoom();
				break;
			case 6: //좌석보기
				checkSeat();
				break;
			case 7: //룸만들기
				System.out.println("7: 룸만들기");
				int x = Integer.parseInt(scan.nextLine()); // 가로
				int y = Integer.parseInt(scan.nextLine()); // 세로
				seatSys.makeRoom(x, y);
				break;
			case -1: //시스템 종료.
				System.out.println(message(room.language, "0018"));
				System.exit(0);
//				return;
			default:
				System.out.println(message(room.language, "0020"));
				break;
			}
		}
	}
	
	public void myPage() {	
		System.out.print(message(room.language, "0200"));
		System.out.print(userStatus.getName());
		System.out.print(message(room.language, "0201"));
		System.out.print(message(room.language, "0202"));
		System.out.println();
		while ((key = selectMenu("01")) != 0) {
			switch (key) {
			case 1:// 패스워드 초기화
				pwdReset();
				if(room.firstMain == true)
					return;
				break;
			case 2:// 휴대폰번호 변경
				phoneNumberReset();
				break;
			case 3://결제내역
				selectReservation();
				break;
			case 4:// 결제취소
				paymentCancel();
				if(room.firstMain == true)
					return;			
				break;
			case 0:
				System.out.println(message(room.language, "0018"));
				System.exit(0);
			}
		}
	}
	public void checkInOut() { //입실/퇴실관리.
//		(userStatus.getCheckIn()) 
		//checkSeat();
	}
	@Override
	public void selectAccount() {
		//내 남은 시간조회
	}

	public void seasonReservation(){//정기권 예약
		while ((key = selectMenu("02")) != 0) {
			if(key == 1) {
				paymentpoint = 0;
			}	
			else if(key == 2) {
				paymentpoint = 1;
			}
			else if(key == 3) {
				paymentpoint = 2;
			}
			else if(key == 4) {
				paymentpoint = 3;
			}
			else if(key == 0) {
				System.out.println(message(room.language, "0020"));
				break;
			}
			payment(); //payment() 이동
			if(room.firstMain == true)
				return;

		}
	}
	public void timeReservation() {//시간권예약
		while ((key = selectMenu("03")) != 0) {
			if(key == 1) {
				paymentpoint = 0;
			}	
			else if(key == 2) {
				paymentpoint = 1;
			}
			else if(key == 3) {
				paymentpoint = 2;
			}
			else if(key == 4) {
				paymentpoint = 3;
			}
			else if(key == 0) {
				System.out.println(message(room.language, "0020"));
				break;
			}
			payment(); //payment() 이동
			if(room.firstMain == true)
				return;
			break;
		}
	}
	
	public void payment() { //결제하다
		while ((key = selectMenu("021")) != 0) {
			switch (key) {
			case 1:
				paymentFinal();
				if(room.firstMain == true)
					return;
				break;
			case 2:
				// 쿠폰 창
				break;
			case 9:
				room.firstMain = true;
				break;
				
			default:
				System.out.println(message(room.language, "0020"));
				break;
			}
		}
	}
	
	//최종결제 후 좌석결제
	public void paymentFinal() {
		while ((key = selectMenu("0211")) != -1212) {
			switch (key) {
			case 1:
				checkRoom();
				room.firstMain = true;
				return;
			default:
				System.out.println(message(room.language, "0020"));
				break;
			}
		}
	}

	public void checkRoom() { // 열람실 몇번방인지 체크
		System.out.println(message(room.language, "0104"));
		
		for (int i = 1; i <= seatSys.getRoomNum().size(); i++) {
			System.out.print(i + ": " + i +" 열람실 \n");
		}
		int room = Integer.parseInt(scan.nextLine());
		
		checkSeat(room);
		
		//날짜값(분까지),boolean 정기구독 구분값,,int 각 가격 ,int 행, int 열
	
	}
	
	public void checkSeat(int room) { //정기권 , 시간권
		seatSys.print(room);
		seatSys.update(room); //배열완성시 팩토리의 Seat 클래스 접속
		//userStatus, payStatus,paymentSeason[paymentpoint],test.indexX,test.indexY //직렬화
		//결제내역 직렬화
		int price = Integer.parseInt(paymentSeason[paymentpoint].replace(",", "")); 
		reservationSys.save(userStatus, payStatus,price,seatSys.getIndexX(),seatSys.getIndexY());
		userStatus.setCheckIn(false);
		System.out.println("완료되었습니다.");
	}
	
	@Override
	public void checkSeat() { // 그냥 자리만 보고싶을때
		System.out.println(message(room.language, "0104"));

		for (int i = 1; i <= seatSys.getRoomNum().size(); i++) {
			System.out.print(i + ": " + i + "열람실 \n");
		}
		int room = Integer.parseInt(scan.nextLine());
		seatSys.print(room);
	}

	
	
	@Override
	public void paymentCancel(){
		CalendarSystem aaaa = new CalendarSystem();
		//결제 취소 
		while (true) {
			String menu;
			System.out.println(message(room.language, "0208"));
			System.out.println(message(room.language, "0209"));
			long a =	aaaa.refundMoneyTime("2021011824","2021011823");
			long a1 =	aaaa.refundMoneySeason("20210118","20210117");

			System.out.println(aaaa.DateStirngYear(Calendar.getInstance()));
			
			System.out.println(a1);
			System.out.println(message(room.language, "0210"));
			
			menu = scan.nextLine();
			if (menu.equals("1")) {
				System.out.println(message(room.language, "0211"));
				room.firstMain = true;		
				//**********************************
				// 회원 정보 삭제 보류
			}
			return;
		}
	}


	
	@Override
	public void pwdReset() {
		//패스워드 초기
		while (true) {
			String pwd;
			String newPwd;
			System.out.println(message(room.language, "0204"));
			pwd = scan.nextLine();// 현재 비밀번호 입력

			if ((!userStatus.getPassword().equals(pwd))) {
				System.out.println(message(room.language, "0205"));

			} else {
				System.out.println(message(room.language, "0206"));
				newPwd = scan.nextLine();
				userStatus.setPassword(newPwd);
				user.update(userStatus);
				System.out.println(message(room.language, "0207"));
				// 하단에서 최상위인 첫 메인화면으로 이동할경우 firstMain 값을 true 변경할것이다.
				room.firstMain = true; 
				return;
			}	
		}
	}
	
	
	
	public void phoneNumberReset() {
		//휴대폰 번호 초기
		while (true) {
			String phoneNumber;
			String newPhoneNumber;
			System.out.println(message(room.language, "0212"));
			newPhoneNumber = scan.nextLine();// 현재 휴대폰 번호 입력

			if ((!userStatus.getPhoneNumber().equals(newPhoneNumber))) {
				System.out.println(message(room.language, "0213"));

			} else {
				System.out.println(message(room.language, "0214"));
				newPhoneNumber = scan.nextLine();
				userStatus.setPhoneNumber(newPhoneNumber);
				user.update(userStatus);
				System.out.println(message(room.language, "0215"));
				// 하단에서 최상위인 첫 메인화면으로 이동할경우 firstMain 값을 true 변경할것이다.
				return;
			}	
		}
	}
	public void selectReservation() {
		//결제내역 조회  !!!정기구독권 사용자로 처음 시스템 시작시 데이터 load할수 있게 해야함 
//		reservationSys.load();
		reservationMap = reservationSys.getReservation();//구독내역
//		Set<String> reservationList = reservationMap.keySet();
		if(reservationMap.get(userStatus.getId()) != null)//키값이 존재할경우.
		System.out.println(reservationMap.get(userStatus.getId()).toString());
//		get(userStatus.getId());
//			System.out.print(value);
//			reservationData = (ReservationData)value;
//			System.out.println(reservationData.toString());
//		}
	}
	@Override
	public int selectMenu(String index) {
		if(index == "0")
			System.out.printf(message(room.language, "0002"),checkIn);
		else if(index == "01")
			System.out.println(message(room.language, "0026"));
		// selectMenu 메소드
		else if(index == "02")
			System.out.printf(message(room.language, "0100"), paymentSeason[0],paymentSeason[1],paymentSeason[2]); //정기권 가격 출력
		else if(index == "03")
			System.out.printf(message(room.language, "0108"), paymentTime[0],paymentTime[1],paymentTime[2],paymentTime[3],paymentTime[4]); //시간권 가격 출력
		else if(index == "06")
		{//특수케이스
			System.out.println(message(room.language, "0111"));//열람실 개수는 3개 고정으로 둘것이지만 추후 증가될것을 생각하여 for문으로 직접 작성.

			for(int i = 0 ; i < seatSys.getRoomNum().size(); i++) {
				System.out.printf((i+1) + " : " + message(room.language, "0029"),(i+1));
			}
			System.out.println();
		}

		else if(index == "021") 
			System.out.printf(message(room.language, "0102"), payStatus == false ? paymentSeason[paymentpoint] : paymentTime[paymentpoint]);  // 무슨 권이냐에 따라 가격 출력
		else if(index == "0211")
			System.out.printf(message(room.language, "0103"), userStatus.getId(), payStatus == false ? "" : "\n 9: 돌아가기 ");  // 아이디 출력 후 시즌권에 따라 뒤로가기 다르게 출력
		return Integer.parseInt(scan.nextLine());
	}
}

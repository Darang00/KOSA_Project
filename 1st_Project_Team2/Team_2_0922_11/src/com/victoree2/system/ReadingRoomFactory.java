package com.victoree2.system;

import java.util.HashMap;
import java.util.Set;

import com.victoree2.common.AccountData;
import com.victoree2.common.ReturnMessage;

// 클래스 집합

public class ReadingRoomFactory extends ReturnMessage{
	
	
	public AccountSystem getUser() {//계정 데이터 생성
		return new AccountSystem();
	}
	public ReservationSystem getReservationSystem() {
		return new ReservationSystem();
	}
	public UserSystem getUserSystem(AccountData userStatus) {//사용자페이지
		return new UserSystem(userStatus);
	}
	public AdminSystem getAdminSystem(HashMap<String, AccountData> userMap) {//관리자페이지
		return new AdminSystem(userMap);
	}
	public SeatSystem getSeatSystem() {//좌석 구현햐함..
		return new SeatSystem();
	}
	public CalendarSystem getCalendarSystem() {//캘린더
		return new CalendarSystem();
	}

}

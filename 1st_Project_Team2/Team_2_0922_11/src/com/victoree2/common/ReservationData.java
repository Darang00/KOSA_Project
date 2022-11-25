package com.victoree2.common;

import java.io.Serializable;

public class ReservationData implements Serializable{//입실퇴실, 남은시간 기록
	private String id; //id
	private String name; //이름
	private String startDay; //결제일
	private String endDay; //종료일
	private String remainderPeriod; //남은기간
	private boolean payStatus; //1: 정기구독권 2: 시간제사용자
	private int indexX; // 정기구독권의 경우 고정 자리 정보값을 받는다.
	private int indexY;
	private int price; //가격
	public String getId() {
		return id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndexX() {
		return indexX;
	}
	public void setIndexX(int indexX) {
		this.indexX = indexX;
	}
	public int getIndexY() {
		return indexY;
	}
	public void setIndexY(int indexY) {
		this.indexY = indexY;
	}
	
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getRemainderPeriod() {
		return remainderPeriod;
	}
	public void setRemainderPeriod(String remainderPeriod) {
		this.remainderPeriod = remainderPeriod;
	}
	@Override
	public String toString() {
		return "ReservationData [id=" + id + ", name=" + name + ", startDay=" + startDay + ", endDay=" + endDay
				+ ", remainderPeriod=" + remainderPeriod + ", payStatus=" + payStatus + ", indexX=" + indexX
				+ ", indexY=" + indexY + ", price=" + price + "]";
	}
	public boolean isPayStatus() {
		return payStatus;
	}
	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}
}

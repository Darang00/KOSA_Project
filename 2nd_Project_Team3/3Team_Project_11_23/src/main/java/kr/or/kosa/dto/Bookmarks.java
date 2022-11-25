package kr.or.kosa.dto;

import java.sql.Date;

public class Bookmarks {
	private int bookidx;
	private int idx;
	private String userid;
	private String title;
	private Date writedate;
	private int lovenum;
	

	public Bookmarks(int bookidx, int idx, String userid, String title, Date writedate, int lovenum) {
		super();
		this.bookidx = bookidx;
		this.idx = idx;
		this.userid = userid;
		this.title = title;
		this.writedate = writedate;
		this.lovenum = lovenum;
	}
	

	public Bookmarks() {
		super();
	}



	public int getbookidx() {
		return bookidx;
	}
	public int getIdx() {
		return idx;
	}
	public String getUserid() {
		return userid;
	}
	public String getTitle() {
		return title;
	}
	public Date getWritedate() {
		return writedate;
	}
	public int getLovenum() {
		return lovenum;
	}
	public void setbookidx(int bookidx) {
		this.bookidx = bookidx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public void setLovenum(int lovenum) {
		this.lovenum = lovenum;
	}
	
	@Override
	public String toString() {
		return "Bookmarks [bookidx=" + bookidx + ", idx=" + idx + ", userid=" + userid + ", title=" + title
				+ ", writedate=" + writedate + ", lovenum=" + lovenum + "]";
	}
	
	
}

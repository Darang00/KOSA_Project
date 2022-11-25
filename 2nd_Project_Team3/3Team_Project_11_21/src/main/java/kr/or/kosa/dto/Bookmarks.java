package kr.or.kosa.dto;

import java.sql.Date;

public class Bookmarks {
	private int bookindex;
	private int idx;
	private String userid;
	private String title;
	private String writer;
	private Date writedate;
	private int refernum;
	
	public Bookmarks(int bookindex, int idx, String userid, String title, String writer, Date writedate,
			int refernum) {
		super();
		this.bookindex = bookindex;
		this.idx = idx;
		this.userid = userid;
		this.title = title;
		this.writer = writer;
		this.writedate = writedate;
		this.refernum = refernum;
	}
	

	public Bookmarks() {
		super();
	}

	public int getBookindex() {
		return bookindex;
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
	public String getWriter() {
		return writer;
	}
	public Date getWritedate() {
		return writedate;
	}
	public int getRefernum() {
		return refernum;
	}
	public void setBookindex(int bookindex) {
		this.bookindex = bookindex;
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
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public void setRefernum(int refernum) {
		this.refernum = refernum;
	}
	@Override
	public String toString() {
		return "Bookmarks [bookindex=" + bookindex + ", idx=" + idx + ", userid=" + userid + ", title=" + title
				+ ", writer=" + writer + ", writedate=" + writedate + ", refernum=" + refernum + "]";
	}

}

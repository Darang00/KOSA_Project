package kr.or.kosa.dto;

import java.util.Date;

public class Board_bak {
	private int idx;
	private String userid;
	private String boardname;
	private String title;
	private String content;
	private String writedate; 
	private int readnum;
	private int lovenum;
	private int boardstatus;
	
	
	
	
	public Board_bak() {
		super();
	}
	public Board_bak(int idx, String userid, String boardname, String title, String content, String writedate, int readnum,
			int lovenum, int boardstatus) {
		super();
		this.idx = idx;
		this.userid = userid;
		this.boardname = boardname;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.readnum = readnum;
		this.lovenum = lovenum;
		this.boardstatus = boardstatus;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public int getLovenum() {
		return lovenum;
	}
	public void setLovenum(int lovenum) {
		this.lovenum = lovenum;
	}
	public int getBoardstatus() {
		return boardstatus;
	}
	public void setBoardstatus(int boardstatus) {
		this.boardstatus = boardstatus;
	}
	@Override
	public String toString() {
		return "Board [idx=" + idx + ", userid=" + userid + ", boardname=" + boardname + ", title=" + title
				+ ", content=" + content + ", writedate=" + writedate + ", readnum=" + readnum + ", lovenum=" + lovenum
				+ ", boardstatus=" + boardstatus + "]";
	}

	
}

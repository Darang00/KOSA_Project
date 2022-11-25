package kr.or.kosa.dto;
import java.util.Date;


public class Reply {
	private int replynum;
	private int idx_fk;
	private int replynum2; //부모댓글
	private String userid; //댓글단 아이디
	private String content;
	private int refernum;
	private int depth;
	private int replycheck;
	private Date replydate;
	
	public Reply() {}

	public Reply(int replynum, int idx_fk, int replynum2, String userid, String content, int refernum, int depth,
			int replycheck, Date replydate) {
		super();
		this.replynum = replynum;
		this.idx_fk = idx_fk;
		this.replynum2 = replynum2;
		this.userid = userid;
		this.content = content;
		this.refernum = refernum;
		this.depth = depth;
		this.replycheck = replycheck;
		this.replydate = replydate;
	}

	public int getReplynum() {
		return replynum;
	}

	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}

	public int getIdx_fk() {
		return idx_fk;
	}

	public void setIdx_fk(int idx_fk) {
		this.idx_fk = idx_fk;
	}

	public int getReplynum2() {
		return replynum2;
	}

	public void setReplynum2(int replynum2) {
		this.replynum2 = replynum2;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRefernum() {
		return refernum;
	}

	public void setRefernum(int refernum) {
		this.refernum = refernum;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getReplycheck() {
		return replycheck;
	}

	public void setReplycheck(int replycheck) {
		this.replycheck = replycheck;
	}

	public Date getReplydate() {
		return replydate;
	}

	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}

	@Override
	public String toString() {
		return "Reply [replynum=" + replynum + ", idx_fk=" + idx_fk + ", replynum2=" + replynum2 + ", userid=" + userid
				+ ", content=" + content + ", refernum=" + refernum + ", depth=" + depth + ", replycheck=" + replycheck
				+ ", replydate=" + replydate + "]";
	}

	
	
	
	
	
}

package kr.or.kosa.dto;

import java.util.Date;

public class Reply {
	private int replynum;
	private int idx;
	private int parentreply; // 부모댓글
	private String userid; // 댓글단 아이디
	private String content;
	private int refernum;
	private int depth;
	private int replycheck;
	private Date replydate;
	private int replystatus;

	public Reply() {
	}

	public Reply(int replynum, int idx, int parentreply, String userid, String content, int refernum, int depth,
			int replycheck, Date replydate, int replystatus) {
		super();
		this.replynum = replynum;
		this.idx = idx;
		this.parentreply = parentreply;
		this.userid = userid;
		this.content = content;
		this.refernum = refernum;
		this.depth = depth;
		this.replycheck = replycheck;
		this.replydate = replydate;
		this.replystatus = replystatus;
	}

	public int getReplynum() {
		return replynum;
	}

	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getParentreply() {
		return parentreply;
	}

	public void setParentreply(int parentreply) {
		this.parentreply = parentreply;
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

	public int getReplystatus() {
		return replystatus;
	}

	public void setReplystatus(int replystatus) {
		this.replystatus = replystatus;
	}

	@Override
	public String toString() {
		return "Reply [replynum=" + replynum + ", idx=" + idx + ", parentreply=" + parentreply + ", userid=" + userid
				+ ", content=" + content + ", refernum=" + refernum + ", depth=" + depth + ", replycheck=" + replycheck
				+ ", replydate=" + replydate + ", replystatus=" + replystatus + "]";
	}

}

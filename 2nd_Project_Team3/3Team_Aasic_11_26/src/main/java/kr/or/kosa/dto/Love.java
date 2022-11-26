package kr.or.kosa.dto;

public class Love {

	private int loveidx;
	private int idx;
	private String userid;

	public Love() {
		super();
	}

	public Love(int loveidx, int idx, String userid) {
		super();
		this.loveidx = loveidx;
		this.idx = idx;
		this.userid = userid;
	}

	public int getLoveidx() {
		return loveidx;
	}

	public void setLoveidx(int loveidx) {
		this.loveidx = loveidx;
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

	@Override
	public String toString() {
		return "Love [loveidx=" + loveidx + ", idx=" + idx + ", userid=" + userid + "]";
	}

}

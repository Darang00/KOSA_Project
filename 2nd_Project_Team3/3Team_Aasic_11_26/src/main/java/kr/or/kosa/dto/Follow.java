package kr.or.kosa.dto;

public class Follow {

	private int follownum;
	private String userid;
	private String followid;

	public Follow() {
		super();
	}

	public Follow(int follownum, String userid, String followid) {
		super();
		this.follownum = follownum;
		this.userid = userid;
		this.followid = followid;
	}

	public int getFollownum() {
		return follownum;
	}

	public void setFollownum(int follownum) {
		this.follownum = follownum;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFollowid() {
		return followid;
	}

	public void setFollowid(String followid) {
		this.followid = followid;
	}

	@Override
	public String toString() {
		return "Follow [follownum=" + follownum + ", userid=" + userid + ", followid=" + followid + "]";
	}

}

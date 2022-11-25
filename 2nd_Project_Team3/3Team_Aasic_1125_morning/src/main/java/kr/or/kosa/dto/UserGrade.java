package kr.or.kosa.dto;

public class UserGrade {

	private String userid;
	private String gradename;
	private int totallovecount;

	public UserGrade() {
		super();
	}

	public UserGrade(String userid, String gradename, int totallovecount) {
		super();
		this.userid = userid;
		this.gradename = gradename;
		this.totallovecount = totallovecount;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public int getTotallovecount() {
		return totallovecount;
	}

	public void setTotallovecount(int totallovecount) {
		this.totallovecount = totallovecount;
	}

	@Override
	public String toString() {
		return "UserGrade [userid=" + userid + ", gradename=" + gradename + ", totallovecount=" + totallovecount + "]";
	}

}

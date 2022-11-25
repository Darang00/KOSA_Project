package kr.or.kosa.dto;

import java.util.Date;

/*
create table mvcregister(
 id number constraint pk_mvcregister_id primary key,
 pwd varchar2(20) not null,
 email varchar2(50) not null
)

*/
//생성
public class Member {
	private String userid;
	private int status;
	private String ip;
	
	public Member() {
		
	}

	public String getUserid() {
		return userid;
	}

	public int getStatus() {
		return status;
	}

	public String getIp() {
		return ip;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "Member [userid=" + userid + ", status=" + status + ", ip=" + ip + "]";
	}

	
}

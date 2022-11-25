package kr.or.kosa.dto;

import java.util.Date;

public class MemberDetail {
	private String userid;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String gender;
	private Date startdate;
	private String profilephoto;
	
	
	public MemberDetail() {
		super();
	}
	public MemberDetail(String userid, String pwd, String name, String email, String phone, String address,
			String gender, Date startdate, String profilephoto) {
		super();
		this.userid = userid;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.startdate = startdate;
		this.profilephoto = profilephoto;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public String getProfilephoto() {
		return profilephoto;
	}
	public void setProfilephoto(String profilephoto) {
		this.profilephoto = profilephoto;
	}
	@Override
	public String toString() {
		return "MemberDetail [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", address=" + address + ", gender=" + gender + ", startdate=" + startdate + ", profilephoto="
				+ profilephoto + "]";
	}
	
}

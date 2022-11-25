package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.kosa.dto.Member;
import kr.or.kosa.dto.MemberDetail;


public class UserDao {

	DataSource ds = null;
	private Connection conn;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt1;
	private PreparedStatement pstmt2;
	private ResultSet rs;
	private String sql;
	private int resultRow; 
	private boolean idCheck; // 아이디 체크여부
	private Member user;
	private MemberDetail userDetail;
	private List<Member> userList;
	private List<MemberDetail> userDetailList;
	private String pwdCheck;

	
	public UserDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		conn = null;
		pstmt = null;
		pstmt1 = null;
		pstmt2 = null;
		rs = null;
		resultRow = 0;
		idCheck = false;
		sql = "";
		// dtoList = null;
	}

	/* pstmt.setDate(11,new java.sql.Date(dto.getStartDate().getTime())); */
	// 아이디 체크
	public boolean idCheck(String id, String pwd) {
		try {
			conn = ds.getConnection();
			sql = "select userid, pwd from memberdetail where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pwdCheck = rs.getString("pwd");
				if (pwdCheck.equals(pwd)) {
					idCheck = true; // 아이디, 비밀번호 일치
				} else {
					idCheck = false; // 비밀번호 불일치
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {

			}
		}
		return idCheck;
	}

	/*
	 * public List<User>getUserList(){
	 * 
	 * 
	 * return dtoList; }
	 */

	// writeOk ...
	// 회원가입
	public int writeOk(Member user, MemberDetail userDetail) {
	      try {
	         conn = ds.getConnection();

	         conn.setAutoCommit(false);

	         String sql1 = "insert into memberdetail(userid, pwd, name, email, "
	               + "phone, address, gender, startdate, profilephoto)" + " values(?,?,?,?,?,?,?,sysdate,0)";
	         
	         
	         pstmt1 = conn.prepareStatement(sql1);
	         pstmt1.setString(1, userDetail.getUserid());
	         pstmt1.setString(2, userDetail.getPwd());
	         pstmt1.setString(3, userDetail.getName());
	         pstmt1.setString(4, userDetail.getEmail());
	         pstmt1.setString(5, userDetail.getPhone());
	         pstmt1.setString(6, userDetail.getAddress());
	         pstmt1.setString(7, userDetail.getGender());
	         int test = pstmt1.executeUpdate();
	         


	         String sql2 = "insert into member(userid, status, ip) values(?,1,?)";
	         
	         pstmt2 = conn.prepareStatement(sql2);
	         pstmt2.setString(1, user.getUserid());
	         pstmt2.setString(2, user.getIp());
	         resultRow = pstmt2.executeUpdate();
	         System.out.println(user.toString());
	         System.out.println(resultRow);

	         conn.commit();
	         conn.setAutoCommit(true);

	      } catch (SQLException e) {
	         try {
	            conn.rollback();
	         } catch (Exception e1) {
	            e1.printStackTrace();
	         }

	      } finally {
	         try {
	            pstmt1.close();
	            pstmt2.close();
	            conn.close();
	         } catch (Exception e2) {

	         }

	      }
	      return resultRow;
	   }

	// 회원테이블 전체조회 getAllTableList();
	public int getAllTableList() {
		
		
		
		return resultRow;
	}
	
	
	  
	 
}

package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import kr.or.kosa.dto.Board;


public class BoardDao {
	DataSource ds = null;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	private int resultRow;
	private Board board;
	private List<Board> dtoList;
	private String pwdCheck; 
	private int totalcount;
	
	
	public BoardDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		conn = null;
		pstmt = null;
		rs = null;
		resultRow = 0;
		sql = "";
		dtoList = null;
	}
	
	
	//글쓰기(원본글)
		public int writeok(Board boarddata) {
			conn = null;
			pstmt = null;
			int row = 0;
			try {
				conn = ds.getConnection();
				System.out.println("글쓰기 준비");
				String sql = "insert into board(idx, userid, boardname, title, content, writedate, readnum, refernum, boardstatus)"
			               + "values(board_idx.nextval, ?, ?, ?, ?, sysdate, ?, ?, 2)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, boarddata.getUserid());
				//pstmt.setString(2, boarddata.getPwd());
				pstmt.setString(2, boarddata.getBoardname());
				pstmt.setString(3, boarddata.getTitle());
				pstmt.setString(4, boarddata.getContent());
				//pstmt.setString(6, boarddata.getHomepage());
				pstmt.setInt(5, boarddata.getReadnum());
				System.out.println("set 완료");
				
				//계층형 게시판
				//refer(참조값) , step , depth
				//1. 원본글 : refer 생성?  , step(0) default , depth(0) default
				//2. 답변글 : refer 생성?  , step +1 , depth +1
				
				//int refermax = getMaxRefer();
				//int refer = refermax + 1;
				//pstmt.setInt(8,refer);
				
				row = pstmt.executeUpdate();
				
			}catch(Exception e) {
				
			}finally {
				try {
					pstmt.close();
					conn.close();//반환하기
				} catch (Exception e2) {
				
				}
			}
			
			
			return row;
		}
	
	
	//게시물 총 건수 구하기
	public int totalBoardCount() {
		try {
			conn = ds.getConnection();
			sql = "select count(*) cnt from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalcount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				
			}
		}
		return totalcount;
	}
	
	//게시물 목록보기
	public List<Board> list(int cpage , int pagesize){
		
		conn = null;
		pstmt = null;
		rs = null;
		dtoList = null;
		try {
			conn = ds.getConnection();
			sql = "select * from" + "(select rownum rn,idx, title, userid, writedate, readnum "
		               + "from (SELECT * FROM board ORDER BY idx DESC) "
		               + "where rownum <= ?"
		               + ") where rn >= ?";
			
			/*
			 sql = "select * from" + "(select rownum rn,idx, title, userid, writedate, readnum "
		               + "from (SELECT * FROM board where boardstatus=5 ORDER BY idx DESC) "
		               + "where rownum <= ?"
		               + ") where rn >= ?"; 
			 
			  */
			
			pstmt = conn.prepareStatement(sql);
			//공식같은 로직
			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5
			//
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			dtoList = new ArrayList<Board>();
			while(rs.next()) {
				
				Board board = new Board();
				board.setIdx(rs.getInt("idx"));
				board.setTitle(rs.getString("title")); //sql문이랑 컬럼명 다 맞춰야 한다. get이면
				board.setUserid(rs.getString("userid"));
				board.setWritedate(rs.getDate("writedate"));
				board.setReadnum(rs.getInt("readnum"));
				
				/*
				board.setRefer(rs.getInt("refer"));
				board.setStep(rs.getInt("step"));
				board.setDepth(rs.getInt("depth")); */
				
				dtoList.add(board);
			}
			System.out.println(dtoList);
		}catch (Exception e) {
			System.out.println("오류 :" + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				
			}
		}
			
		return dtoList;
	}
	
	
	
	
	
}

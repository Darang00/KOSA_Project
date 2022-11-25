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
	
	
	//게시물 총 건수 구하기
	public int totalBoardCount() {
		try {
			conn = ds.getConnection();
			sql = "select count(*) cnt from jspboard";
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
	public List<Board> list(int cpage, int pagesize){
		try {
			conn = ds.getConnection();
//			sql = "select * from " +
//                    "(select rownum rn,idx,writer,email,homepage,pwd,subject , content, writedate, readnum " +
//                    ",filename,filesize,refer,depth,step " +
//                    " from ( SELECT * FROM board ORDER BY refer DESC , step ASC ) "+
//                    " where rownum <= ?" +  //endrow
//                    ") where rn >= ?"; //startrow
			
			sql = "select * from board";
                    
                    
			pstmt = conn.prepareStatement(sql);
			//공식같은 로직
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize; 
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
			dtoList = new ArrayList<Board>();
			
			while(rs.next()) {
				board = new Board();
				board.setIdx(rs.getInt("idx"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getDate("writedate"));
				board.setReadnum(rs.getInt("readnum"));
				//계층형
				board.setRefer(rs.getInt("refer"));
				board.setStep(rs.getInt("step"));
				board.setDepth(rs.getInt("depth"));
				
				dtoList.add(board);
			}
		} catch (Exception e) {
			System.out.println("오류 : " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dtoList;
	}
	
	
}

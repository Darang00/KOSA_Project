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
import kr.or.kosa.dto.Bookmarks;
import kr.or.kosa.dto.Files;
import kr.or.kosa.dto.MemberDetail;

public class MemberDao {
	DataSource ds = null;
	private Connection conn;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt1;
	private PreparedStatement pstmt2;
	private PreparedStatement pstmt3;
	private ResultSet rs1;
	private ResultSet rs2;
	private String sql1;
	private String sql2;
	private String sql3;
	private int idx;
	private int resultRow;
	private String pwdCheck;
	private Board board;
	private List<Board> boardList;
	private int totalcount;

	public MemberDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		conn = null;
		pstmt = null;
		pstmt1 = null;
		pstmt2 = null;
		pstmt3 = null;
		rs1 = null;
		resultRow = 0;
		sql1 = "";
		sql2 = "";
		sql3 = "";
		idx = 0;
	}
	
	
	// 계정 수정
	public int update(MemberDetail memberdetail) {
		try {
			// String pwdCheck;
			conn = ds.getConnection();
			sql1 = "update MemberDetail set pwd=?, name=?, email=?, phone=?, address=?, gender=? where userid=?";
			pstmt1 = conn.prepareStatement(sql1);
			System.out.println("sql구문출력 : "+sql1);
			
			pstmt1.setString(1, memberdetail.getPwd());
			pstmt1.setString(2, memberdetail.getName());
			pstmt1.setString(3, memberdetail.getEmail());
			pstmt1.setString(4, memberdetail.getPhone());
			pstmt1.setString(5, memberdetail.getAddress());
			pstmt1.setString(6, memberdetail.getGender());
			//pstmt.setDate(5, memberdetail.getStartDate());
			//setDate(5, new java.sql.Date(memberdetail.getStartDate().getTime()));
			pstmt1.setString(7, memberdetail.getUserid());
			System.out.println();
			System.out.println("memberdetail출력 : "+memberdetail.toString());
			resultRow = pstmt1.executeUpdate();
			System.out.println("resultRow 출력 : "+resultRow);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt1.close();
				rs1.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return resultRow;
	}
	
	//카테고리별 게시물 총 건수 구하기
	public int totalBoardCount(String boardname) {
		try {
			conn = ds.getConnection();
			sql1 = "select count(*) cnt from board where boardname = ?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, boardname);

			rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				totalcount = rs1.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt1.close();
				rs1.close();
				conn.close();
			} catch (Exception e2) {
				
			}
		}
		return totalcount;
	}
	
	
	//게시물 총 건수 구하기
	public int totalBoardCount() {
		try {
			conn = ds.getConnection();
			sql1 = "select count(*) cnt from board";
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				totalcount = rs1.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt1.close();
				rs1.close();
				conn.close();
			} catch (Exception e2) {
				
			}
		}
		return totalcount;
	}
	
	//카테고리별 게시물 목록보기
	public List<Board> listByCategory(int cpage, int pagesize, String boardname){
		try {
			conn = ds.getConnection();
			sql1 = "select * from (select rownum as num, b.* from " +
				   "board b where boardname = ? order by lovenum desc, idx asc) " +
				   "where num <= ? " +  //end
				   "and num >= ?";  //start
			pstmt1 = conn.prepareStatement(sql1);
			//공식같은 로직
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			pstmt1.setString(1, boardname);
			pstmt1.setInt(2, end);
			pstmt1.setInt(3, start);
			rs1 = pstmt1.executeQuery();
			boardList = new ArrayList<Board>();
			
			while(rs1.next()) {
				board = new Board();
				board.setIdx(rs1.getInt("idx"));
				board.setTitle(rs1.getString("title"));
				board.setUserid(rs1.getString("userid"));
				board.setWritedate(rs1.getDate("writedate"));
				board.setReadnum(rs1.getInt("readnum"));
				//계층형
				board.setLovenum(rs1.getInt("lovenum"));
				boardList.add(board);
			}
		} catch (Exception e) {
			System.out.println("오류 : " + e.getMessage());
		}finally {
			try {
				pstmt1.close();
				rs1.close();
				conn.close();//반환
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return boardList;
	}
	
	

	//게시물 목록보기
	public List<Board> list(int cpage, int pagesize){
		try {
			conn = ds.getConnection();
			sql1 = "select * from (select rownum as num, b.* from " +
				   "board b order by lovenum desc, idx asc) " +
				   "where num <= ? " +  //end
				   "and num >= ?";  //start

			pstmt1 = conn.prepareStatement(sql1);
			//공식같은 로직
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize; 
			pstmt1.setInt(1, end);
			pstmt1.setInt(2, start);
			rs1 = pstmt1.executeQuery();
			boardList = new ArrayList<Board>();
			
			while(rs1.next()) {
				board = new Board();
				board.setIdx(rs1.getInt("idx"));
				board.setTitle(rs1.getString("title"));
				board.setUserid(rs1.getString("userid"));
				board.setWritedate(rs1.getDate("writedate"));
				board.setReadnum(rs1.getInt("readnum"));
				//계층형
				board.setLovenum(rs1.getInt("lovenum"));
				boardList.add(board);
			}
		} catch (Exception e) {
			System.out.println("오류 : " + e.getMessage());
		}finally {
			try {
				pstmt1.close();
				rs1.close();
				conn.close();//반환
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return boardList;
	}
	
	
	//글쓰기
	public int writeok(Board board) {
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			String boardname="";
			sql1 = "select boardname from category where boardname=?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, board.getBoardname());
			rs1 = pstmt1.executeQuery();
			sql2 = "insert into board(idx,userid,boardname,title,content,writedate,readnum,lovenum,boardstatus) "+ 
				   "values(board_idx.nextval,?,?,?,?,sysdate,0,0,1)";
			if (rs1.next()) {
				boardname = rs1.getString("boardname");				
			}else {
				boardname="";	
			}
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, board.getUserid());
			pstmt2.setString(2, boardname);
			pstmt2.setString(3, board.getTitle());
			pstmt2.setString(4, board.getContent());			
			resultRow = pstmt2.executeUpdate();

			
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1){
				e1.printStackTrace();
			}
		} finally {
			try {
				pstmt1.close();
				rs1.close();
				pstmt2.close();
				conn.close();
			} catch (Exception e2) {

			}
		}
		return resultRow;
	}
	//글쓰기(파일 추가)
	public int writeok(Board board, Files file) {
		try {
			
			resultRow = writeok(board);
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			if(resultRow > 0)
			{
				sql1 = "select max(idx) as idx from board";
				pstmt1 = conn.prepareStatement(sql1);
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					idx = rs1.getInt("idx");
					sql2 = "insert into files(filenum,idx,filename,filesize) "
							+ "values(files_idx.nextval,?,?,?)";
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, idx);
					pstmt2.setString(2, file.getFilename());
					pstmt2.setInt(3, file.getFilesize());
					resultRow = pstmt2.executeUpdate();
				}
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1){
				e1.printStackTrace();
			}
		} finally {
			try {
				pstmt1.close();
				rs1.close();
				pstmt2.close();
				conn.close();
			} catch (Exception e2) {

			}
		}
		return resultRow;
	}
	
	
	
	
	
	// 게시물 상세보기
	public Board getContent(int idx) {
		try {
			conn = ds.getConnection();
			sql1 = "select idx, userid, boardname, title, content, writedate, "
					+ "readnum, lovenum, boardstatus from board where idx=?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, idx);
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				board = new Board();
				board.setIdx(rs1.getInt("idx"));
				board.setUserid(rs1.getString("userid"));
				board.setBoardname(rs1.getString("boardname"));
				board.setTitle(rs1.getString("title"));
				board.setContent(rs1.getString("content"));
				board.setBoardstatus(rs1.getInt("boardstatus"));
				board.setReadnum(rs1.getInt("readnum"));
				board.setLovenum(rs1.getInt("lovenum"));
				board.setWritedate(rs1.getDate("writedate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt1.close();
				rs1.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return board;
	}

	// 게시글 조회수 증가
	public boolean getReadNum(String idx) {
		boolean result = false;
		try {
			conn = ds.getConnection();
			sql1 = "update board set readnum = readnum + 1 where idx=?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, idx);
			resultRow = pstmt1.executeUpdate();
			if (resultRow > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt1.close();
				rs1.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}
	
	public void BookmarksDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			conn = null;
			pstmt = null;
			rs2 = null;
			resultRow = 0;
			sql1 = "";
			idx = 0;
			
		} catch(Exception e1) {
			System.out.println("e1: " + e1.getMessage());
			e1.printStackTrace();
		}

	} 
	
	//북마크 체크 여부 검사
	/*public int BookmarkCheck(String userid, int idx){
		System.out.println("BookmarkCheck 함수 탈거다");
		String sql1 = "select count(*) as cnt from bookmarks where userid = ?"; //예빈언니가 문제 없다고 했음 내 책임 아님
		List<Bookmarks> bookMarksList = new ArrayList<Bookmarks>();
		
		try {
			System.out.println("BookmarkCheck 함수 try문 진입");
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, userid);
			rs1 = pstmt.executeQuery();
			resultRow = 0;
			while (rs1.next()) {
				resultRow = rs1.getInt("cnt");
			} }
			catch(Exception e3) {
				System.out.println("e3 " + e3);
				e3.printStackTrace();
			}
		
		return resultRow;//int값 맞으면 1 아니면 0;
	}  */
	
	//북마크 여부 검사
	public int bookmarkCheck(Bookmarks bookmarks) {
		int bookmarkcheck = -1; //아예 동작하지 않을 경우 대비해서 -1로 초기화
		
		try {
			conn = ds.getConnection();
			sql1 = "select count(*) cnt from bookmarks where idx = ? and userid = ?";
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, bookmarks.getIdx());
			pstmt1.setString(2, bookmarks.getUserid());
			rs1 = pstmt1.executeQuery();
			
			if(rs1.next()) {
				bookmarkcheck = rs1.getInt("cnt");
			}
		}catch (Exception e2) {
			System.out.println("e2: " + e2.getMessage());
			e2.getStackTrace();
		}finally {
			try {
				pstmt1.close();
				rs1.close();
				conn.close();
			}catch(Exception e3) {
				System.out.println("e3: " + e3.getMessage());
				e3.getStackTrace();
			}
		}

		return bookmarkcheck;
	}
	
	//북마크 insert, delete
	public int bookmarkUpdate(Bookmarks bookmarks) {
		int bookmarkcheck = -1;
		
		try {
			bookmarkcheck = bookmarkCheck(bookmarks);
			
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			if(bookmarkcheck == 0) {
				sql1 = "insert into bookmarks (bookidx, idx, userid, title, writedate, lovenum)"
						+ "values (book_idx.nextval, ?, ?, ?, ?, 7)";
				pstmt1 = conn.prepareStatement(sql1);
				System.out.println(sql1);
				System.out.println(bookmarks.toString());
				pstmt1.setInt(1, bookmarks.getIdx());
				pstmt1.setString(2,  bookmarks.getUserid());
				pstmt1.setString(3,  bookmarks.getTitle());
				pstmt1.setDate(4, bookmarks.getWritedate());
				//pstmt1.setInt(5, bookmarks.getLovenum());
				resultRow = pstmt1.executeUpdate();
				System.out.println("insert 성공!");
			} else if(bookmarkcheck ==1) {
				sql2 = "delete from bookmarks where idx=? and userid=?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, bookmarks.getIdx());
				pstmt2.setString(2, bookmarks.getUserid());
				resultRow = pstmt2.executeUpdate();
				System.out.println("delete 성공!");
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (Exception e4) {
			System.out.println("e4: " + e4.getMessage());
			e4.getStackTrace();
			try {
				conn.rollback();
			} catch (Exception e5) {
				System.out.println("e5: " + e5.getMessage());
				e5.printStackTrace();
			} finally {
				try {
					pstmt1.close();
					conn.close();
				} catch(Exception e6) {
					System.out.println("e6: " + e6.getMessage());
					e6.getStackTrace();
				}
			}
		}
		System.out.println("bookmarks update 성공! resultRow 반환할거다");
		return resultRow;
	}
	
	
	
	//북마크 전체 목록 조회
	public List<Bookmarks> getBookmarks(int bookmarksindex, String userid, int idx){
		String sql1 = "select * from bookmarks where userid = ?"; //예빈언니가 문제 없다고 했음 내 책임 아님
		List<Bookmarks> bookMarksList = new ArrayList<Bookmarks>();
		
		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, userid);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				Bookmarks bookmarks = new Bookmarks();
				bookmarks.setbookidx(rs1.getInt(1));
				bookmarks.setUserid(rs1.getString(2));
				bookmarks.setIdx(rs1.getInt(3));
				bookMarksList.add(bookmarks);
			} }
			catch(Exception e3) {
				System.out.println("e3 " + e3);
				e3.printStackTrace();
			}
		
		return bookMarksList;
	}

	
}

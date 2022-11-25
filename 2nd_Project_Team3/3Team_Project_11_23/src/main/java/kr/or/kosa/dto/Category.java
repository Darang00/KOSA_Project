package kr.or.kosa.dto;

public class Category {
	private String boardname;

	
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String boardname) {
		super();
		this.boardname = boardname;
	}

	public String getBoardname() {
		return boardname;
	}

	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}

	@Override
	public String toString() {
		return "Category [boardname=" + boardname + "]";
	}
	
	
	
	
}

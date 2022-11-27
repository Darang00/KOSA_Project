package kr.or.kosa.dto;

public class Files {
	private int filenum;
	private int idx;
	private String filename;
	private int filesize;
	
	
	public Files() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Files(int filenum, int idx, String filename, int filesize) {
		super();
		this.filenum = filenum;
		this.idx = idx;
		this.filename = filename;
		this.filesize = filesize;
	}
	public int getFilenum() {
		return filenum;
	}
	public void setFilenum(int filenum) {
		this.filenum = filenum;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	@Override
	public String toString() {
		return "Files [filenum=" + filenum + ", idx=" + idx + ", filename=" + filename + ", filesize=" + filesize + "]";
	}
	
}

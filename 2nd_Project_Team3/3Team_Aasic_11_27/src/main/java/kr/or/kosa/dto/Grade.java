package kr.or.kosa.dto;

public class Grade {

	private String gradename;
	private int graderange;

	public Grade() {
		super();
	}

	public Grade(String gradename, int graderange) {
		super();
		this.gradename = gradename;
		this.graderange = graderange;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public int getGraderange() {
		return graderange;
	}

	public void setGraderange(int graderange) {
		this.graderange = graderange;
	}

	@Override
	public String toString() {
		return "Grade [gradename=" + gradename + ", graderange=" + graderange + "]";
	}

}

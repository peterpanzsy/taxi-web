package cn.edu.cup.map.business;

public class Line{
	public Line(){
		
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Line(String start2, String end2, int i) {
		// TODO Auto-generated constructor stub
		this.start=start2;
		this.end=end2;
		this.type=i;
	}
	String start;
	String end;
	int type;
}
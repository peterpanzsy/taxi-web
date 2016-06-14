package cn.edu.cup.manage.business;

public class Style {
	public Style() {
		super();
	}
	int ID;
	String StyleName;
	public int getID() {
		return ID;
	}
	public Style(int iD, String styleName) {
		super();
		ID = iD;
		StyleName = styleName;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getStyleName() {
		return StyleName;
	}
	public void setStyleName(String styleName) {
		StyleName = styleName;
	}
}

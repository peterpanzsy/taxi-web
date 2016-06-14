package cn.edu.cup.manage.business;

public class Parameters {
	String name;
	String ID;
	String measureID;//单位
	String display;
	String measureSymbol;//体系
	public Parameters(Integer id2, String mid, String mSymbol, String display2,
			String name2) {
		ID=id2.toString();
		measureID=mid;
		measureSymbol=mSymbol;
		display=display2;
		name=name2;
		// TODO Auto-generated constructor stub
	}
	public String getMeasureSymbol() {
		return measureSymbol;
	}
	public void setMesureSymbol(String measureSymbol) {
		this.measureSymbol = measureSymbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMeasureID() {
		return measureID;
	}
	public void setMeasureID(String measureID) {
		this.measureID = measureID;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}

}

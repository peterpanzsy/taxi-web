package cn.edu.cup.manage.business;

public class Measure {
	int ID;
	int PhysicalID;
	public Measure(int iD, int physicalID, Physical phy, Style style,
			String eName, String cName, String symbol, double ratioA,
			double ratioB, int styleID) {
		super();
		ID = iD;
		PhysicalID = physicalID;
		this.phy = phy;
		this.style = style;
		EName = eName;
		CName = cName;
		Symbol = symbol;
		RatioA = ratioA;
		RatioB = ratioB;
		StyleID = styleID;
	}
	Physical phy;
	Style style;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getPhysicalID() {
		return PhysicalID;
	}
	public void setPhysicalID(int physicalID) {
		PhysicalID = physicalID;
	}
	public Physical getPhy() {
		return phy;
	}
	public void setPhy(Physical phy) {
		this.phy = phy;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	public String getEName() {
		return EName;
	}
	public void setEName(String eName) {
		EName = eName;
	}
	public String getCName() {
		return CName;
	}
	public void setCName(String cName) {
		CName = cName;
	}
	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	public double getRatioA() {
		return RatioA;
	}
	public void setRatioA(double ratioA) {
		RatioA = ratioA;
	}
	public double getRatioB() {
		return RatioB;
	}
	public void setRatioB(double ratioB) {
		RatioB = ratioB;
	}
	public double getStyleID() {
		return StyleID;
	}
	public void setStyleID(int styleID) {
		StyleID = styleID;
	}
	public Measure() {
		super();
	}
	String EName;
	String CName;
	String Symbol;
	double RatioA;
	double RatioB;
	int StyleID;
}

package cn.edu.cup.manage.business;

public class Physical {
	public Physical(int iD, String cName, String eName, String description,
			String iSOBasicUnit) {
		super();
		ID = iD;
		CName = cName;
		EName = eName;
		Description = description;
		ISOBasicUnit = iSOBasicUnit;
	}
	int ID;
	String CName;
	String EName;
	String Description;
	String ISOBasicUnit;
	public Physical() {
		super();
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCName() {
		return CName;
	}
	public void setCName(String cName) {
		CName = cName;
	}
	public String getEName() {
		return EName;
	}
	public void setEName(String eName) {
		EName = eName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getISOBasicUnit() {
		return ISOBasicUnit;
	}
	public void setISOBasicUnit(String iSOBasicUnit) {
		ISOBasicUnit = iSOBasicUnit;
	}
}

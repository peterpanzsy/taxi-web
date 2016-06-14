package cn.edu.cup.manage.business;

public class CalcHisInput {
	Integer id ;
	Integer pro_id ;
	Integer par_id ;
	String par_display ;
	
	Double par_value ;
	String par_Name ;
	String symbol ;
	public CalcHisInput(Integer id, Integer pro_id, Integer par_id,
			String par_display, Double par_value, String par_Name, String symbol) {
		super();
		this.id = id;
		this.pro_id = pro_id;
		this.par_id = par_id;
		this.par_display = par_display;
		this.par_value = par_value;
		this.par_Name = par_Name;
		this.symbol = symbol;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPro_id() {
		return pro_id;
	}
	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
	}
	public Integer getPar_id() {
		return par_id;
	}
	public void setPar_id(Integer par_id) {
		this.par_id = par_id;
	}
	public String getPar_display() {
		return par_display;
	}
	public void setPar_display(String par_display) {
		this.par_display = par_display;
	}
	public Double getPar_value() {
		return par_value;
	}
	public void setPar_value(Double par_value) {
		this.par_value = par_value;
	}
	public String getPar_Name() {
		return par_Name;
	}
	public void setPar_Name(String par_Name) {
		this.par_Name = par_Name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}

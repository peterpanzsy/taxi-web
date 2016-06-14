package cn.edu.cup.map.action;


import com.opensymphony.xwork2.ActionSupport;

public class TableAddAction extends ActionSupport{
	

	String id;
	String name;
	String address;
	String town;
	String country;
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getTown(){
		return town;
	}
	public void setTown(String town){
		this.town=town;
	}
	
	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country=country;
	}

	public String execute()
	{
		id="1";
	    
		return "SUCCESS";
	}
	
}

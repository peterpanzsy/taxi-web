package cn.edu.cup.map.action;


import com.opensymphony.xwork2.ActionSupport;

public class TableDeleteAction extends ActionSupport{
	

	String id;
	
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	

	public String execute()
	{
//		testinit();
		
		return "SUCCESS";
	}
	
}

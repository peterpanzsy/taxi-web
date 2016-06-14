package cn.edu.cup.test;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.DefaultActionInvocation;

public class iceTest extends ActionSupport{
	public String username;
	long id;
    String ice;
	public String getIce() {
		return ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String execute(){
		System.out.println("step 4");
		System.out.println(username);
		return "SUCCESS";
	}
	
}

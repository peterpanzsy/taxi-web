package cn.edu.cup.test;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.DefaultActionInvocation;

public class TestData extends ActionSupport{
	private String teacher_name;
	private String organization_name;
	
	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	
	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	
	public TestData(String teacher_name,String organization_name){
		super();
		this.teacher_name=teacher_name;
		this.organization_name=organization_name;
	}
	
}

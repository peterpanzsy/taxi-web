package cn.edu.cup.manage.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.edu.cup.manage.dao.PhysicalDao;
import cn.edu.cup.manage.dao.UserDao;
import cn.edu.cup.manage.business.Measure;
import cn.edu.cup.manage.business.User;

public class UserAction extends ActionSupport{
	String  username;
	String password;
	String type;
	UserDao userDAO;
	List<User> dataList;
	public List<User> getDataList() {
		return dataList;
	}
	public void setDataList(List<User> dataList) {
		this.dataList = dataList;
	}
	public UserDao getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String list(){		
		UserDao dao=new UserDao();
		dataList=dao.getUserList();
		return "SUCCESS";
	}

	
}

package cn.edu.cup.gui.business;

import java.util.Date;

public class GUIPro {

	Integer id;
	String proname;
	Integer aid;
	String author;
	String description;
	Date addTime;
	String JSONData;
	public String getJSONData() {
		return JSONData;
	}
	public void setJSONData(String jSONData) {
		JSONData = jSONData;
	}
	public GUIPro(Integer id, String proname, Integer aid, String author,
			String description, Date addTime) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.proname=proname;
		this.aid=aid;
		this.author=author;
		this.description=description;
		this.addTime=addTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}

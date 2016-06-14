package cn.edu.cup.manage.business;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.edu.cup.tools.DateTools;

public class ProjectCalcHis {
 Date startTime;
 public ProjectCalcHis(Date startTime, Date endTime, int iD, int pro_ID,
		int algorithm_ID, String algorithm_name, String calc_re) {
	super();
	this.startTime = startTime;
	this.endTime = endTime;
	ID = iD;
	Pro_ID = pro_ID;
	Algorithm_ID = algorithm_ID;
	Algorithm_name = algorithm_name;
	Calc_re = calc_re;
	this.startTimeS=DateTools.getStringFromDate(startTime);
	this.endTimeS=DateTools.getStringFromDate(endTime);
}
String startTimeS;
 Date endTime;
 String endTimeS;
 int ID;
 int Pro_ID;
 int Algorithm_ID;
 String Algorithm_name;
 String Calc_re;
 Map<String,Double> paramInputs=new HashMap<String,Double>();
 Map<String,Double> paramOutputs=new HashMap<String,Double>();
public Date getStartTime() {
	return startTime;
}
public void setStartTime(Date startTime) {
	this.startTime = startTime;
}
public String getStartTimeS() {
	return startTimeS;
}
public void setStartTimeS(String startTimeS) {
	this.startTimeS = startTimeS;
}
public Date getEndTime() {
	return endTime;
}
public void setEndTime(Date endTime) {
	this.endTime = endTime;
}
public String getEndTimeS() {
	return endTimeS;
}
public void setEndTimeS(String endTimeS) {
	this.endTimeS = endTimeS;
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public int getPro_ID() {
	return Pro_ID;
}
public void setPro_ID(int pro_ID) {
	Pro_ID = pro_ID;
}
public int getAlgorithm_ID() {
	return Algorithm_ID;
}
public void setAlgorithm_ID(int algorithm_ID) {
	Algorithm_ID = algorithm_ID;
}
public String getAlgorithm_name() {
	return Algorithm_name;
}
public void setAlgorithm_name(String algorithm_name) {
	Algorithm_name = algorithm_name;
}
public String getCalc_re() {
	return Calc_re;
}
public void setCalc_re(String calc_re) {
	Calc_re = calc_re;
}
public Map<String, Double> getParamInputs() {
	return paramInputs;
}
public void setParamInputs(Map<String, Double> paramInputs) {
	this.paramInputs = paramInputs;
}
public Map<String, Double> getParamOutputs() {
	return paramOutputs;
}
public void setParamOutputs(Map<String, Double> paramOutputs) {
	this.paramOutputs = paramOutputs;
}
 	
}

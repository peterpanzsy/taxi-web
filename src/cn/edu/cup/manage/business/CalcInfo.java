package cn.edu.cup.manage.business;

import java.util.HashMap;
import java.util.Map;

public class CalcInfo {
	Map<String,Double> paramInputs=new HashMap<String,Double>();
	Map<String,Double> paramOutputs=new HashMap<String,Double>();
	
	int Algorthm;
	public Map<String, Double> getParamInputs() {
		return paramInputs;
	}
	public void setParamInputs(Map<String, Double> paramInputs) {
		this.paramInputs = paramInputs;
	}
	public int getAlgorthm() {
		return Algorthm;
	}
	public void setAlgorthm(int algorthm) {
		Algorthm = algorthm;
	}
	public void addParamInput(String paramName,Double value){
		this.paramInputs.put(paramName, value);
	}
	public double getParamInput(String paramName){
		return this.paramInputs.get(paramName);
	}
	public double getParamOutput(String paramName){
		return this.paramOutputs.get(paramName);
	}
	public void addParamOutput(String paramName,Double value){
		this.paramOutputs.put(paramName, value);
	}
	public CalcInfo() {
		super();
	}
}

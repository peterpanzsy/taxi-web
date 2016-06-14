package cn.edu.cup.map.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.cup.test.TestData;

import com.opensymphony.xwork2.ActionSupport;

public class TableAction extends ActionSupport{
	private static String PAGE="page";
	private static String TOTAL="total";
	private static String RECORDS="records";
	
	private long total;
	private long page;
	private long records;
	List<TestData> dataList;
	List<TestData> aaData;
	private String resultJson;
	
	Map result = new HashMap();
//	public List<TestData> getDataList() {
//		return dataList;
//	}
	
	public List<TestData> getAaData(){
		return aaData;
	}
	
	public long getTotal(){
		return total;
	}
	public void setTotal(long total){
		this.total=total;
	}
	
	public long getPage(){
		return page;
	}
	public void setPage(long page){
		this.page=page;
	}
	
	public long getRecords(){
		return records;
	}
	public void setRecords(long records){
		this.records=records;
	}
	
	public String getResultJson(){
		return resultJson;
	}
	public void setResultJson(String resultJson){
		this.resultJson=resultJson;
	}
	
	public void testinit(){
		dataList=new ArrayList<TestData>();
		dataList.add(new TestData("a","a"));
		aaData=new ArrayList<TestData>();
		aaData.add(new TestData("b","b"));
		for(int i=0;i<10000;i++){
		aaData.add(new TestData("a","a"));
		
		}
		
//		page=1;
		records=10000;
		total=10;
		result.put(PAGE, 1);
		result.put(RECORDS, 10000);
		result.put(TOTAL, 10);
		result.put("aaData", aaData);
//		resultJson = JSONObject.fromObject(result).toString();
	}
	
	public String execute()
	{
		testinit();
		
		return "SUCCESS";
	}
	
}

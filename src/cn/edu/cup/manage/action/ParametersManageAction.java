package cn.edu.cup.manage.action;

import java.util.List;

import cn.edu.cup.manage.business.Parameters;
import cn.edu.cup.manage.dao.ParameterDao;
import cn.edu.cup.manage.dao.PhysicalDao;

import com.opensymphony.xwork2.ActionSupport;

public class ParametersManageAction extends ActionSupport{
	private int ID;
	private String name;
	private int measureID;
	private String display;
	private int rows;
	private int rowNum;
	private int total;
	private String sidx;
	private String sord;
	Parameters param;
	List<Parameters> dataList;
	private int page;
	private int records;
	private List<Integer> ids;
	private boolean exist;
	public boolean isExist() {
		return exist;
	}
	public void setParam(Parameters param) {
		this.param = param;
	}
	public Parameters getParam() {
		return param;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public int getMeasureID() {
		return measureID;
	}
	public void setMeasureID(int measureID) {
		this.measureID = measureID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}

	public void setDataList(List<Parameters> dataList) {
		this.dataList = dataList;
	}
	public List<Parameters> getDataList() {
		return dataList;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	public int getPage() {
		return page;
	}

	public void setRecords(int records) {
		this.records = records;
	}
	public int getRecords() {
		return records;
	}

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getTotal() {
		return total;
	}
	
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	
	public void setSord(String sord) {
		this.sord = sord;
	}
	
	public String search(){
		ParameterDao dao=new ParameterDao();
		param=dao.searchParameter(ID);
		return "SUCCESS";
	}
	public String list(){		

		ParameterDao dao=new ParameterDao();

		dataList=dao.getParametersList(page,rows,sidx,sord);
	
		records=dao.getCountParameters();

		if(records!=0&&rows!=0){
			total=records/rows;
			if(records%rows!=0){
				total++;
			}
		}
		return "SUCCESS";
	}


	public String add(){

		ParameterDao dao=new ParameterDao();
		int count=dao.isExistParameter(name);
		if(count==0){
			int result=dao.addParameter(measureID, display, name);
			this.exist=false;
		}else{
			this.exist=true;
		}
		
		return "SUCCESS";
	}
	public String delete(){
		ParameterDao dao=new ParameterDao();
		if(!ids.isEmpty()){

			for(int id:ids){
				dao.deleteParameter(id);
			}
		}
		dao.close();
		
		return "SUCCESS";
	}
	public String update(){
		ParameterDao dao=new ParameterDao();
		int re=dao.updateParameter(ID, measureID, display, name);
		return "SUCCESS"; 
	}
}

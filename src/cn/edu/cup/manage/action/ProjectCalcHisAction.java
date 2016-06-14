package cn.edu.cup.manage.action;

import java.util.Date;
import java.util.List;

import cn.edu.cup.manage.business.CalcHisInput;
import cn.edu.cup.manage.business.CalcHisOutput;
import cn.edu.cup.manage.business.ProjectCalcHis;
import cn.edu.cup.manage.dao.AlgorithmProDao;
import cn.edu.cup.manage.dao.ProjectCalcHisDao;

public class ProjectCalcHisAction {
	int ID;
	int proID;
	
	
	
	List<ProjectCalcHis> dataList;
	List<CalcHisInput> inputList;
	List<CalcHisOutput> outputList;
	private int page;
	private int records;
	private int rows;
	private int rowNum;
	private int total;
	private String sidx;
	private String sord;
	private List<Integer> ids;
	
	public int getProID() {
		return proID;
	}
	public void setProID(int proID) {
		this.proID = proID;
	}


	public List<ProjectCalcHis> getDataList() {
		return dataList;
	}
	public void setDataList(List<ProjectCalcHis> dataList) {
		this.dataList = dataList;
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
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getRows() {
		return rows;
	}
	
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getRowNum() {
		return rowNum;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal() {
		return total;
	}
	
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSidx() {
		return sidx;
	}
	
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSord() {
		return sord;
	}
	
//	public String searchList(){
//		AlgorithmProDao dao=new AlgorithmProDao();
	//	dataList=dao.getAlgorithmProsList(page,rows,sidx,sord);
//		return "SUCCESS";

//	}
	public String list(){		

		ProjectCalcHisDao dao=new ProjectCalcHisDao();
		dataList=dao.getCalcHis(this.proID);
		
		return "SUCCESS";
	}
	public String getInputslist(){		

		ProjectCalcHisDao dao=new ProjectCalcHisDao();
		inputList=dao.getCalcInputsHis(this.ID);
		
		return "SUCCESS";
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getOutputslist(){		

		ProjectCalcHisDao dao=new ProjectCalcHisDao();
		outputList=dao.getCalcOutputsHis(this.ID);
		
		return "SUCCESS";
	}

	public List<CalcHisInput> getInputList() {
		return inputList;
	}
	public void setInputList(List<CalcHisInput> inputList) {
		this.inputList = inputList;
	}
	public List<CalcHisOutput> getOutputList() {
		return outputList;
	}
	public void setOutputList(List<CalcHisOutput> outputList) {
		this.outputList = outputList;
	}
	public String add(int pro_id,Date start){

		ProjectCalcHisDao dao=new ProjectCalcHisDao();
		dao.addCalcHis(pro_id,start);
		//int result=dao.addAlgorithmPro(this.Description,this.authorID,this.name);
		return "SUCCESS";
	}
	public String delete(){
		AlgorithmProDao dao=new AlgorithmProDao();
		//AlgorithmInputDao inputDao=new AlgorithmInputDao();
		if(!ids.isEmpty()){

			for(int id:ids){
				
				dao.deletePro(id);
			}
		}
		//inputDao.close();
		dao.close();
//		dao.deleteAlgorithm(ID);
		return "SUCCESS";
	}
	public String update(){
		AlgorithmProDao dao=new AlgorithmProDao();
		//int re=dao.updatePro(ID, this.name,this.Description);
		return "SUCCESS"; 
	}
	
}

package cn.edu.cup.manage.action;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cup.manage.business.AlgorithmInput;
import cn.edu.cup.manage.business.Parameters;
import cn.edu.cup.manage.dao.AlgorithmInputDao;
import cn.edu.cup.manage.dao.ParameterDao;

public class AlgorithmInputAction {
	List<AlgorithmInput> dataList;
	private List<Integer> ids;
	List<Parameters> paramList;
	List<AlgorithmInput> existInputList;
	boolean exist=false;
	public List<AlgorithmInput> getExistInputList() {
		return existInputList;
	}
	public void setExistInputList(List<AlgorithmInput> existInputList) {
		this.existInputList = existInputList;
	}
	public boolean isExist() {
		return exist;
	}
	
	public List<Parameters> getParamList() {
		return paramList;
	}
	public void setParamList(List<Parameters> paramList) {
		this.paramList = paramList;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	int ID;
	int CycleID;
	int ParamID;
	String symbol;
	String display;
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getDisplay() {
		return display;
	}
	public List<AlgorithmInput> getDataList() {
		return dataList;
	}


	public void setDataList(List<AlgorithmInput> dataList) {
		this.dataList = dataList;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getCycleID() {
		return CycleID;
	}


	public void setCycleID(int cycleID) {
		CycleID = cycleID;
	}


	public int getParamID() {
		return ParamID;
	}


	public void setParamID(int paramID) {
		ParamID = paramID;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getRecords() {
		return records;
	}


	public void setRecords(int records) {
		this.records = records;
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


	public void setTotal(int total) {
		this.total = total;
	}


	public String getSidx() {
		return sidx;
	}


	public void setSidx(String sidx) {
		this.sidx = sidx;
	}


	public String getSord() {
		return sord;
	}


	public void setSord(String sord) {
		this.sord = sord;
	}
	private int page;
	private int records;
	private int rows;
	private int rowNum;
	private int total;
	private String sidx;
	private String sord;

	
	public String list(){		

		AlgorithmInputDao dao=new AlgorithmInputDao();
		
		
		
		dataList=dao.getAlgorithmInputList(page,rows,sidx,sord,CycleID);
	
		records=dao.getCountAlgorithms();

		if(rows!=0&&records!=0){
			total=records/rows;
			if(records%rows!=0){
				total++;
			}
		}
		return "SUCCESS";
	}


	public String add(){

		AlgorithmInputDao dao=new AlgorithmInputDao();
		ParameterDao paraDao=new ParameterDao();
		
		if(!ids.isEmpty()){
			paramList=new ArrayList<Parameters>();
			for(int id:ids){
				int inputCount=dao.isExistAlgorithmInput(CycleID, id);
				if(inputCount==0){
					dao.addAlgorithm(this.CycleID,id);
					Parameters param=paraDao.searchParameter(id);
					paramList.add(param);
				}else{
					this.existInputList=dao.getExistAlgorithmInputList(CycleID,id);
					this.exist=true;
				}
			}
		}
		paraDao.close();
		dao.close();
		
//		int result=dao.addAlgorithm(this.CycleID,this.ParamID);
		return "SUCCESS";
	}
	public String delete(){
		AlgorithmInputDao dao=new AlgorithmInputDao();
		dao.deleteAlgorithm(ID);
		return "SUCCESS";
	}
	public String update(){
		AlgorithmInputDao dao=new AlgorithmInputDao();
		int re=dao.updateParameter(ID, this.CycleID,this.ParamID);
		return "SUCCESS"; 
	}
}

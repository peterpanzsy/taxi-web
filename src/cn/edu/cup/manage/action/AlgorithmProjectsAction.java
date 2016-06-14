package cn.edu.cup.manage.action;

import java.util.Date;
import java.util.List;

import cn.edu.cup.manage.business.AlgorithmJarPlug;
import cn.edu.cup.manage.business.AlgorithmPro;
import cn.edu.cup.manage.dao.AlgorithmProDao;
import cn.edu.cup.tools.JarTools;

public class AlgorithmProjectsAction {
	int ID;
	int inputID;
	int planID;
	int outputID;
	int authorID;
	String Description;
	Date addDate;
	Date lastUpdateDate;
	String name;
	List<AlgorithmPro> dataList;
	boolean exeSuccess;
	private int algID;
	private int page;
	private int records;
	private int rows;
	private int rowNum;
	private int total;
	private String sidx;
	private String sord;
	private List<Integer> ids;
	
	public void setExeSuccess(boolean exeSuccess) {
		this.exeSuccess = exeSuccess;
	}
	public boolean isExeSuccess() {
		return exeSuccess;
	}
	public void setAlgID(int algID) {
		this.algID = algID;
	}
	public int getAlgID() {
		return algID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getID() {
		return ID;
	}
	

	

	

	

	
	public void setDescription(String description) {
		Description = description;
	}
	public String getDescription() {
		return Description;
	}
	
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getAddDate() {
		return addDate;
	}
	
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	public void setDataList(List<AlgorithmPro> dataList) {
		this.dataList = dataList;
	}
	public List<AlgorithmPro> getDataList() {
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
	
	public String list(){		

		AlgorithmProDao dao=new AlgorithmProDao();
		
		
		
		dataList=dao.getAlgorithmProsList(page,rows,sidx,sord);
	
		records=dao.getCountAlgorithms();


		total=records/rows;
		if(records%rows!=0){
			total++;
		}
		return "SUCCESS";
	}


	public String add(){

		AlgorithmProDao dao=new AlgorithmProDao();
	
		int result=dao.addAlgorithmPro(this.Description,this.authorID,this.name);
		return "SUCCESS";
	}
	public int getInputID() {
		return inputID;
	}
	public void setInputID(int inputID) {
		this.inputID = inputID;
	}
	public int getPlanID() {
		return planID;
	}
	public void setPlanID(int planID) {
		this.planID = planID;
	}
	public int getOutputID() {
		return outputID;
	}
	public void setOutputID(int outputID) {
		this.outputID = outputID;
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public String getName() {
		return name;
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
		int re=dao.updatePro(ID, this.name,this.Description);
		return "SUCCESS"; 
	}
	public String searchProAlg(){
		AlgorithmProDao dao=new AlgorithmProDao();
		this.algID=dao.searchProAlg(this.ID);
		return "SUCCESS";
	}
	public String selectAlg(){
		AlgorithmProDao dao=new AlgorithmProDao();
		int re=dao.addAlgorithm(this.ID,this.algID);
		return "SUCCESS";
	}
	String className;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String runAlg(){
		AlgorithmProDao dao=new AlgorithmProDao();
		String algFile=dao.getAlgorithmFile(this.ID);
		String clsName=dao.getAlgorithmClass(this.ID);
		AlgorithmJarPlug alg=JarTools.getPlug(algFile,clsName, this.ID);
		if (alg!=null){
		Date start=new Date();
		try{
		alg.startCalc();
		alg.save();
		alg.saveHis(start);
		dao.updateProInfo(this.ID);
		dao.close();
		this.exeSuccess=true;
		}catch(Exception e){
			this.exeSuccess=false;
		}
		

		
	}
		return "SUCCESS"; 
}
}

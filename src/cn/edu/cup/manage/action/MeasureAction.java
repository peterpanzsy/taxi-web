package cn.edu.cup.manage.action;

import java.util.List;



import cn.edu.cup.manage.business.Measure;
import cn.edu.cup.manage.business.Style;
import cn.edu.cup.manage.dao.PhysicalDao;

import com.opensymphony.xwork2.ActionSupport;

public class MeasureAction extends ActionSupport{
	private int mid;
	String mCName;
	String mEName;
	String Symbol;
	String RatioA;
	String RatioB;
	String PhysicalID;
	String StyleID;
	String PCName;
	String StyleName;
	List<Measure> dataList;
	private int page;
	private int records;
	private int rows;
	private int rowNum;
	private int total;
	private String sidx;
	private String sord;
	private int id;
	
	public void setStyleName(String styleName) {
		StyleName = styleName;
	}
	public String getStyleName() {
		return StyleName;
	}
	public String getPCName() {
		return PCName;
	}
	public void setPCName(String pCName) {
		PCName = pCName;
	}
	private List<Integer> ids;
	
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public String getMEName() {
		return mEName;
	}

	public void setMEName(String MEName) {
		mEName = MEName;
	}

	public String getMCName() {
		return mCName;
	}

	public void setMCName(String MCName) {
		mCName = MCName;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

	public String getRatioA() {
		return RatioA;
	}

	public void setRatioA(String ratioA) {
		RatioA = ratioA;
	}

	public String getRatioB() {
		return RatioB;
	}

	public void setRatioB(String ratioB) {
		RatioB = ratioB;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int Mid) {
		mid = Mid;
	}

	public String getPhysicalID() {
		return PhysicalID;
	}

	public void setPhysicalID(String physicalID) {
		PhysicalID = physicalID;
	}

	public String getStyleID() {
		return StyleID;
	}

	public void setStyleID(String styleID) {
		StyleID = styleID;
	}

	public int getId() {
		return id;
	}

	public void setSidx(String sidx){
		this.sidx=sidx;
	}
	public String getSidx(String sidx){
		return sidx;
	}
	
	public void setSord(String sord){
		this.sord=sord;
	}
	public String getSord(String sord){
		return sord;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setId(int id) {
		this.id = id;
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

	public int getPage(){
		return page;
	}
	

	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Measure> getDataList() {
		return dataList;
	}

	public int getRecords() {
		return records;
	}


	public String execute(){
		
		return "SUCCESS";
	}
	public String add(){
		PhysicalDao dao=new PhysicalDao();
		int re=dao.addMessure(this.PhysicalID,this.mEName,this.mCName,this.Symbol,this.RatioA,this.RatioB,this.StyleID);
		return "SUCCESS";
	}
	public String list(){		
		PhysicalDao dao=new PhysicalDao();
		dataList=dao.getMessureList(page,rows,sidx,sord,mid,mCName,mEName,Symbol,RatioA,RatioB,PCName,StyleName);
		records=dao.getCountMessure(mid,mCName,mEName,Symbol,RatioA,RatioB,PCName,StyleName);
		if(records!=0&&rows!=0){
			total=records/rows;
			if(records%rows!=0){
				total++;
			}
		}
		return "SUCCESS";
	}
	

	public String delete(){	
		PhysicalDao dao=new PhysicalDao();
		if(!ids.isEmpty()){

			for(int id:ids)
				dao.deleteMessure(id);
		}
		dao.commit();
		return "SUCCESS";
	}
		
	public String update(){
		PhysicalDao dao=new PhysicalDao();
		int re=dao.updateMessure(this.mid,this.PhysicalID,this.mEName,this.mCName,this.Symbol,this.RatioA,this.RatioB,this.StyleID);
	
		return "SUCCESS"; 
	}
	
	List<Style> styleList;
	public List<Style> getStyleList() {
		return styleList;
	}
	public void setStyleList(List<Style> styleList) {
		this.styleList = styleList;
	}
	public String listPhysicalStyle(){
		PhysicalDao dao=new PhysicalDao();
		styleList=dao.getPhysicalStyleList();
		return "SUCCESS";
	}
	
}

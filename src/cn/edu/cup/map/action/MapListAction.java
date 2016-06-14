package cn.edu.cup.map.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.cup.map.business.FileToGraphi;
import cn.edu.cup.map.business.Graphi;
import cn.edu.cup.map.business.MapPro;
import cn.edu.cup.map.dao.MapDao;

import com.opensymphony.xwork2.ActionSupport;


public class MapListAction extends ActionSupport{
	public String filePath;
	public String proName;
	List<MapPro> dataList;
	private int page;
	private int records;
	private int rows;
	private int rowNum;
	private int total;
	private String sidx;
	private String sord;
	
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
	private Graphi graphi;
	String re;
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}



	
	public String getRe() {
		return re;
	}

	public String delete(){
		MapDao dao=new MapDao();
		int reint=dao.deleteMap(id);
		if(reint!=1){
			re="删除失败，此地图记录已不存在";
		}else{
			re="删除成功";
		}
		return "SUCCESS";
	}
	public String add(){
		MapDao dao=new MapDao();
		FileToGraphi a=new FileToGraphi();
		graphi=a.FileTrans(filePath);
		dao.addPro(proName, filePath,graphi);
		

		return "SUCCESS";
	}
	

	public void setPage(int page) {
		this.page = page;
	}
	private int id;
	public void setId(int id) {
		this.id = id;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}


	public String list(){		
		MapDao dao=new MapDao();
		dataList=dao.list(page,rows,sidx,sord);
		
		
		records=dao.getMax();
		total=10;
		for(int i=0;i<dataList.size();i++){
			MapPro m=dataList.get(i);
			String fp=m.getFilePath();
			
			m.setFilePath(changePath(fp));
		}
		total=records/rows;
		if(records%rows!=0){
			total++;
		}
		return "SUCCESS";
	}
	private String changePath(String AbsolutePath){
		String WebPath="";
		int start=AbsolutePath.indexOf(UploadAction.UPLOADPATH);
		if(start>0){
		WebPath=AbsolutePath.substring(start);}
		WebPath=WebPath.replace("\\", "/");
		System.out.println(WebPath);
		return WebPath;
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

	public List<MapPro> getDataList() {
		return dataList;
	}

	public int getRecords() {
		return records;
	}

	public Graphi getGraphi() {
		return graphi;
	}
	
	public String execute(){
		
		
		return "SUCCESS";
	}
	

	
	public String viewMap(){
		FileToGraphi a=new FileToGraphi();
		MapDao dao=new MapDao();
		//filePath=dao.getFilePath(id);
		//graphi=a.FileTrans(filePath);
		graphi=dao.getGraphiByID(id);
		return "SUCCESS";
	}


	

	
}

package cn.edu.cup.map.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.edu.cup.map.business.Papoint;
import cn.edu.cup.map.business.Tapoint;
import cn.edu.cup.map.business.TaxiPoint;
import cn.edu.cup.map.business.TaxiPointDropPick;
import cn.edu.cup.map.dao.MapDao;
import cn.edu.cup.map.dao.TaxiDao;

import com.opensymphony.xwork2.ActionSupport;


public class TaxiListAction extends ActionSupport{
	List<TaxiPoint> dataList=new ArrayList<TaxiPoint>();
	List<TaxiPointDropPick> taxiAddrCouList=new ArrayList<TaxiPointDropPick>();
	List<Tapoint> tapointsList = new ArrayList<Tapoint>();
	List<Papoint> papointsList = new ArrayList<Papoint>();
	String type;
	
	public List<Papoint> getPapointsList() {
		return papointsList;
	}
	public void setPapointsList(List<Papoint> papointsList) {
		this.papointsList = papointsList;
	}
	
	public List<Tapoint> getTapointsList() {
		return tapointsList;
	}
	public void setTapointsList(List<Tapoint> tapointsList) {
		this.tapointsList = tapointsList;
	}
	public List<TaxiPointDropPick> getTaxiAddrCouList() {
		return taxiAddrCouList;
	}
	public void setTaxiAddrCouList(List<TaxiPointDropPick> taxiAddrCouList) {
		this.taxiAddrCouList = taxiAddrCouList;
	}
	public List<TaxiPoint> getDataList() {
		return dataList;
	}
	public void setDataList(List<TaxiPoint> dataList) {
		this.dataList = dataList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String list(){		
		TaxiDao dao=new TaxiDao();
		dataList=dao.list(type);
		
		return "SUCCESS";
	}
	public String listCamera(){
		TaxiDao dao=new TaxiDao();
		taxiAddrCouList=dao.listCamera();
		
		return "SUCCESS";
	}
	

	public String listtapoints(){		
		TaxiDao dao=new TaxiDao();
		tapointsList=dao.listTapoints();
		
		return "SUCCESS";
	}
	

	public String listpapoints(){		
		TaxiDao dao=new TaxiDao();
		papointsList=dao.listPapoints();
		
		return "SUCCESS";
	}

	

	
}

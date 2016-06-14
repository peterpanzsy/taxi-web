package cn.edu.cup.map.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import cn.edu.cup.map.business.Point;

import com.opensymphony.xwork2.ActionSupport;

public class MapAction extends ActionSupport{
	List<Point> points;
	
	public List<Point> getPoints() {
		return points;
	}
	public int testinit(){
		points=new ArrayList<Point>();
		points.add(new Point(642150.4345, 3947632.937, "PH66-04", "PH1-002"));
		points.add(new Point(642370.2253, 3947415.113, "PH66-02", "PH1-002"));
		points.add(new Point(642598.6307, 3947479.802, "PH66-05", "PH1-002"));
		points.add(new Point(642877.5666, 3947678.192, "PH66-08", "PH1-002"));
		points.add(new Point(642735.4336, 3947269.037, "PH66-03", "PH1-002"));
		points.add(new Point(642497.1578, 3947153.18,  "PH56-10", "PH1-002"));
		points.add(new Point(642883.1592, 3947125.296, "PH56-11", "PH1-002"));
		points.add(new Point(642696.1411, 3946827.752, "PH56-09", "PH1-002"));
		points.add(new Point(642430.1129, 3946669.858, "PH56-06", "PH1-002"));
		points.add(new Point(642176.8753, 3946598.227, "PH56-03", "PH1-002"));
		points.add(new Point(642192.5931, 3946984.241, "PH56-08", "PH1-002"));
		points.add(new Point(642111.7407, 3947240.585, "PH66-01", "PH1-002"));
		points.add(new Point(642572.4831, 3946415.014, "PH56-04", "PH1-002"));
		points.add(new Point(643826.9864, 3948658.53,  "PH77-16", "PH1-002"));
		points.add(new Point(644008.5306, 3949266.397, "PH88-02", "PH1-002"));
		points.add(new Point(644586.7507, 3949695.279, "PH88-04", "PH1-002"));
		points.add(new Point(644458.4138, 3949409.207, "PH88-03", "PH1-002"));
		points.add(new Point(644135.4246, 3949632.468, "PH88-07", "PH1-002"));
		
		return points.size();
	}
	JSONArray jsonArray2;
	public JSONArray getJsonArray2() {
		return jsonArray2;
	}
	@Override
	public String execute(){
		testinit();
		System.out.println(points.get(1).getLatitude());
		
		return "SUCCESS";
	}
	

	public static void main(String args[]){
		MapAction a=new MapAction();
		a.testinit();
		a.execute();
	}
}

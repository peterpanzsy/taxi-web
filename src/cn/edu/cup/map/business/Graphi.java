package cn.edu.cup.map.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.cup.map.business.Point.Type;

public class Graphi {
	Map<String,Point> points;
	List<Line> lines;
	public List getJSON(){
		List temp=new ArrayList();
		temp.addAll(points.values());
		temp.addAll(lines);
		return temp;
	}
	public void setPoints(Map<String, Point> points) {
		this.points = points;
	}
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
	public Map<String, Point> getPoints() {
		return points;
	}
	public List<Line> getLines() {
		return lines;
	}
	public Graphi(){
		lines=new ArrayList<Line>();
		points=new HashMap<String, Point>();

	}
	public Boolean addPoint(Point temp){
		if(!points.containsValue(temp)){
			points.put(temp.getName(), temp);			
			return true;
		}
		return false;
	}
	public Boolean addLine(String start,String end){
		if(points.containsKey(start)&&points.containsKey(end)){
			Point temp=points.get(start);
			if(temp.type==Type.jingkou){
				lines.add(new Line(start,end,1));
			}else{
				lines.add(new Line(start,end,2));
			}
			return true;
		}
		return false;
	}

}

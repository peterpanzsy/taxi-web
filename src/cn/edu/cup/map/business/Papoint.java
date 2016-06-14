package cn.edu.cup.map.business;

public class Papoint {
	String road;
	double lon;
	double lat;
	double pa;
	
	public Papoint(String road, double lon, double lat, double pa) {
		super();
		this.road = road;
		this.lon = lon;
		this.lat = lat;
		this.pa = pa;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getPa() {
		return pa;
	}
	public void setPa(double pa) {
		this.pa = pa;
	}
	

}

package cn.edu.cup.map.business;

public class Tapoint {
	String road;
	double lon;
	double lat;
	int ta;
	
	public Tapoint(String road, double lon, double lat, int ta) {
		super();
		this.road = road;
		this.lon = lon;
		this.lat = lat;
		this.ta = ta;
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
	public int getTa() {
		return ta;
	}
	public void setTa(int ta) {
		this.ta = ta;
	}
	

}

package cn.edu.cup.map.business;

public class TaxiPoint {
	private double longitude;
	private double latitude;
	private String type;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public TaxiPoint(double longitude,double latitude,String type){
		this.latitude=latitude;
		this.longitude=longitude;
		this.type=type;
	}
}

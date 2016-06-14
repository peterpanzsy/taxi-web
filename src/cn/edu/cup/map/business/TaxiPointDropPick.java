package cn.edu.cup.map.business;

public class TaxiPointDropPick {
	private double lng;
	private double lat;
	private int count;

	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public TaxiPointDropPick(double longitude,double latitude,int count){
		this.lat=latitude;
		this.lng=longitude;
		this.count=count;
	}
}

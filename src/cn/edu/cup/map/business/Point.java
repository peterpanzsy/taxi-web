package cn.edu.cup.map.business;

public class Point {
	String WellNO;
	String Name;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	Type type;
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	public enum Type {
        jingkou, jiqizhan,fazu;    
    }
	public static Type getType(String name){
		if (name.equalsIgnoreCase("jingkou")){
			return Type.jingkou;
		}
		if (name.equalsIgnoreCase("fazu")){
			return Type.fazu;
		}
		if (name.equalsIgnoreCase("jiqizhan")){
			return Type.jiqizhan;
		}
		return null;
	}
	public String getWellNO() {
		return WellNO;
	}
	public void setWellNO(String wellNO) {
		WellNO = wellNO;
	}
	public String getGroupNO() {
		return GroupNO;
	}
	public void setGroupNO(String groupNO) {
		GroupNO = groupNO;
	}
	public double getGeodeticCoordinatesX() {
		return GeodeticCoordinatesX;
	}
	public void setGeodeticCoordinatesX(double geodeticCoordinatesX) {
		GeodeticCoordinatesX = geodeticCoordinatesX;
	}
	public double getGeodeticCoordinatesY() {
		return GeodeticCoordinatesY;
	}
	public void setGeodeticCoordinatesY(double geodeticCoordinatesY) {
		GeodeticCoordinatesY = geodeticCoordinatesY;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	String  GroupNO;
	/**
	 * da di zuo biao 
	 */
	double GeodeticCoordinatesX;
	/**
	 * da di zuo biao 
	 */
	double GeodeticCoordinatesY;
	
	/**
	 * 经度
	 */
	double Longitude;
	/**
	 * 纬度
	 */
	double latitude;
	public Point(double d,double e,String wellNO,String groupNO){
		this.GeodeticCoordinatesX=d;
		this.GeodeticCoordinatesY=e;
		this.WellNO=wellNO;
		this.GroupNO=groupNO;
		this.getLatLonFromGeo();
	}
	public Point() {
		// TODO Auto-generated constructor stub
	}
	public void getLatLonFromGeo(){
		double a[]=new CoordinateConversion().utm2LatLon("50 S "+String.valueOf(this.GeodeticCoordinatesX)+" "+String.valueOf(this.GeodeticCoordinatesY));
		this.latitude=a[0];
		this.Longitude=a[1];
	}
	public void setTypeByInt(String trim) {
		// TODO Auto-generated method stub
		if(trim.equalsIgnoreCase("1")){
			this.type=Type.jingkou;		}
		
		
	if(trim.equalsIgnoreCase("2")){
		this.type=Type.fazu;		
	
	}if(trim.equalsIgnoreCase("3")){
		this.type=Type.jiqizhan;		}
	
	}
}

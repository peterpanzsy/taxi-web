package cn.edu.cup.map.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import cn.edu.cup.map.business.Graphi;
import cn.edu.cup.map.business.Line;
import cn.edu.cup.map.business.MapPro;
import cn.edu.cup.map.business.Papoint;
import cn.edu.cup.map.business.Point;
import cn.edu.cup.map.business.Tapoint;
import cn.edu.cup.map.business.TaxiPoint;
import cn.edu.cup.map.business.TaxiPointDropPick;
import cn.edu.cup.test.TestHibernate;

public class TaxiDao {
	
	public List<TaxiPointDropPick> listCamera() {
		String sql;
		SQLQuery q;
		List re=new ArrayList<TaxiPointDropPick>();

		sql="select t.LONGITUDE,t.LATITUDE,t.count from taxi_camera t";
		q = session.createSQLQuery(sql);
		List l = q.list();
		
		for(int i=0;i<l.size();i++)
		{
			  Object[] row = (Object[])l.get(i);;
			  double LONGITUDE = Double.parseDouble(row[0].toString());
			  double LATITUDE = Double.parseDouble(row[1].toString());  
			  int count=(Integer) row[2];					
			  TaxiPointDropPick p=new TaxiPointDropPick(LONGITUDE, LATITUDE, count);
			  re.add(p);
		}
		return re;
	}
	
	public List<Tapoint> listTapoints() {
		String sql;
		SQLQuery q;
		List re=new ArrayList<Tapoint>();

		sql="select t.road,t.lon,t.lat,t.ta from ta_points_copy t";
		q = session.createSQLQuery(sql);
		List l = q.list();
		
		for(int i=0;i<l.size();i++)
		{
			  Object[] row = (Object[])l.get(i);
			  String road = row[0].toString();
			  double LONGITUDE = Double.parseDouble(row[1].toString());
			  double LATITUDE = Double.parseDouble(row[2].toString());  
			  int count=(Integer) row[3];					
			  Tapoint p=new Tapoint(road,LONGITUDE, LATITUDE, count);
			  re.add(p);
		}
		return re;
	}
	
	
	public List<Papoint> listPapoints() {
		String sql;
		SQLQuery q;
		List re=new ArrayList<Papoint>();

		sql="select t.road,t.lon,t.lat,t.pa from pa_points t";
		q = session.createSQLQuery(sql);
		List l = q.list();
		
		for(int i=0;i<l.size();i++)
		{
			  Object[] row = (Object[])l.get(i);
			  String road = row[0].toString();
			  double LONGITUDE = Double.parseDouble(row[1].toString());
			  double LATITUDE = Double.parseDouble(row[2].toString());  
			  double Pa = Double.parseDouble(row[3].toString());  				
			  Papoint p=new Papoint(road,LONGITUDE, LATITUDE, Pa);
			  re.add(p);
		}
		return re;
	}
	
	public List<TaxiPoint> list(String type) {
		String sql;
		SQLQuery q;
		List re=new ArrayList<TaxiPoint>();
		if(type.equals("easy_take_top")){
			sql="select t.lon_min,t.lat_min from t_off_on_ratio_down100_filter t";
			q = session.createSQLQuery(sql);
			List l = q.list();
			
			for(int i=0;i<l.size();i++)
			{
				  Object[] row = (Object[])l.get(i);;
				  double LONGITUDE = Double.parseDouble(row[0].toString());
				  double LATITUDE = Double.parseDouble(row[1].toString());  
				  int count=1;					
				  TaxiPointDropPick p=new TaxiPointDropPick(LONGITUDE, LATITUDE, count);
				  re.add(p);
			}
		}else if(type.equals("shift_top")){
			sql="select t.lon_min,t.lat_min from t_shift_place t";
			q = session.createSQLQuery(sql);
			List l = q.list();
			
			for(int i=0;i<l.size();i++)
			{
				  Object[] row = (Object[])l.get(i);;
				  double LONGITUDE = Double.parseDouble(row[0].toString());
				  double LATITUDE = Double.parseDouble(row[1].toString());  
				  int count=1;					
				  TaxiPointDropPick p=new TaxiPointDropPick(LONGITUDE, LATITUDE, count);
				  re.add(p);
			}
		}else if(type.equals("dropoff")){
			sql="select t.LONGITUDE,t.LATITUDE,t.type from taxi_dropoff0102 t";
			q = session.createSQLQuery(sql);
			List l = q.list();
			
			for(int i=0;i<l.size();i++)
			{
				  Object[] row = (Object[])l.get(i);;
				  double LONGITUDE = Double.parseDouble(row[0].toString());
				  double LATITUDE = Double.parseDouble(row[1].toString());  
				  int count=1;					
				  TaxiPointDropPick p=new TaxiPointDropPick(LONGITUDE, LATITUDE, count);
				  re.add(p);
			}
			
		}else if(type.equals("pick")){
			sql="select t.LONGITUDE,t.LATITUDE,t.type from taxi_pick0102 t";
			q = session.createSQLQuery(sql);
			List l = q.list();
			
			for(int i=0;i<l.size();i++)
			{
				  Object[] row = (Object[])l.get(i);;
				  double LONGITUDE = Double.parseDouble(row[0].toString());
				  double LATITUDE = Double.parseDouble(row[1].toString());  
				  int count=1;				
				  TaxiPointDropPick p=new TaxiPointDropPick(LONGITUDE, LATITUDE, count);
				  re.add(p);
			}
		}else if(type.equals("陕AT7009")||type.equals("陕AU4815")){
			sql="select t.LONGITUDE,t.LATITUDE,t.type from taxi_addr t where t.type=?";
//			sql="select t.LONGITUDE,t.LATITUDE,'' from taxi_camera t";
			q = session.createSQLQuery(sql);
			q.setParameter(0, type);
			List l = q.list();
			
			for(int i=0;i<l.size();i++)
			{
				//TestDb user = (TestDb)l.get(i);
				//System.out.println(user.getUsername());

				  Object[] row = (Object[])l.get(i);;
				  double LONGITUDE = Double.parseDouble(row[0].toString());
				  double LATITUDE = Double.parseDouble(row[1].toString());  
				  String TYPE =(String)row[2];
				
				  TaxiPoint p=new TaxiPoint(LONGITUDE, LATITUDE, type);
				  re.add(p);
			}
		}else{
			return null;
		}
		
	
		
		return re;
	}
	
	
	//遍历
	public  void all()
	{
		Query q = session.createSQLQuery("select id,name from Test");
		
		List l = q.list();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);;
			  Integer id = (Integer)row[0];
			  String name = (String)row[1];  
			  System.out.println(id+" "+name);
		}
	}
	
	//读取
	public  void load()
	{
		TestHibernate obj = (TestHibernate) session.load(TestHibernate.class, new Long(2));
		
			
		
		//System.out.println(obj.getUsername());
	}
	public int addPro(String ProName,String FilePath, Graphi graphi){
		
		Query q = session.createSQLQuery("insert into T_MapPro (ProName,FilePath,Adddate) values (?,?,now())");
		q.setParameter(0, ProName);
		q.setParameter(1, FilePath);
		int result=q.executeUpdate();
		SQLQuery sqlq = session.createSQLQuery("select max(ID) from T_MapPro");

		Integer proid=((Integer)sqlq.uniqueResult()).intValue();
		
		addPoint(proid,graphi);
		addLine(proid,graphi);
		tx.commit();
		return result;
	}
	private void addLine(int proid,Graphi graphi) {
		List<Line> lines=graphi.getLines();
		for(Iterator<Line> iter=lines.iterator();iter.hasNext();){
			Line line=iter.next();
			Query q = session.createSQLQuery("insert into T_MapLine values (?,?,?,?)");
			q.setParameter(0, proid);
			q.setParameter(1, line.getStart());
			q.setParameter(2, line.getEnd());
			q.setParameter(3, line.getType());

			q.executeUpdate();
		
		}
		
	}

	private void addPoint(int proid,Graphi graphi) {
		Map<String, Point> map=graphi.getPoints();
		Collection<Point> points=map.values();
		for(Iterator<Point> iter=points.iterator();iter.hasNext();){
			Point p=iter.next();
			Query q = session.createSQLQuery("insert into T_MapPoint values (?,?,?,?,?,?,?)");
			q.setParameter(0, proid);
			q.setParameter(1, p.getName());
			q.setParameter(2, p.getType().toString());
			q.setParameter(3, p.getGeodeticCoordinatesX());
			q.setParameter(4, p.getGeodeticCoordinatesY());
			q.setParameter(5, p.getLatitude());
			q.setParameter(6, p.getLongitude());
			q.executeUpdate();
		
		}
	
	}

	//更新
	public  void update()
	{
		TestHibernate obj = (TestHibernate) session.load(TestHibernate.class, new Long(2));
		//obj.setUsername("cg");
	}
	
	//插入
	public  void insert()
	{
		TestHibernate user = new TestHibernate();
		//user.setUsername("sb");

		session.save(user);
	}
	
	 SessionFactory sessionFactory;
	 Session session ;
	 Transaction tx ;
	

	public TaxiDao()
	{	
		Configuration cfg = new Configuration();  
        cfg.configure();          
        ServiceRegistry  sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();           
        SessionFactory sessionFactory = cfg.buildSessionFactory(sr);  
                  
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	
	}
	
	private  void close()
	{
		tx.commit();
		session.close();
		//sessionFactory.close();
	}
	public int getMax(){
		SQLQuery q = session.createSQLQuery("select count(*) from t_MapPro");
		Integer a=((BigInteger)q.uniqueResult()).intValue();
		return a;
	}
	public String getFilePath(int id){
		SQLQuery q = session.createSQLQuery("select FilePath from t_MapPro where ID=?");
		q.setParameter(0, id);
		String a=((String)q.uniqueResult());
		
		return a;
	}
	public Graphi getGraphiByID(int id){
		Graphi a=new Graphi();
		a.setLines(getLinesByID(id));
		a.setPoints(getPointsByID(id));
		
		return a;
	}
	public List<Line> getLinesByID(int id){
		List<Line> lines=new ArrayList<Line>();
		SQLQuery q = session.createSQLQuery("select Start,End,Type from T_MapLine where ProID=?");
		q.setParameter(0, id);
		List l = q.list();
		Line temp;
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());
			  temp=new Line();
			  Object[] row = (Object[])l.get(i);;
			  temp.setStart((String)row[0]);
			  temp.setEnd((String)row[1]);
			  temp.setType((Integer)row[2]);
			  lines.add(temp);
		}
		
		
		return lines;
		
	}
	public Map<String,Point> getPointsByID(int id){
		Map<String,Point> points=new HashMap<String, Point>();
		SQLQuery q = session.createSQLQuery("select PointName,Type,GeodeticCoordinatesX,GeodeticCoordinatesY,Latitude,Longitude from t_MapPoint where ProID=?");
		q.setParameter(0, id);
		List l = q.list();
		Point temp;
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());
			  temp=new Point();
			  Object[] row = (Object[])l.get(i);;
			  String proName=(String)row[0];
			  temp.setName(proName);
			  temp.setType(Point.getType((String)row[1]));
			  temp.setGeodeticCoordinatesX((Double)row[2]);
			  temp.setGeodeticCoordinatesY((Double)row[3]);
			  temp.setLatitude((Double)row[4]);
			  temp.setLongitude((Double)row[5]);

			  points.put(proName, temp);
		}
		
		
		
		return points;
	}

	public int deleteMap(int id) {
		SQLQuery q = session.createSQLQuery("delete from t_MapPro where ID=?");
		q.setParameter(0, id);
		int re=q.executeUpdate();
		tx.commit();
		return re;
		
	}
}

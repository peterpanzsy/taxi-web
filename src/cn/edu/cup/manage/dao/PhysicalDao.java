package cn.edu.cup.manage.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import cn.edu.cup.manage.business.Measure;
import cn.edu.cup.manage.business.Physical;
import cn.edu.cup.manage.business.Style;
import cn.edu.cup.test.TestHibernate;

public class PhysicalDao {


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
	public int addPhysical(String CName,String EName, String Description,String ISOBasicUnit){
		
		Query q = session.createSQLQuery("insert into T_Physical (CName,EName,Description,ISOBasicUnit) values (?,?,?,?)");
		q.setParameter(0, CName);
		q.setParameter(1, EName);
		q.setParameter(2, Description);
		q.setParameter(3, ISOBasicUnit);
		int result=q.executeUpdate();
		
		tx.commit();
		return result;
	}
	
	
	
	
	
	
	public int addMessure(String PhysicalID,String EName,String CName,String Symbol,String RatioA,String RatioB,String styleID) {
		Query q = session.createSQLQuery("insert into T_Measure (PhysicalID,EName,CName,Symbol,RatioA,RatioB,StyleID) values (?,?,?,?,?,?,?)");
		q.setParameter(0, PhysicalID);
		q.setParameter(1, EName);
		q.setParameter(2, CName);
		q.setParameter(3, Symbol);
		q.setParameter(4, RatioA);
		q.setParameter(5, RatioB);
		q.setParameter(6, styleID);
		int result=q.executeUpdate();
		
		tx.commit();
		return result;
		
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
	

	public PhysicalDao()
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
	public void commit(){
		tx.commit();
		session.close();
	}
	public int getCountMessure(int mid,String CName,String EName,String Symbol,String RatioA,String RatioB,String PCName,String StyleName){
		 StringBuilder where = new StringBuilder();
		 where.append(" where t1.PhysicalID=t2.ID and t1.StyleID=t3.StyleID ");
			if(mid!=0){
				if(where.length()!=0)
					where.append(" and ");
				else
					where.append(" where ");
				where.append("t1.ID=").append(mid);
			}
			_joint_string_sql(where,"t1","CName",CName);
			_joint_string_sql(where,"t1","EName",EName);
			_joint_string_sql(where,"t1","Symbol",Symbol);
			_joint_string_sql(where,"t1","RatioA",RatioA);
			_joint_string_sql(where,"t1","RatioB",RatioB);
			_joint_string_sql(where,"t2","CName",PCName);
			_joint_string_sql(where,"t3","StyleName",StyleName);
		String sql="select count(*) from T_Measure t1,T_Physical t2,T_PhysicalStyle t3 "+where.toString();
		SQLQuery q = session.createSQLQuery(sql);
		Integer a=((BigInteger)q.uniqueResult()).intValue();
		return a;
	}
	
	public int getCountPhysical(int id, String cName, String eName, String description, String iSOBasicUnit){
		 StringBuilder where = new StringBuilder();
			if(id!=0){
				if(where.length()!=0)
					where.append(" or ");
				else
					where.append(" where ");
				where.append("t2.ID=").append(id);
			}
			_joint_string_sql(where,"t2","CName",cName);
			_joint_string_sql(where,"t2","EName",eName);
			_joint_string_sql(where,"t2","Description",description);
			_joint_string_sql(where,"t2","iSOBasicUnit",iSOBasicUnit);
		String sql="select count(*) from T_Physical t2 "+where.toString();
		SQLQuery q = session.createSQLQuery(sql);
		Integer count=((BigInteger)q.uniqueResult()).intValue();
		return count;
	}

	public List<Measure> getMessureList(int page,int rows,String sidx,String sord,int mid,String CName,String EName,String Symbol,String RatioA,String RatioB,String PCName,String StyleName) {
		 StringBuilder where = new StringBuilder();
		 where.append(" where t1.PhysicalID=t2.ID and t1.StyleID=t3.StyleID ");
		if(mid!=0){
			if(where.length()!=0)
				where.append(" and ");
			else
				where.append(" where ");
			where.append("t1.ID=").append(mid);
		}
		_joint_string_sql(where,"t1","CName",CName);
		_joint_string_sql(where,"t1","EName",EName);
		_joint_string_sql(where,"t1","Symbol",Symbol);
		_joint_string_sql(where,"t1","RatioA",RatioA);
		_joint_string_sql(where,"t1","RatioB",RatioB);
		_joint_string_sql(where,"t2","CName",PCName);
		_joint_string_sql(where,"t3","StyleName",StyleName);
		
		SQLQuery q = session.createSQLQuery("select t1.ID mid,t1.CName mCName,t1.EName mEName,t1.RatioA ratioA,t1.RatioB ratioB,t1.Symbol symbol,t2.ID pid,t2.CName pCName,t2.EName pEName,t2.Description description,t2.ISOBasicUnit ISOBasicUnit,t3.StyleID sid,t3.StyleName styleName from T_Measure t1,T_Physical t2,T_PhysicalStyle t3 "+where.toString()+" order by "+sidx+" "+sord);

		q.setFirstResult((page-1)*rows);
		q.setMaxResults(rows);
		List l = q.list();
		List<Measure> re=new ArrayList<Measure>();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);;
			  Integer id = (Integer)row[0];
			  String mCName = (String)row[1];  
			  String mEName = (String)row[2];  
			  Double ra=(Double)row[3];
			  Double rb=(Double)row[4];
			  String mSymbol = (String)row[5]; 
			  Integer pid = (Integer)row[6];
			  String pCName = (String)row[7];  
			  String pEName = (String)row[8]; 
			  String Descrip = (String)row[9];  
			  String punit = (String)row[10]; 
			  
			  Integer sid = (Integer)row[11];
			  String sName = (String)row[12];  
			  
			  Physical phy=new Physical(pid, pCName, pEName, Descrip, punit);
			  Style style=new Style(sid, sName);
			  Measure m=new Measure(id, pid, phy, style, mEName, mCName, mSymbol, ra, rb, sid);
			  
			  
			  re.add(m);
		}
		return re;
	}

	public List<Physical> getPhysicalList(int page,int rows,String sidx,String sord, int id, String cName, String eName, String description, String iSOBasicUnit) {

		 StringBuilder where = new StringBuilder();
		if(id!=0){
			if(where.length()!=0)
				where.append(" and ");
			else
				where.append(" where ");
			where.append("t2.ID=").append(id);
		}
		_joint_string_sql(where,"t2","CName",cName);
		_joint_string_sql(where,"t2","EName",eName);
		_joint_string_sql(where,"t2","Description",description);
		_joint_string_sql(where,"t2","iSOBasicUnit",iSOBasicUnit);
		/*
		if(cName!=null&&!cName.isEmpty()){
			if(where.length()!=0)
				where.append(" or ");
			else
				where.append(" where ");
			where.append(" t2.CName=\"").append(cName).append("\"");
		}
		if(eName!=null&&!eName.isEmpty()){
			if(where.length()!=0)
				where.append(" or ");
			else
				where.append(" where ");
			where.append("t2.EName=\"").append(eName).append("\"");
		}
		if(description!=null&&!description.isEmpty()){
			if(where.length()!=0)
				where.append(" or ");
			else
				where.append(" where ");
			where.append("t2.Description=\"").append(description).append("\"");
		}
		if(iSOBasicUnit!=null&&!iSOBasicUnit.isEmpty()){
			if(where.length()!=0)
				where.append(" or ");
			else
				where.append(" where ");
			where.append("t2.ISOBasicUnit=\"").append(iSOBasicUnit).append("\"");
		}*/
		System.out.print(where);
		String sql="select t2.ID,t2.CName,t2.EName,t2.Description,t2.ISOBasicUnit from T_Physical t2 "+where.toString()+" order by t2."+sidx+" "+sord;
		SQLQuery q = session.createSQLQuery(sql);
		q.setFirstResult((page-1)*rows);
		q.setMaxResults(rows);
		List l = q.list();
		List<Physical> re=new ArrayList<Physical>();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);
			  
			  Integer pid = (Integer)row[0];
			  String pCName = (String)row[1];  
			  String pEName = (String)row[2]; 
			  String Descrip = (String)row[3];  
			  String punit = (String)row[4]; 
			  
			  
			  
			  Physical phy=new Physical(pid, pCName, pEName, Descrip, punit);
			  
			  
			  re.add(phy);
		}
		
		return re;
	}

	public List<Style> getPhysicalStyleList() {
		SQLQuery q = session.createSQLQuery("SELECT t.StyleID,t.StyleName FROM t_physicalstyle t");
		List l = q.list();
		List<Style> re=new ArrayList<Style>();
		for(int i=0;i<l.size();i++)
		{
			  Object[] row = (Object[])l.get(i);;
			  Integer sid = (Integer)row[0];
			  String sName = (String)row[1];  
			  Style style=new Style(sid, sName);	 
			  re.add(style);
		}
		return re;
	}
	
	public int deleteMessure(int  id) {
		SQLQuery q = session.createSQLQuery("delete from T_Measure where ID=?");
		q.setParameter(0, id);
		int re=q.executeUpdate();
		return re;
		
	}
	public int deletePhysical(int id) {
		SQLQuery q = session.createSQLQuery("delete from T_Physical where ID=?");
		q.setParameter(0, id);
		int re=q.executeUpdate();
		return re;
		
	}

	public int updatePhysical(String iD, String cName, String eName,
			String description, String iSOBasicUnit) {
		// TODO Auto-generated method stub
		SQLQuery q = session.createSQLQuery("update T_Physical t set EName=?,CName=?,description=?,iSOBasicUnit=? where t.ID=?");
		q.setParameter(0, eName);
		q.setParameter(1, cName);
		q.setParameter(2, description);
		q.setParameter(3, iSOBasicUnit);
		q.setParameter(4, iD);
		int re=q.executeUpdate();
		tx.commit();
		return re;
	}

	public int updateMessure(int id, String physicalID, String eName,
			String cName, String symbol, String ratioA, String ratioB,
			String styleID) {
		// TODO Auto-generated method stub
		SQLQuery q = session.createSQLQuery("update T_Measure set physicalID=?,eName=?,cName=?,symbol=?,ratioA=?,ratioB=?,styleID=? where id=?");
		q.setParameter(0, physicalID);
		q.setParameter(1, eName);
		q.setParameter(2, cName);
		q.setParameter(3, symbol);
		q.setParameter(4, ratioA);
		q.setParameter(0, ratioB);
		q.setParameter(1, styleID);
		q.setParameter(2, id);
		int re=q.executeUpdate();
		tx.commit();
		return re;
		
	}
	
	/*
	 * sql的where条件的字符串拼接，用于条件查询,判断字段是否为空，不为空则加入条件
	 */
	private StringBuilder _joint_string_sql(StringBuilder where,String tableName,String fieldName,String param ){
		if(param!=null&&!param.isEmpty()){
			if(where.length()!=0)
				where.append(" and ");
			else
				where.append(" where ");
			param.replaceAll(".*([';]+|(--)+).*\\/\"", " ");//特殊字符的过滤，防注入
			where.append(tableName).append(".").append(fieldName).append(" like '%").append(param).append("%'");
			System.out.println(where);
		}
		return where;
	}
	
	public double getISOValue(int messid,double value){
		Measure m=getMess(messid);
		double ISOvalue=m.getRatioA()*value+m.getRatioB();
		return ISOvalue;
	}
	public double getMessValue(int messid,double value){
		Measure m=getMess(messid);
		double Messvalue=(value-m.getRatioB())/m.getRatioA();
		return Messvalue;
	}
	public Measure getMess(int messid){
		SQLQuery q = session.createSQLQuery("select t1.RatioA,t1.RatioB from T_Measure t1 where t1.ID=?");
		q.setParameter(0, messid);
		List l = q.list();
		Measure re=new Measure();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);;
			  Double RA = (Double)row[0];
			  Double RB = (Double)row[1]; 
			  re.setRatioA(RA);
			  re.setRatioB(RB);
			  
		}
		
		return re;
		
	}
}

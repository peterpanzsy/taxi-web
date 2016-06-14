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
import cn.edu.cup.manage.business.Parameters;
import cn.edu.cup.manage.business.Physical;
import cn.edu.cup.manage.business.Style;
import cn.edu.cup.test.TestHibernate;

public class ParameterDao {


	
	public int addParameter(int mid,String display, String name){
		
		Query q = session.createSQLQuery("insert into t_parameters (measureID,display,name) values (?,?,?)");
		q.setParameter(0, mid);
		q.setParameter(1, display);
		q.setParameter(2, name);
		int result=q.executeUpdate();
		
		tx.commit();
		return result;
	}
	
	
	
	




	

	
	 SessionFactory sessionFactory;
	 Session session ;
	 Transaction tx ;
	

	public ParameterDao()
	{	
		Configuration cfg = new Configuration();  
        cfg.configure();          
        ServiceRegistry  sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();           
        SessionFactory sessionFactory = cfg.buildSessionFactory(sr);  
                  
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	
	}
	
	public  void close()
	{
		tx.commit();
		session.close();
		//sessionFactory.close();
	}

	public int getCountParameters(){
		SQLQuery q = session.createSQLQuery("select count(*) from t_parameters");
		Integer a=((BigInteger)q.uniqueResult()).intValue();
		return a;
	}



	public int isExistParameter(String name){
		SQLQuery q=session.createSQLQuery("select count(*) from t_parameters t where t.name=?");
		q.setParameter(0, name);
		Integer count=((BigInteger)q.uniqueResult()).intValue();
		return count;
		
	}
	public Parameters searchParameter(int id){
		SQLQuery q = session.createSQLQuery("SELECT t1.id,t1.measureID,CONCAT(CName,'(',t2.Symbol,')'),t1.display,t1.name from t_parameters t1,t_measure t2 where t1.measureID=t2.ID and t1.id=?");
		q.setParameter(0, id);
		Object[] row=(Object[]) q.uniqueResult();
		Integer Id = (Integer)row[0];
		String mid = row[1].toString();  
		String mSymbol = (String)row[2];  
		String display=(String)row[3];
		String name=(String)row[4];
		  
		Parameters p=new Parameters(Id, mid, mSymbol, display, name);
		return p;

	}
	public List<Parameters> getParametersList(int page,int rows,String sidx,String sord) {

		SQLQuery q = session.createSQLQuery("SELECT t1.id,t1.measureID,CONCAT(CName,'(',t2.Symbol,')'),t1.display,t1.name from t_parameters t1,t_measure t2 where t1.measureID=t2.ID order by t1."+sidx+" "+sord);

		q.setFirstResult((page-1)*rows);
		q.setMaxResults(rows);
		List l = q.list();
		List<Parameters> re=new ArrayList<Parameters>();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);
			  Integer id = (Integer)row[0];
			  String mid = row[1].toString();  
			  String mSymbol = (String)row[2];  
			  String display=(String)row[3];
			  String name=(String)row[4];
			  
			  Parameters p=new Parameters(id, mid, mSymbol, display, name);

			  
			  re.add(p);
		}
		
		return re;
	}


	
	public int deleteParameter(int  id) {
		SQLQuery q = session.createSQLQuery("delete from t_parameters where ID=?");
		q.setParameter(0, id);
		int re=q.executeUpdate();
//		tx.commit();
		return re;
		
	}


	public int updateParameter(int iD, int mid, String display,
			String name) {
		// TODO Auto-generated method stub
		SQLQuery q = session.createSQLQuery("update t_parameters t set measureID=?,display=?,name=? where t.ID=?");
		q.setParameter(0, mid);
		q.setParameter(1, display);
		q.setParameter(2, name);
		q.setParameter(3, iD);
		int re=q.executeUpdate();
		tx.commit();
		return re;
	}

	public double getISOValue(int paramid,double value){
		SQLQuery q = session.createSQLQuery("select t.measureID from t_parameters t where t.ID=?");
		q.setParameter(0, paramid);
		Integer a=(Integer)(q.uniqueResult());
		int messid=a.intValue();		
		PhysicalDao phydao=new PhysicalDao();
		Measure m=phydao.getMess(messid);
		double ISOvalue=m.getRatioA()*value+m.getRatioB();
		
		return ISOvalue;
	}
	public double getMessValue(int paramid,double value){
		SQLQuery q = session.createSQLQuery("select t.measureID from t_parameters t where t.ID=?");
		q.setParameter(0, paramid);
		Integer a=(Integer)(q.uniqueResult());
		int messid=a.intValue();	
		
		PhysicalDao phydao=new PhysicalDao();
		Measure m=phydao.getMess(messid);
		double Messvalue=(value-m.getRatioB())/m.getRatioA();
		return Messvalue;
	}

	public double getMessValueByName(String name, double paramOutput) {
		SQLQuery q = session.createSQLQuery("select t.measureID from t_parameters t where t.Name=?");
		q.setParameter(0, name);
		Integer a=(Integer)(q.uniqueResult());
		int messid=a.intValue();	
		
		PhysicalDao phydao=new PhysicalDao();
		Measure m=phydao.getMess(messid);
		double Messvalue=(paramOutput-m.getRatioB())/m.getRatioA();
		return Messvalue;
	
	}

}

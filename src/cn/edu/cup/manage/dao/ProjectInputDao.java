package cn.edu.cup.manage.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import cn.edu.cup.manage.business.ProjectInputs;

public class ProjectInputDao {

	
	 SessionFactory sessionFactory;
	 Session session ;
	 Transaction tx ;
		
	public  void close()
	{
		tx.commit();
		session.close();
		//sessionFactory.close();
	}

	public ProjectInputDao()
	{	
		Configuration cfg = new Configuration();  
     cfg.configure();          
     ServiceRegistry  sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();           
     SessionFactory sessionFactory = cfg.buildSessionFactory(sr);  
               
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	
	}
	

	public ProjectInputs searchInput(int iD) {
		// TODO Auto-generated method stub
		Date modifyTime=new Date();
		SQLQuery q = session.createSQLQuery("select t1.ID,t1.par_display,t1.par_id,t1.par_messID,t1.par_name,t1.par_value,t1.Pro_ID,CONCAT(t2.CName,'(',t2.Symbol,')') from t_projectinputs t1,t_measure t2 where t1.par_messID=t2.ID and t1.ID=? ");
		q.setParameter(0, iD);
		Object[] row=(Object[]) q.uniqueResult();
		Integer id = ((Integer)row[0]);
	    String display = ((String)row[1]);
		Integer parid = ((Integer)row[2]);
		Integer meid = ((Integer)row[3]);
		String name=(String)row[4];
		double vaule=(Double)row[5];
		Integer pid = ((Integer)row[6]);
		String messSymbol=(String)row[7];
		  
		ProjectInputs p=new ProjectInputs(id,display,parid,meid,name,vaule,pid,messSymbol);
		return p;
	}
	
	public List<ProjectInputs> getProInputsList(int pro_id,int page, int rows,
			String sidx, String sord) {
		SQLQuery q = session.createSQLQuery("select t1.ID,t1.par_display,t1.par_id,t1.par_messID,t1.par_name,t1.par_value,t1.Pro_ID,CONCAT(t2.CName,'(',t2.Symbol,')') from t_projectinputs t1,t_measure t2 where t1.par_messID=t2.ID and t1.Pro_ID=? order by t1."+sidx+" "+sord);
		q.setParameter(0, pro_id);
		q.setFirstResult((page-1)*rows);
		q.setMaxResults(rows);
		List l = q.list();
		List<ProjectInputs> re=new ArrayList<ProjectInputs>();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);;
			  Integer id = ((Integer)row[0]);
			  String display = ((String)row[1]);
			  Integer parid = ((Integer)row[2]);
			  Integer meid = ((Integer)row[3]);
			  String name=(String)row[4];
			  double vaule=(Double)row[5];
			  Integer pid = ((Integer)row[6]);
			  String messSymbol=(String)row[7];
			  
			  
			  ProjectInputs p=new ProjectInputs(id,display,parid,meid,name,vaule,pid,messSymbol);
			  
			  
			  re.add(p);
		}
		
		return re;
	}

	public int getCountProInputs(int proid) {
		// TODO Auto-generated method stub
		String sql="select count(*) from t_projectinputs t2 where t2.pro_id=? ";
		SQLQuery q = session.createSQLQuery(sql);
		q.setParameter(0, proid);
		Integer count=((BigInteger)q.uniqueResult()).intValue();
		return count;

	}
	public int addInput(int pro_id,int param_id,double value) {
		ParameterDao pDao=new ParameterDao();
		double ISOValue=pDao.getISOValue(param_id, value);
		Query q = session.createSQLQuery("INSERT into t_projectinputs  select NULL,?,t1.id,t1.display,?,t1.name,t1.measureID,? from t_parameters t1 where t1.ID=?;");
		q.setParameter(0, pro_id);
		q.setParameter(1, value);
		q.setParameter(2, ISOValue);
		q.setParameter(3, param_id);
		
		int result=q.executeUpdate();
		
		tx.commit();
		int ret_id=0; 
		Query q2 = session.createSQLQuery("select LAST_INSERT_ID()"); 
		ret_id=((BigInteger) q2.uniqueResult()).intValue();
		return ret_id;
	}
	
	public int deleteInput(int  id) {
		SQLQuery q = session.createSQLQuery("delete from t_projectinputs where ID=?");
		q.setParameter(0, id);
		int re=q.executeUpdate();
//		tx.commit();
		return re;
		
	}


	public int updateInput(int iD, double value) {
		// TODO Auto-generated method stub
		Date modifyTime=new Date();
		int paramID=getParamID(iD);
		ParameterDao pDao=new ParameterDao();
		
		double ISOValue=pDao.getISOValue(paramID, value);
		
		SQLQuery q = session.createSQLQuery("update t_projectinputs t set t.par_value=?,t.par_ISOValue=? where t.ID=?");
		q.setParameter(0, value);

		q.setParameter(1, ISOValue);
		q.setParameter(2, iD);
		int re=q.executeUpdate();

		return re;
	}

	public int getParamID(int id){
		String sql="select t2.par_id from t_projectinputs t2 where t2.id=?";
		SQLQuery q = session.createSQLQuery(sql);
		q.setParameter(0, id);
		Integer paramid=((Integer)q.uniqueResult()).intValue();
		return paramid;

	}
}

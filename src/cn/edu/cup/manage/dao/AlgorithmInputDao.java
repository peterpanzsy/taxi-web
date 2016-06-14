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

import cn.edu.cup.manage.business.AlgorithmInput;

public class AlgorithmInputDao {
	 SessionFactory sessionFactory;
	 Session session ;
	 Transaction tx ;
		
	public  void close()
	{
		tx.commit();
		session.close();
		//sessionFactory.close();
	}

	public AlgorithmInputDao()
	{	
		Configuration cfg = new Configuration();  
      cfg.configure();          
      ServiceRegistry  sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();           
      SessionFactory sessionFactory = cfg.buildSessionFactory(sr);  
                
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	
	}
	public List<AlgorithmInput> getAlgorithmInputList(int page, int rows,
			String sidx, String sord,int CycleID) {
		SQLQuery q = session.createSQLQuery("select t1.ID,t1.CycleID,t2.ID pid,t2.display,CONCAT(t3.CName,'(',t3.Symbol,')') from t_algorithminput t1,t_parameters t2,t_measure t3 where t1.ParamID=t2.ID and t3.id=t2.measureID and t1.CycleID=? order by t1."+sidx+" "+sord);
		q.setParameter(0, CycleID);
		q.setFirstResult((page-1)*rows);
		q.setMaxResults(rows);
		List l = q.list();
		List<AlgorithmInput> re=new ArrayList<AlgorithmInput>();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);;
			  String id = ((Integer)row[0]).toString();
			  String cid = ((Integer)row[1]).toString();
			  String mid = ((Integer)row[2]).toString();
			  String display = (String) row[3];
			  String symbol = (String) row[4];
			  AlgorithmInput p=new AlgorithmInput(id,cid,mid,display,symbol);
			  re.add(p);
		}
		
		return re;
	}

	public int getCountAlgorithms() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<AlgorithmInput> getExistAlgorithmInputList(int CycleID,int paramID) {
		SQLQuery q = session.createSQLQuery("select t1.ID,t1.CycleID,t2.ID pid,t2.display,CONCAT(t3.CName,'(',t3.Symbol,')') from t_algorithminput t1,t_parameters t2,t_measure t3 where t1.ParamID=t2.ID and t3.id=t2.measureID and t1.CycleID=? and t1.ParamID=?");
		q.setParameter(0, CycleID);
		q.setParameter(1, paramID);
		List l = q.list();
		List<AlgorithmInput> re=new ArrayList<AlgorithmInput>();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);;
			  String id = ((Integer)row[0]).toString();
			  String cid = ((Integer)row[1]).toString();
			  String mid = ((Integer)row[2]).toString();
			  String display = (String) row[3];
			  String symbol = (String) row[4];
			  AlgorithmInput p=new AlgorithmInput(id,cid,mid,display,symbol);
			  re.add(p);
		}
		
		return re;
	}
	
	public int isExistAlgorithmInput(int cycleID,int paramID){
		Query q=session.createSQLQuery("select count(*) from t_algorithminput t where t.cycleID=? and t.ParamID=?");
		q.setParameter(0, cycleID);
		q.setParameter(1,paramID);
		Integer count=((BigInteger)q.uniqueResult()).intValue();
		return count;
	}
	public int addAlgorithm(int cycleID, int paramID) {
		Date addDate=new Date();
		Query q = session.createSQLQuery("insert into t_algorithminput (cycleID,paramID) values (?,?)");
		q.setParameter(0, cycleID);
		q.setParameter(1, paramID);
		int result=q.executeUpdate();
		
//		tx.commit();
		return result;
	}
	
	public int deleteAlgorithm(int  id) {
		SQLQuery q = session.createSQLQuery("delete from t_algorithminput where ID=?");
		q.setParameter(0, id);
		int re=q.executeUpdate();
		tx.commit();
		return re;
		
	}

	public int deleteAlgorithmByCycle(int  cycleId) {
		SQLQuery q = session.createSQLQuery("delete from t_algorithminput where cycleID=?");
		q.setParameter(0, cycleId);
		int re=q.executeUpdate();
//		tx.commit();
		return re;
		
	}
	
	public int updateParameter(int iD, int cycleID, int paramID) {
		// TODO Auto-generated method stub
		
		SQLQuery q = session.createSQLQuery("update t_algorithminput t set cycleID=?,paramID=? where t.ID=?");
		q.setParameter(0, cycleID);
		q.setParameter(1, paramID);
		q.setParameter(2, iD);
		int re=q.executeUpdate();
		tx.commit();
		return re;
	}
}

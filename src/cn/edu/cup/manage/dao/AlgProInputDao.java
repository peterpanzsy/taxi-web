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

import cn.edu.cup.manage.business.AlgorithmPro;
import cn.edu.cup.manage.business.ProjectInputs;

public class AlgProInputDao {

	
	 SessionFactory sessionFactory;
	 Session session ;
	 Transaction tx ;
		
	public  void close()
	{
		tx.commit();
		session.close();
		//sessionFactory.close();
	}

	public AlgProInputDao()
	{	
		Configuration cfg = new Configuration();  
      cfg.configure();          
      ServiceRegistry  sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();           
      SessionFactory sessionFactory = cfg.buildSessionFactory(sr);  
                
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	
	}
	public List<ProjectInputs> getAlgorithmProInputsList(int page, int rows,
			String sidx, String sord,int algCycleID,int pro_id) {
		SQLQuery q = session.createSQLQuery("select count(*) from t_calcinput_his t1 ,t_projects t4 where t1.Pro_ID=t4.ID");

		//q.setFirstResult((page-1)*rows);
		//q.setMaxResults(rows);
		Integer cou=(Integer)q.uniqueResult();
		if (cou>0){
			//q = session.createSQLQuery("INSERT INTO t_calcinput_his t1 (SELECT NULL,-1,t2.Pro_ID,t2.AlgorCycle_ID,t2.Input_Par_ID,t2.Input_Par_Value from t_calcinput_his t2 where t2.Pro_ID=? and t2.calc_id=(select max(t3.id) from t_calchis t3 where t3.pro_id=?))");
			//q.setParameter(0, pro_id);
			//q.setParameter(1, algCycleID);
			//int result=q.executeUpdate();
		}else{
			q = session.createSQLQuery("INSERT INTO t_calcinput_his SELECT NULL,-1,?,t2.CycleID,t3.ID,0 from t_algorithminput t2,t_parameters t3,t_measure t4 where t2.CycleID=? and t2.ParamID=t3.ID  and t3.measureID=t4.ID");
			q.setParameter(0, pro_id);
			q.setParameter(1, algCycleID);
			int result=q.executeUpdate();
			tx.commit();
			
		}
		q = session.createSQLQuery("select t0.id,t3.name,t3.display,CONCAT(t4.CName,'(',t4.Symbol,')'),t0.Input_Par_Value from t_calcinput_his t0,t_parameters t3,t_measure t4 where t0.Calc_ID=-1 and t0.Pro_ID=? and t0.Input_Par_ID=t3.ID  and t3.measureID=t4.ID");
		q.setParameter(0, pro_id);
		List l = q.list();
		List<ProjectInputs> re=new ArrayList<ProjectInputs>();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);;
			  Integer id = ((Integer)row[0]);
			  String name = ((String)row[1]);
			  String display=(String)row[2];
			  String mess=((String)row[3]);
			  Double value=(Double)row[4];
			  
			  
			  
			  ProjectInputs p=new ProjectInputs(id, name,display,mess,value);
			  
			  
			  re.add(p);
		}
		
		return re;
	}

	//public int getCountAlgorithms() {
		// TODO Auto-generated method stub
	//	String sql="select count(*) from t_projects t2 ";
	//	SQLQuery q = session.createSQLQuery(sql);
	//	Integer count=((BigInteger)q.uniqueResult()).intValue();
	//	return count;

	//}


	public int updateProInputs(int iD, double value) {
		// TODO Auto-generated method stub
		Date modifyTime=new Date();
		SQLQuery q = session.createSQLQuery("update t_calcinput_his t set Input_Par_Value=? where t.ID=?");
		q.setParameter(0, value);
		q.setParameter(1, iD);


		int re=q.executeUpdate();
		tx.commit();
		return re;
	}



	
}

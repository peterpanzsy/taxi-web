package cn.edu.cup.gui.dao;

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

import cn.edu.cup.gui.business.GUIPro;
import cn.edu.cup.manage.business.CalcInfo;

public class GUIDao {

	
	 SessionFactory sessionFactory;
	 Session session ;
	 Transaction tx ;
		
	public  void close()
	{
		tx.commit();
		session.close();
		//sessionFactory.close();
	}

	public GUIDao()
	{	
	 Configuration cfg = new Configuration();  
     cfg.configure();          
     ServiceRegistry  sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();           
     SessionFactory sessionFactory = cfg.buildSessionFactory(sr);  
               
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	}
	public List<GUIPro> getGUIProsList(int page, int rows,
			String sidx, String sord) {
		SQLQuery q = session.createSQLQuery("select t1.ID,t1.name,t1.Description,t1.AuthorID,t2.Username,t1.AddTime from t_guipro t1,t_user t2 where t1.AuthorID=t2.ID order by t1."+sidx+" "+sord);

		q.setFirstResult((page-1)*rows);
		q.setMaxResults(rows);
		List l = q.list();
		List<GUIPro> re=new ArrayList<GUIPro>();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			  Object[] row = (Object[])l.get(i);;
			  Integer id = ((Integer)row[0]);
			  String proname = ((String)row[1]);
			  String description=(String)row[2];
			  Integer aid=((Integer)row[3]);
			  String author=(String)row[4];
			  Date addTime=(Date)row[5];
			  
			  
			  
			  GUIPro p=new GUIPro(id, proname,aid,author,description,addTime);
			  
			  
			  re.add(p);
		}
		
		return re;
	}

	public int getCountGUIPros() {
		// TODO Auto-generated method stub
		String sql="select count(*) from t_guipro t2 ";
		SQLQuery q = session.createSQLQuery(sql);
		Integer count=((BigInteger)q.uniqueResult()).intValue();
		return count;

	}

	
	public int deletePro(int  id) {
		SQLQuery q = session.createSQLQuery("delete from t_guipro where ID=?");
		q.setParameter(0, id);
		int re=q.executeUpdate();
//		tx.commit();
		return re;
		
	}


	public int updatePro(int iD, String data) {
		// TODO Auto-generated method stub
		Date modifyTime=new Date();
		SQLQuery q = session.createSQLQuery("update t_guipro t set addtime=? where t.ID=?");
		q.setParameter(1, iD);
		q.setParameter(0, modifyTime);
		
		int re=q.executeUpdate();
		q = session.createSQLQuery("update t_guijson t set JSONData=?  where t.ID=?");
		q.setParameter(1, iD);
		q.setParameter(0, data);
		
		re=q.executeUpdate();
		tx.commit();
		return re;
	}
	public int searchProAlg(int proID){
		Query q=session.createSQLQuery("select t.algorithm_id from t_projects t where t.ID=?");
		q.setParameter(0, proID);
		int result=0;
		result=(Integer) q.uniqueResult(); 
		tx.commit();
		return result;
	}


	public GUIPro getGUIProView(int pro_id) {
		// TODO Auto-generated method stub
		SQLQuery q = session.createSQLQuery("select t1.ID,t1.name,t1.Description,t1.AuthorID,t2.Username,t1.AddTime ,t3.JSONData from t_guipro t1,t_user t2,t_guijson t3 where t1.AuthorID=t2.ID and t1.id=? and t1.id=t3.id");
		q.setParameter(0, pro_id);
		GUIPro temp=null;
		List l = q.list();
		for(int i=0;i<l.size();i++)
		{
			//TestDb user = (TestDb)l.get(i);
			//System.out.println(user.getUsername());

			Object[] row = (Object[])l.get(i);;
			  Integer id = ((Integer)row[0]);
			  String proname = ((String)row[1]);
			  String description=(String)row[2];
			  Integer aid=((Integer)row[3]);
			  String author=(String)row[4];
			  Date addTime=(Date)row[5];
			  String jsondata=(String)row[6];
			  temp=new GUIPro(id, proname, aid, author, description, addTime);
			  temp.setJSONData(jsondata);
			  return temp;
			 
			  
		}
		
		
		return temp;
	}

	public void updateProInfo(int id) {
		Date modifyTime=new Date();
		SQLQuery q = session.createSQLQuery("update t_projects t set t.CalcHisNum=(select count(*) from t_calchis t1 where t1.Pro_ID=?),t.LastCalcTime=(select max(t2.Calc_EndTime) from t_calchis t2 where t2.Pro_ID=?) where t.ID=?");
		
		q.setParameter(0, id);
		q.setParameter(1, id);
		q.setParameter(2, id);
		int re=q.executeUpdate();
		
		
	}

	public int addPro(String description,String name, String data, Date date, int authorID,int type) {
		Date addDate=new Date();
		Query q = session.createSQLQuery("insert into t_guipro (description,authorID,addtime,name,type) values (?,?,?,?,?)");
		q.setParameter(0, description);
		q.setParameter(1, authorID);
		q.setParameter(2, addDate);
		q.setParameter(3, name);
		q.setParameter(4, type);
		int result=q.executeUpdate();
		
		
		int ret_id = 0;
		Query q2 = session.createSQLQuery("select LAST_INSERT_ID()");
		ret_id = ((BigInteger) q2.uniqueResult()).intValue();
		
		q = session.createSQLQuery("insert into t_guijson (id,JSONData) values (?,?)");
		q.setParameter(0, ret_id);
		q.setParameter(1, data);
		result=q.executeUpdate();
		tx.commit();
		return ret_id;
		
	}

}

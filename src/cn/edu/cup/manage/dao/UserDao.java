package cn.edu.cup.manage.dao;

// default package

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;  
import javax.persistence.TableGenerator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import cn.edu.cup.manage.business.Style;
import cn.edu.cup.manage.business.User;
import cn.edu.cup.map.business.Line;
import cn.edu.cup.map.business.MapPro;
import cn.edu.cup.test.TestHibernate;


public class UserDao  {
    
	//property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String USERGROUPID = "usergroupid";
	public static final String EMAIL = "email";
	public static final String STATE = "state";
	public static final String INFO = "info";
		

	public UserDao()
	{	
		Configuration cfg = new Configuration();  
        cfg.configure();          
        ServiceRegistry  sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();           
        SessionFactory sessionFactory = cfg.buildSessionFactory(sr);  
                  
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	
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
	
	public int addUser(String username,String password,int usergroupid,String email,String state,String info){
		
		Query q = session.createSQLQuery("insert into T_User (username,password,usergroupid,email,state,info) values (?,?,?,?,?,?)");
		q.setParameter(0, username);
		q.setParameter(1, password);
		q.setParameter(2, usergroupid);
		q.setParameter(3, email);
		q.setParameter(4, state);
		q.setParameter(5, info);
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
	

	public void initDao()
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
	
	public User searchUser(String name,String pass,String type){
		
		
		
		User userR=null;
		try {
				
				
		        Query query = session.createSQLQuery("select * from t_user s where s.username=? and s.password=?");
		        query.setParameter(0, name);
		        query.setParameter(1, pass);
		        
		        List users=new ArrayList<User>();
				List l = query.list();
				for(int i=0;i<l.size();i++)
				{
					//TestDb user = (TestDb)l.get(i);
					//System.out.println(user.getUsername());

					  Object[] row = (Object[])l.get(i);;
					  int id = (Integer) row[0];
					  String username = (String)row[1];  
					  String password = (String)row[2];
					  User user=new User();
					  user.setUserid(id);
					  user.setUsername(username);
					  user.setPassword(password);
					  users.add(user);
				}
				if(!users.isEmpty()){
					userR=new User();
					userR=(User)users.get(0);
				}
				
				} catch (Exception e) {
			e.printStackTrace();
		}		
		return userR;
	}
	
	public List<User> getUserList() {
		SQLQuery q = session.createSQLQuery("select t.ID,t.username from t_user t");
		List l = q.list();
		List<User> re=new ArrayList<User>();
		for(int i=0;i<l.size();i++)
		{
			  Object[] row = (Object[])l.get(i);;
			  Integer uid = (Integer)row[0];
			  String uName = (String)row[1];  
			  User user=new User(uid, uName,null,0,null,0,null);	 
			  re.add(user);
		}
		return re;
	}
	
	public Boolean findByUsername(String name){
		SQLQuery q=session.createSQLQuery("select count(*) from t_user t where t.username=?");
		q.setParameter(0, name);
		int count=((BigInteger)q.uniqueResult()).intValue();
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getUSERNAME() {
		return USERNAME;
	}

	public static String getPASSWORD() {
		return PASSWORD;
	}

	public static String getUSERGROUPID() {
		return USERGROUPID;
	}

	public static String getEMAIL() {
		return EMAIL;
	}

	public static String getSTATE() {
		return STATE;
	}

	public static String getINFO() {
		return INFO;
	}

}
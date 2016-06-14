package cn.edu.cup.test;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class TestHibernate {

	
		
		//遍历
		public static void all()
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
		public static void load()
		{
			TestHibernate obj = (TestHibernate) session.load(TestHibernate.class, new Long(2));
			
				
			
			//System.out.println(obj.getUsername());
		}
		
		//更新
		public static void update()
		{
			TestHibernate obj = (TestHibernate) session.load(TestHibernate.class, new Long(2));
			//obj.setUsername("cg");
		}
		
		//插入
		public static void insert()
		{
			TestHibernate user = new TestHibernate();
			//user.setUsername("sb");

			session.save(user);
		}
		
		static SessionFactory sessionFactory;
		static Session session ;
		static Transaction tx ;
		
		@SuppressWarnings("deprecation")
		private static void init()
		{	
			Configuration cfg = new Configuration();  
	        cfg.configure();          
	        ServiceRegistry  sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();           
	        SessionFactory sessionFactory = cfg.buildSessionFactory(sr);  
	                  
			//sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
		}
		
		private static void close()
		{
			tx.commit();
			session.close();
			//sessionFactory.close();
		}
		
		public static void main(String[] args) 
		{
			init();
			all();
			close();
		}
	}



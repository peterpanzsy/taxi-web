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

import cn.edu.cup.manage.business.CalcHisInput;
import cn.edu.cup.manage.business.CalcHisOutput;
import cn.edu.cup.manage.business.ProjectCalcHis;
import cn.edu.cup.manage.business.ProjectInputs;

public class ProjectCalcHisDao {

	SessionFactory sessionFactory;
	Session session;
	Transaction tx;

	public void close() {
		tx.commit();
		session.close();
		// sessionFactory.close();
	}

	public ProjectCalcHisDao() {
		Configuration cfg = new Configuration();
		cfg.configure();
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(
				cfg.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = cfg.buildSessionFactory(sr);

		// sessionFactory = new
		// Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

	}

	public ProjectInputs searchInput(int iD) {
		// TODO Auto-generated method stub
		Date modifyTime = new Date();
		SQLQuery q = session
				.createSQLQuery("select t1.ID,t1.par_display,t1.par_id,t1.par_messID,t1.par_name,t1.par_value,t1.Pro_ID,CONCAT(t2.CName,'(',t2.Symbol,')') from t_projectinputs t1,t_measure t2 where t1.par_messID=t2.ID and t1.ID=? ");
		q.setParameter(0, iD);
		Object[] row = (Object[]) q.uniqueResult();
		Integer id = ((Integer) row[0]);
		String display = ((String) row[1]);
		Integer parid = ((Integer) row[2]);
		Integer meid = ((Integer) row[3]);
		String name = (String) row[4];
		double vaule = (Double) row[5];
		Integer pid = ((Integer) row[6]);
		String messSymbol = (String) row[7];

		ProjectInputs p = new ProjectInputs(id, display, parid, meid, name,
				vaule, pid, messSymbol);
		return p;
	}

	public List<ProjectInputs> getProInputsList(int pro_id, int page, int rows,
			String sidx, String sord) {
		SQLQuery q = session
				.createSQLQuery("select t1.ID,t1.par_display,t1.par_id,t1.par_messID,t1.par_name,t1.par_value,t1.Pro_ID,CONCAT(t2.CName,'(',t2.Symbol,')') from t_projectinputs t1,t_measure t2 where t1.par_messID=t2.ID and t1.Pro_ID=? order by t1."
						+ sidx + " " + sord);
		q.setParameter(0, pro_id);
		q.setFirstResult((page - 1) * rows);
		q.setMaxResults(rows);
		List l = q.list();
		List<ProjectInputs> re = new ArrayList<ProjectInputs>();
		for (int i = 0; i < l.size(); i++) {
			// TestDb user = (TestDb)l.get(i);
			// System.out.println(user.getUsername());

			Object[] row = (Object[]) l.get(i);
			;
			Integer id = ((Integer) row[0]);
			String display = ((String) row[1]);
			Integer parid = ((Integer) row[2]);
			Integer meid = ((Integer) row[3]);
			String name = (String) row[4];
			double vaule = (Double) row[5];
			Integer pid = ((Integer) row[6]);
			String messSymbol = (String) row[7];

			ProjectInputs p = new ProjectInputs(id, display, parid, meid, name,
					vaule, pid, messSymbol);

			re.add(p);
		}

		return re;
	}

	public int getCountProInputs(int proid) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from t_projectinputs t2 where t2.pro_id=? ";
		SQLQuery q = session.createSQLQuery(sql);
		q.setParameter(0, proid);
		Integer count = ((BigInteger) q.uniqueResult()).intValue();
		return count;

	}

	public int addInput(int pro_id, int param_id, double value) {
		ParameterDao pDao = new ParameterDao();
		double ISOValue = pDao.getISOValue(param_id, value);
		Query q = session
				.createSQLQuery("INSERT into t_projectinputs  select NULL,?,t1.id,t1.display,?,t1.name,t1.measureID,? from t_parameters t1 where t1.ID=?;");
		q.setParameter(0, pro_id);
		q.setParameter(1, value);
		q.setParameter(2, ISOValue);
		q.setParameter(3, param_id);

		int result = q.executeUpdate();

		tx.commit();
		int ret_id = 0;
		Query q2 = session.createSQLQuery("select LAST_INSERT_ID()");
		ret_id = ((BigInteger) q2.uniqueResult()).intValue();
		return ret_id;
	}

	public int deleteInput(int id) {
		SQLQuery q = session
				.createSQLQuery("delete from t_projectinputs where ID=?");
		q.setParameter(0, id);
		int re = q.executeUpdate();
		// tx.commit();
		return re;

	}

	public int updateInput(int iD, double value) {
		// TODO Auto-generated method stub
		Date modifyTime = new Date();
		SQLQuery q = session
				.createSQLQuery("update t_projectinputs t set t.par_value=? where t.ID=?");
		q.setParameter(0, value);
		q.setParameter(1, iD);
		int re = q.executeUpdate();
		tx.commit();
		return re;
	}

	public int addCalcHis(int pro_id, Date start) {

		Query q = session
				.createSQLQuery("INSERT into t_calchis (ID,Pro_ID,Algorith_ID,Calc_StartTime,Calc_EndTime,Calc_re) select null,t2.id,t2.algorithm_id,?,now(),t2.CalcRes from t_projects t2 where t2.id=?");
		q.setParameter(0, start);
		q.setParameter(1, pro_id);

		int result = q.executeUpdate();

		int ret_id = 0;
		Query q2 = session.createSQLQuery("select LAST_INSERT_ID()");
		ret_id = ((BigInteger) q2.uniqueResult()).intValue();

		Query q3 = session
				.createSQLQuery("INSERT into t_calcinput_his  (ID,Pro_ID,par_id,par_display,par_value,par_name,par_messID,par_ISOValue,Calc_ID) select NULL,t1.Pro_ID,t1.par_id,t1.par_display,t1.par_value,t1.par_name,t1.par_messID,t1.par_ISOValue,? from t_projectinputs t1 where t1.pro_id=?");
		q3.setParameter(0, ret_id);
		q3.setParameter(1, pro_id);

		result = q3.executeUpdate();

		Query q4 = session
				.createSQLQuery("INSERT into t_calcoutput_his  (ID,Pro_ID,par_id,par_display,par_value,par_name,par_messID,par_ISOValue,Calc_ID) select NULL,t1.Pro_ID,t1.par_id,t1.par_display,t1.par_value,t1.par_name,t1.par_messID,t1.par_ISOValue,? from t_projectoutputs t1 where t1.pro_id=?");
		q4.setParameter(0, ret_id);
		q4.setParameter(1, pro_id);

		result = q4.executeUpdate();

		tx.commit();
		return ret_id;

	}

	public List<ProjectCalcHis> getCalcHis(int proID) {
		Query q = session
				.createSQLQuery("select t.id,t.Pro_ID,t.Algorith_ID,t2.`Name`,t.Calc_StartTime,t.Calc_EndTime,t.Calc_re from t_calchis t,t_algorithmscycle t2 where t.Algorith_ID=t2.ID and t.Pro_ID=? order by t.id asc");
		q.setParameter(0, proID);

		List l = q.list();
		List<ProjectCalcHis> re = new ArrayList<ProjectCalcHis>();
		for (int i = 0; i < l.size(); i++) {

			Object[] row = (Object[]) l.get(i);
			;
			Integer id = ((Integer) row[0]);
			Integer proid = ((Integer) row[1]);
			Integer algid = ((Integer) row[2]);
			String algName = ((String) row[3]);

			Date startTime = ((Date) row[4]);
			Date endTime = ((Date) row[5]);
			String res = ((String) row[6]);

			ProjectCalcHis p = new ProjectCalcHis(startTime, endTime, id,
					proID, algid, algName, res);

			re.add(p);
		}

		return re;
	}

	public List<CalcHisInput> getCalcInputsHis(int calcid) {
		Query q = session
				.createSQLQuery("select t.id,t.Pro_ID,t.par_id,t.par_display,t.par_value,t.par_name,t.par_messID,CONCAT(t2.CName,'(',t2.Symbol,')') from t_calcinput_his t,t_measure t2 where t.par_messID=t2.ID and t.Calc_ID=?");
		q.setParameter(0, calcid);

		List l = q.list();
		List<CalcHisInput> re = new ArrayList<CalcHisInput>();
		for (int i = 0; i < l.size(); i++) {

			Object[] row = (Object[]) l.get(i);
			;
			Integer id = ((Integer) row[0]);
			Integer proid = ((Integer) row[1]);
			Integer parid = ((Integer) row[2]);
			String pardis = ((String) row[3]);
			
			Double parval = ((Double) row[4]);
			String parName = ((String) row[5]);
			String symbol = ((String) row[7]);

			CalcHisInput p = new CalcHisInput(id, proid, parid, pardis, parval, parName, symbol);
			re.add(p);
		}

		return re;
	}
	public List<CalcHisOutput> getCalcOutputsHis(int calcid) {
		Query q = session
				.createSQLQuery("select t.id,t.Pro_ID,t.par_id,t.par_display,t.par_value,t.par_name,t.par_messID,CONCAT(t2.CName,'(',t2.Symbol,')') from t_calcoutput_his t,t_measure t2 where t.par_messID=t2.ID and t.Calc_ID=?");
		q.setParameter(0, calcid);

		List l = q.list();
		List<CalcHisOutput> re = new ArrayList<CalcHisOutput>();
		for (int i = 0; i < l.size(); i++) {

			Object[] row = (Object[]) l.get(i);
			;
			Integer id = ((Integer) row[0]);
			Integer proid = ((Integer) row[1]);
			Integer parid = ((Integer) row[2]);
			String pardis = ((String) row[3]);
			
			Double parval = ((Double) row[4]);
			String parName = ((String) row[5]);
			String symbol = ((String) row[7]);

			CalcHisOutput p = new CalcHisOutput(id, proid, parid, pardis, parval, parName, symbol);
			re.add(p);
		}

		return re;
	}

}

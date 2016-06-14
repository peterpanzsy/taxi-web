package cn.edu.cup.manage.algtest;

import cn.edu.cup.manage.business.AlgorithmPlugBase;

public class testzsy extends AlgorithmPlugBase {

	public testzsy(int pro_id2) {
		super(pro_id2);
		// TODO Auto-generated constructor stub
	}
	
	public String startCalc() {
		double a=this.info.getParamInput("zsya");
		double b=this.info.getParamInput("zsyb");
		double c=a*b;
		this.info.addParamOutput("zsyc", c);
		System.out.println(c);
		return null;
	}

}

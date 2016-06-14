package cn.edu.cup.manage.algtest;

import cn.edu.cup.manage.business.AlgorithmPlugBase;

public class TestAlg extends AlgorithmPlugBase{
	
	public TestAlg(int pro_id) {
		super(pro_id);
		// TODO Auto-generated constructor stub
	}

	public String startCalc() {
		double a=this.info.getParamInput("a");
		double b=this.info.getParamInput("b");
		double c=a+b;
		this.info.addParamOutput("c", c);
		System.out.println(c);
		return null;
	}
}

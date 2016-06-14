package cn.edu.cup.manage.algtest;

import cn.edu.cup.manage.business.AlgorithmPlugBase;

public class TestGG extends AlgorithmPlugBase {

	public TestGG(int pro_id2) {
		super(pro_id2);
		// TODO Auto-generated constructor stub
	}
	public String startCalc() {
		double a=this.info.getParamInput("ZHIBIAN1");
		double b=this.info.getParamInput("ZHIBIAN2");
		double c=Math.sqrt(a*a+b*b);
		this.info.addParamOutput("XIEBIAN", c);
		System.out.println(c);
		return null;
	}
}

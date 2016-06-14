package cn.edu.cup.manage.algtest;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import cn.edu.cup.manage.business.AlgorithmJarPlug;

public class Test {

	/**
	 * @param args
	 */
	public void testalgrun(){
		String class_path = "cn.edu.cup.manage.algtest.TestAlg";//Jar中的所需要加载的类的类名

	    String jar_path = "file:/E:/TestAlg.jar";//jar所在的文件的URL
	    
         
	    try {
	    	

	        ClassLoader loader = new URLClassLoader(new URL[]{new URL(jar_path)});//从Jar文件得到一个Class加载器
	        
	       
	        Class<?> c = loader.loadClass(class_path);//从加载器中加载Class
	        
	         
           
           
            Constructor c1=c.getDeclaredConstructor(new Class[]{int.class});   
            c1.setAccessible(true);   
            AlgorithmJarPlug a1=(AlgorithmJarPlug)c1.newInstance(new Object[]{3});   
	        

	        System.out.println(a1.startCalc());//调用Jar中的类方法

	    } catch (MalformedURLException e) {

	        e.printStackTrace();

	    } catch (ClassNotFoundException e) {

	        e.printStackTrace();

	    } catch (InstantiationException e) {

	        e.printStackTrace();

	    } catch (IllegalAccessException e) {

	        e.printStackTrace();

	    } catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		 new Test().testalgrun();
	}

}

package cn.edu.cup.tools;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import cn.edu.cup.manage.business.AlgorithmJarPlug;
import cn.edu.cup.manage.business.MyClassLoader;

public class JarTools {
	public static AlgorithmJarPlug getPlug(String filename,String ClassName,int pro_id){
		String class_path = "cn.edu.cup.manage.algtest."+ClassName;//Jar中的所需要加载的类的类名

	    String jar_path = "file:/"+filename;//jar所在的文件的URL
	    
         
	    try {
	    	 MyClassLoader loader = new MyClassLoader();  
             loader.addURL(new File(filename).toURI().toURL());  
             
	        
	       
	        Class<?> cla = loader.loadClass(class_path);//从加载器中加载Class
	        
	         
           
           
            Constructor c1=cla.getDeclaredConstructor(new Class[]{int.class});   
            c1.setAccessible(true);   
            AlgorithmJarPlug a1=(AlgorithmJarPlug)c1.newInstance(new Object[]{pro_id});   
	        
            return a1;
	        //System.out.println(a1.startCalc());//调用Jar中的类方法

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
		return null;
	}
}

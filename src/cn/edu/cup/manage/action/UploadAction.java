package cn.edu.cup.manage.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import cn.edu.cup.map.business.Graphi;
import cn.edu.cup.map.business.Line;
import cn.edu.cup.map.business.Point;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = 837481714629791752L;
	private File algorithmfile;
	private String algorithmfileContentType;
	private String algorithmfileFileName;
	// private Graphi graphi;
	private String FilePath;
	public static String UPLOADPATH = "uploadAlgorithm\\";
	private static String WEB_ROOT_PATH;

	// public Graphi getGraphi() {
	// return graphi;
	// }

	public String re;

	// public Point analyzePoint(String line){
	// Point a=new Point();
	// try{
	// String arrayTemp[]=line.split(",");
	// //a.setGroupNO(arrayTemp[0].trim());
	// //a.setWellNO(arrayTemp[1].trim());
	// a.setTypeByInt(arrayTemp[0].trim());
	// a.setName(arrayTemp[1].trim());
	// a.setGeodeticCoordinatesX(Double.valueOf(arrayTemp[2].trim()));
	// a.setGeodeticCoordinatesY(Double.valueOf(arrayTemp[3].trim()));
	// a.getLatLonFromGeo();
	// }catch(Exception e){
	// a=null;
	// }
	// return a;
	// }

	// public Line analyzeLine(String line){
	// Line a=new Line();
	// try{
	// String arrayTemp[]=line.split(",");
	// //a.setGroupNO(arrayTemp[0].trim());
	// //a.setWellNO(arrayTemp[1].trim());
	// a.setStart(arrayTemp[1].trim());
	// a.setEnd(arrayTemp[2].trim());
	// }catch(Exception e){
	// a=null;
	// }
	// return a;
	// }
	public String getWebFileRoot() {
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac
				.get(ServletActionContext.SERVLET_CONTEXT);
		WEB_ROOT_PATH = sc.getRealPath("/");
		return WEB_ROOT_PATH;
	}

	public String uploadFile() throws Exception {
		// graphi=new Graphi();
		System.out.println(algorithmfileFileName);
		BufferedReader reader = null;

		String path = getWebFileRoot() + this.UPLOADPATH;
		path = path + String.valueOf(System.currentTimeMillis()) + ".alg";
		FilePath = path;

		copyFile(algorithmfile, new File(FilePath));

		return "SUCCESS";

	}

	public String getFilePath() {
		return FilePath;
	}

	private void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	public String getRe() {
		return re;
	}

	public void setAlgorithmfile(File algorithmfile) {
		this.algorithmfile = algorithmfile;
	}

	public void setAlgorithmfileContentType(String algorithmfileContentType) {
		this.algorithmfileContentType = algorithmfileContentType;
	}

	public void setAlgorithmfileFileName(String algorithmfileFileName) {
		this.algorithmfileFileName = algorithmfileFileName;
	}
}

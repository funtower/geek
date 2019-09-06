package geekcompetition.topic6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

public class FileGenerator {

	public static void main(String[] args) {
		initInputFile();
	}
	
	public static void initInputFile() {
		System.out.println("开始生成");
		long start = System.currentTimeMillis();
		OutputStream out = null;
		try {
			out= new FileOutputStream("E:/FUNTOWER/project/wokspace/GeekTopic/git-repo/geek/src/main/resources/InputFile.txt");
			for (int i = 0; i < 100000; i++) {
				StringBuffer record = new StringBuffer();
				record.append(genTime()).append(",")
				.append(i % 2 + 1).append(",")
				.append(genCustId()).append(",")
				.append("600001").append(",")
				.append((i % 2 + 1) == 1 ? genPrice(9.9, 10.5) : genPrice(11.1, 11.9)).append(",")
				.append(100 + ((int)(Math.random()* 10)) * 10000)
				.append("\r\n");
				out.write(record.toString().getBytes("utf-8"));
				System.out.println(i);
			}
		} catch (Exception e) {
			
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时： " + (end - start) + "ms");
		System.out.println("生成完毕");
	
	}
	
	public static String genTime() {
		int sAm = (int) (15 + (Math.random() * 11));
		String t = "09" + sAm;
		return t ;
	}
	
	public static String genCustId () {
		int id = (int) (1 + (Math.random() * 9));
		
		return "000000000" + id;
	}
	
	public static String genPrice (double from, double to) {
		double price = from + Math.random() * (to - from);
		return new DecimalFormat("#.000").format(price);
	}

}

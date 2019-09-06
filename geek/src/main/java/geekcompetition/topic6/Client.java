package geekcompetition.topic6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 7701);
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter bos = new OutputStreamWriter(os, "utf-8");
		PrintWriter writer = new PrintWriter(bos, true);
		writer.println("000027/home/jike/intdiv/input.txt");
		InputStream inputStream = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String message = "";
		message = br.readLine();
		if (null==message)
		System.out.println(message);
		socket.close();
		
	}
}

/**  
* <p>Title: server.java</p>  
* <p>Description: </p>     
* @author funtower  
* @date 2019年1月15日  
* @version 1.0  
*/  
package geekcompetition.topic6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**  
* <p>Title: server</p>  
* <p>Description: </p>  
* @author funtower  
* @date 2019年1月15日  
*/
public class Server implements Runnable{

	private ServerSocket server;
	
	public Server() {
		try {
			this.server = new ServerSocket(7701);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	/** (non-Javadoc)  
	 * <p>Title: run</p>  
	 * <p>Description: 该线程用于打印套接字传输过来的流信息</p>    
	 * @see java.lang.Runnable#run()  
	 */
	@Override
	public void run() {
		Socket socket = null;
		InputStream in = null;
		BufferedReader br = null;
		try {
			System.out.println("服务端已启动，等待客户端连接……");
			socket = server.accept();
			String originRemoteAddress = socket.getInetAddress().toString().replace("/", "");
			String remoteAddress = socket.getInetAddress().toString()+"对方主机: ("+originRemoteAddress+")";
			System.out.println(remoteAddress+"已连接您的主机，您可以输入 "+originRemoteAddress+":9999 和对方通信");
			in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			br = new BufferedReader(isr);
			String message = "";
			message = br.readLine();
			if (null==message)
			System.out.println(message);
			int port = socket.getPort();
			System.out.println("连接端口："+ port);
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter bos = new OutputStreamWriter(os, "utf-8");
			PrintWriter writer = new PrintWriter(bos, true);
			writer.println("回文");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

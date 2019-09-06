package geekcompetition.topic6;

public class StartUpSocket {

	public static void main(String[] args) {
		Server ser = new Server();
		Thread thread = new Thread(ser);
		thread.start();
	}
}

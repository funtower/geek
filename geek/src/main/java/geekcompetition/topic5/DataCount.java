package geekcompetition.topic5;

public class DataCount {

	/**
	 * 入口方法
	 * @param rpcNum 接口数量
	 * @param timeOut 接口超时时间（秒）
	 * @param costTime 最大处理时间（秒）
	 * @return 返回结果为逗号隔开的两个数字（超时接口数和成功接口返回值汇总）
	 * @throws Exception 
	 */
	public String process(int rpcNum, int timeOut, int costTime) throws Exception {
		for (int i = 0; i < rpcNum; i++) {
			long start = System.nanoTime();
			RPC.call(costTime);
			long end = System.nanoTime();
			
		}
		return null;
		
	}
	
	public static void main(String[] args) throws Exception {
		DataCount dataCount = new DataCount();
		for (int i = 0; i < 10; i++) {
			int rpcNum = (int) (Math.random() * 100);
			int timeOut = (int) (Math.random() * 5) - 1;
			int costTime = 5;
			dataCount.process(rpcNum, timeOut, costTime);
		}
	}
}

package geekcompetition.topic5;

import java.util.concurrent.atomic.AtomicInteger;

public class RPC {

	private static AtomicInteger count = new AtomicInteger(0);
	

	/**
	 * 通过调用该方法模拟业务接口调用
	 * @param costTime 调用接口消耗时间
	 * @return 调用成功返回固定结果
	 * @throws Exception
	 */
	public static int call (int costTime) throws Exception {
		count.incrementAndGet();// 被调用的次数
		int t = (int) (Math.random() * costTime ) +1;// 接口调用时间，随机生成1至5秒
		Thread.sleep(t * 1000);// 随机时间（秒），接口调用时间并不固定，可能会大于timeOut
		return 1;
	}
	
	/**
	 * 获取接口被调次数
	 * @return
	 */
	public static int getCount() {
		return count.get();
	}
	
	
}

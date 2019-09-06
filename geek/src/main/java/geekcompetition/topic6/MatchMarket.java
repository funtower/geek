package geekcompetition.topic6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Map;
import java.util.Queue;

public class MatchMarket {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new MatchMarket().trade();
		long end  = System.currentTimeMillis();
		System.out.println("耗时： " + (end - start) + "ms");
	}

	public void trade() {
		System.out.println("开始交易");
		Map<String, ? extends Collection<StockInfoInput>> parse = InputFileParser
				.parse("E:/FUNTOWER/project/wokspace/GeekTopic/git-repo/geek/src/main/resources/InputFile.txt");
		Collection<StockInfoInput> sell = parse.get("sell");
		Collection<StockInfoInput> bite = parse.get("bite");
		ArrayDeque<StockInfoInput> sellQ = new ArrayDeque<StockInfoInput>(sell);
		ArrayDeque<StockInfoInput> biteQ = new ArrayDeque<StockInfoInput>(bite);
		OutputStream out = null;
		int i = 0;
		try {
			out = new FileOutputStream(
					"E:/FUNTOWER/project/wokspace/GeekTopic/git-repo/geek/src/main/resources/outputFile.txt");

			while (true) {
				i++;
				if (sellQ.isEmpty() || biteQ.isEmpty()) {
					System.out.println("撮合交易结束");
					break;
				}
				StockInfoInput ss = sellQ.pollFirst();
				StockInfoInput sb = biteQ.pollFirst();
				if (ss.getUnitPrice().compareTo(sb.getUnitPrice()) > 0) {
					// 买一小于卖一
					System.out.println("撮合交易结束");
					break;
				}
				ss = getNotEmptyShare(ss, sellQ);
				sb = getNotEmptyShare(sb, biteQ);

				StringBuffer record = new StringBuffer();
				if (ss.getShare().intValue() == sb.getShare().intValue()) {
					// 份额一致，头寸都变成0
					record.append(ss.getStockCode()).append(",").append(ss.getCustId()).append(",")
							.append(sb.getCustId()).append(",").append(ss.getUnitPrice()).append(",")
							.append(ss.getShare());
					ss.setShare(0);
					sb.setShare(0);
				} else if (ss.getShare().intValue() > sb.getShare().intValue()) {
					// 卖方头寸大于买方头寸
					record.append(ss.getStockCode()).append(",").append(ss.getCustId()).append(",")
							.append(sb.getCustId()).append(",").append(ss.getUnitPrice()).append(",")
							.append(sb.getShare());
					ss.setShare(ss.getShare().intValue() - sb.getShare().intValue());
					sb.setShare(0);
					sellQ.offerFirst(ss);
				} else {
					// 卖方头寸小于买方头寸
					record.append(ss.getStockCode()).append(",").append(ss.getCustId()).append(",")
							.append(sb.getCustId()).append(",").append(ss.getUnitPrice()).append(",")
							.append(ss.getShare());
					sb.setShare(sb.getShare().intValue() - ss.getShare().intValue());
					ss.setShare(0);
					biteQ.offerFirst(sb);
				}
				out.write((record.toString() + "\r\n").getBytes("utf-8"));
			}
			System.out.println("循环次数:" + i);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private StockInfoInput getNotEmptyShare(StockInfoInput stock, Queue<StockInfoInput> q) {
		if (stock.getShare().intValue() == 0) {
			stock = q.poll();
			if (q.isEmpty()) {
				return stock;
			}
			stock = getNotEmptyShare(stock, q);
		}
		return stock;
	}

}

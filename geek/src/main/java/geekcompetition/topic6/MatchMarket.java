package geekcompetition.topic6;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

public class MatchMarket {

	public void trade() {
		Map<String, TreeSet<StockInfoInput>> parse = InputFileParser.parse("E:/FUNTOWER/project/wokspace/online-credit-loan/ocl/src/test/resources/InputFile.txt");
		TreeSet<StockInfoInput> sell = parse.get("sell");
		TreeSet<StockInfoInput> bite = parse.get("bite");
		ArrayDeque<StockInfoInput> sellQ = new ArrayDeque<StockInfoInput>(sell);
		ArrayDeque<StockInfoInput> biteQ = new ArrayDeque<StockInfoInput>(bite);
		System.out.println("-------");
		List<String> tradeFlow = new ArrayList<String>(); 
		
		while (true) {
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
			
			if (ss.getShare().intValue() == sb.getShare().intValue()) {
				// 份额一致，头寸都变成0
				StringBuffer recored = new StringBuffer();
				recored.append(ss.getStockCode()).append(",")
				.append(ss.getCustId()).append(",")
				.append(sb.getCustId()).append(",")
				.append(ss.getUnitPrice()).append(",")
				.append(ss.getShare());
				tradeFlow.add(recored.toString());
				ss.setShare(0);
				sb.setShare(0);
			} else if (ss.getShare().intValue() > sb.getShare().intValue()) {
				// 卖方头寸大于买方头寸
				StringBuffer recored = new StringBuffer();
				recored.append(ss.getStockCode()).append(",")
				.append(ss.getCustId()).append(",")
				.append(sb.getCustId()).append(",")
				.append(ss.getUnitPrice()).append(",")
				.append(sb.getShare());
				tradeFlow.add(recored.toString());
				ss.setShare(ss.getShare().intValue() - sb.getShare().intValue());
				sb.setShare(0);
				sellQ.offerFirst(ss);
			} else {
				// 卖方头寸小于买方头寸
				StringBuffer recored = new StringBuffer();
				recored.append(ss.getStockCode()).append(",")
				.append(ss.getCustId()).append(",")
				.append(sb.getCustId()).append(",")
				.append(ss.getUnitPrice()).append(",")
				.append(ss.getShare());
				tradeFlow.add(recored.toString());
				sb.setShare(sb.getShare().intValue() - ss.getShare().intValue());
				ss.setShare(0);
				biteQ.offerFirst(sb);
			}
		}
		System.out.println(tradeFlow);
	}
	
	private StockInfoInput getNotEmptyShare(StockInfoInput stock, Queue<StockInfoInput> q) {
		if (stock.getShare().intValue() == 0) {
			stock = q.poll();
			if(q.isEmpty()) {
				return stock;
			}
			stock = getNotEmptyShare(stock, q);
		}
		return stock;
	}
	
}

package geekcompetition.topic6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class InputFileParser2 {

	public static Map<String, ArrayList<StockInfoInput>> parse(String filePath) {
		Map<String, ArrayList<StockInfoInput>> result = new HashMap<String, ArrayList<StockInfoInput>>();
		// 卖出股票集合，按价格优先，时间优先排序
		ArrayList<StockInfoInput> sell = new ArrayList<>();
		// 买入股票集合，按价格优先，时间优先排序
		ArrayList<StockInfoInput> bite = new ArrayList<>();
		// 日常指定读取的文件
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("文件不存在");
		} else {
			// 初始化读取操作的br对象
			BufferedReader br = null;
			try {
				// 调用字节流,设置读取文件的编码格式
				InputStreamReader ir = new InputStreamReader(new FileInputStream(filePath), "utf-8");
				br = new BufferedReader(ir);
				// 格式固定,对象名len可以自拟
				String len = null;
				while ((len = br.readLine()) != null && len.trim().length() > 0) {
					StockInfoInput stock = getStockFromInputFile(len);
					if (stock.getTradeFlag().intValue() == 1) {
						// 1:卖出
						sell.add(stock);
					} else {
						// 2:买入
						bite.add(stock);
					}
					sell.sort(new Comparator<StockInfoInput>() {
						@Override
						public int compare(StockInfoInput o1, StockInfoInput o2) {
							int compareTo = o1.getUnitPrice().compareTo(o2.getUnitPrice());
							if (compareTo == 0) {
								compareTo = Integer.compare(Integer.parseInt(o2.getTradeTime()),
										Integer.parseInt(o1.getTradeTime()));
							}
							return compareTo;
						}
					});
					bite.sort(new Comparator<StockInfoInput>() {
						@Override
						public int compare(StockInfoInput o1, StockInfoInput o2) {
							int compareTo = o2.getUnitPrice().compareTo(o1.getUnitPrice());
							if (compareTo == 0) {
								compareTo = Integer.compare(Integer.parseInt(o2.getTradeTime()),
										Integer.parseInt(o1.getTradeTime()));
							}
							return compareTo;

						}
					});
					result.put("sell", sell);
					result.put("bite", bite);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {

				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}

	private static StockInfoInput getStockFromInputFile(String len) {
		String[] stockInfo = len.split(",");
		StockInfoInput st = new StockInfoInput();
		st.setTradeTime(stockInfo[0]);
		st.setTradeFlag(Integer.parseInt(stockInfo[1]));
		st.setCustId(stockInfo[2]);
		st.setStockCode(stockInfo[3]);
		st.setUnitPrice(new BigDecimal(stockInfo[4]));
		st.setShare(Integer.parseInt(stockInfo[5]));
		return st;
	}

}

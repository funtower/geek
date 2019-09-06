package geekcompetition.topic6;

import java.math.BigDecimal;

public class StockInfoInput {

	/** 交易时间 4位 如：0935表示09：35*/
	private String tradeTime;
	/** 交易标志 1：卖出    2：买入*/
	private Integer tradeFlag;
	/** 客户号10位 */
	private String custId;
	/** 股票代码6位 */
	private String stockCode;
	/** 每股价格精确到小数点后三位 */
	private BigDecimal unitPrice;
	/** 股份 数字类型100的整数倍*/
	private Integer share;
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public Integer getTradeFlag() {
		return tradeFlag;
	}
	public void setTradeFlag(Integer tradeFlag) {
		this.tradeFlag = tradeFlag;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getShare() {
		return share;
	}
	public void setShare(Integer share) {
		this.share = share;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result + ((share == null) ? 0 : share.hashCode());
		result = prime * result + ((stockCode == null) ? 0 : stockCode.hashCode());
		result = prime * result + ((tradeFlag == null) ? 0 : tradeFlag.hashCode());
		result = prime * result + ((tradeTime == null) ? 0 : tradeTime.hashCode());
		result = prime * result + ((unitPrice == null) ? 0 : unitPrice.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockInfoInput other = (StockInfoInput) obj;
		if (custId == null) {
			if (other.custId != null)
				return false;
		} else if (!custId.equals(other.custId))
			return false;
		if (share == null) {
			if (other.share != null)
				return false;
		} else if (!share.equals(other.share))
			return false;
		if (stockCode == null) {
			if (other.stockCode != null)
				return false;
		} else if (!stockCode.equals(other.stockCode))
			return false;
		if (tradeFlag == null) {
			if (other.tradeFlag != null)
				return false;
		} else if (!tradeFlag.equals(other.tradeFlag))
			return false;
		if (tradeTime == null) {
			if (other.tradeTime != null)
				return false;
		} else if (!tradeTime.equals(other.tradeTime))
			return false;
		if (unitPrice == null) {
			if (other.unitPrice != null)
				return false;
		} else if (!unitPrice.equals(other.unitPrice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StockInfoInput [tradeTime=" + tradeTime + ", tradeFlag=" + tradeFlag + ", custId=" + custId
				+ ", stockCode=" + stockCode + ", unitPrice=" + unitPrice + ", share=" + share + "]";
	}
	
}

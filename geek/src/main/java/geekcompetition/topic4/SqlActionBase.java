package geekcompetition.topic4;

import java.sql.Connection;

public abstract class SqlActionBase {

	public static final int TRX_REQUIRED = 1;
	public static final int TRX_REQUIRE_NEW = 0;

	protected SqlActionBase next;

	protected int trxFlag;

	protected String sql;

	protected Connection conn;

	public abstract void execute();

	public void setTrxFlag(int flag) {
		this.trxFlag = flag;
	}

	/**
	 * 链式写法
	 * @param action
	 * @return next
	 */
	public SqlActionBase next(SqlActionBase action) {
		this.next = action;
		this.next.conn = this.conn;
		return this.next;
	}

	public void setSql(String str) {
		this.sql = str;
	}

	public void setConn (Connection conn) {
		this.conn = conn;
	}
}

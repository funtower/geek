package geekcompetition.topic4;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlAction extends SqlActionBase {

	@Override
	public void execute() {
		try {
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(this.sql);
			statement.execute();
			if (this.next == null || this.next.trxFlag == TRX_REQUIRE_NEW) { // 下一个为独立事务
				conn.commit();
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
			}
		}
		if (this.next != null) {
			this.next.execute();
		}
	}

}

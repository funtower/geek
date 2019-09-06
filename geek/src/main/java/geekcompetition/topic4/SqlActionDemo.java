package geekcompetition.topic4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlActionDemo {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = getConnection();
			SqlActionBase action1 = new SqlAction();
			action1.setSql(
					"INSERT INTO acct_charge_info  (acct_id, acct_tran_id, tran_date, tran_amt, acct_no, dc_flag, to_acct_no) VALUES ('1', 'tr0000001', '2018-08-08', 1000, 'no100000', 'D', 'no200000')");
			SqlActionBase action2 = new SqlAction();
			action2.setSql(
					"INSERT INTO acct_charge_info  (acct_id, acct_tran_id, tran_date, tran_amt, acct_no, dc_flag, to_acct_no) VALUES ('1', 'tr0000001', '2018-08-08', 1000, 'no200000', 'C', 'no100000')");
			SqlActionBase action3 = new SqlAction();
			action3.setSql(
					"INSERT INTO acct_trans_info  (chnl_tran_id, tran_date, tran_amt, out_acct_no, in_acct_no, tran_sts) VALUES ('tr0000001', '2018-08-08', 1000, 'no100000', 'no200000', '01')");

			action1.setConn(conn);
			action1.setTrxFlag(SqlActionBase.TRX_REQUIRED);
			action2.setTrxFlag(SqlActionBase.TRX_REQUIRED);
			action3.setTrxFlag(SqlActionBase.TRX_REQUIRE_NEW);
			action1.next(action2).next(action3);
			action1.execute();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		String currentPath = SqlAction.class.getResource("").getPath();
		try {
			// 去除中文路径乱码的问题
			currentPath = java.net.URLDecoder.decode(currentPath, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Properties properties = new Properties();
		Connection conn = null;
		loadDbInfo(properties, currentPath + "datasource_mysql.properties");
		String driverName = properties.getProperty("driverName");
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		// 1.注册驱动
		Class.forName(driverName);
		// 2.获取数据库连接对象 导包选择java.sql
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static void loadDbInfo(Properties properties, String path) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(path);
		properties.load(fr);
		fr.close();
	}

}

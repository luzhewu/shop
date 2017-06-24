package cn.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.shop.tools.ConfigManager;

/**
 * 
 *获取数据库连接的工具类
 *@param connection	数据库连接对象
 * @param pstm			预编译语句对象
 * @param rs			结果集对象
 */
public class BaseDao {

	/**
	 * 获取数据库连接对象
	 * @return	connection对象
	 */
	public static Connection getConnection() {
		Connection connection = null;
		String driver = ConfigManager.getInstance().getValue("driver");
		String url = ConfigManager.getInstance().getValue("url");
		String user = ConfigManager.getInstance().getValue("user");
		String password = ConfigManager.getInstance().getValue("password");
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 查询操作
	 * @param connection
	 * @param pstm
	 * @param rs
	 * @param sql
	 * @param params
	 * @return	rs 查询得到的结果集
	 * @throws Exception 
	 */
	public static ResultSet execute(Connection connection,
			PreparedStatement pstm, ResultSet rs, String sql, Object... params)
			throws Exception {
		pstm = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pstm.setObject(i + 1, params[i]);
		}
		rs = pstm.executeQuery();
		return rs;
	}

	/**
	 * 更新操作
	 * @param connection
	 * @param pstm
	 * @param sql
	 * @param params
	 * @return	updateRows 受影响的行数
	 * @throws Exception
	 */
	public static int execute(Connection connection, PreparedStatement pstm,
			String sql, Object... params) throws Exception {
		int updateRows = 0;
		pstm = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pstm.setObject(i + 1, params[i]);
		}
		updateRows = pstm.executeUpdate();
		return updateRows;
	}

	/**
	 * 关闭资源
	 * @param connection	数据库连接对象
	 * @param pstm			预编译语句对象
	 * @param rs			结果集对象
	 * @return				关闭资源是否成功
	 */
	public static boolean closeSource(Connection connection,
			PreparedStatement pstm, ResultSet rs) {
		boolean flag = false;
		if (rs != null) {
			try {
				rs.close();
				rs = null;// GC回收
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
				pstm = null;// GC回收
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
				connection = null;// GC回收
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}

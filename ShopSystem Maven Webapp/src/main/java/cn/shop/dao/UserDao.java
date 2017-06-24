package cn.shop.dao;

import java.sql.Connection;
import java.util.List;

import cn.shop.pojo.User;

public interface UserDao {
	/**
	 * 增加操作
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(Connection connection, User user) throws Exception;

	/**
	 * 登錄功能---獲得user對象
	 * @param connection
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public User getLonginUser(Connection connection, String userCode)
			throws Exception;

	/**
	 * 通過userName查找對應的user集合
	 * @param pageNo 当前页码
	 * @param pageSize 每页显示条数
	 * @param connection
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList(Connection connection, String userName,
			int pageNo, int pageSize) throws Exception;

	/**
	 * 通过id删除数据
	 * @param connection
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public int deleteUserById(Connection connection, Integer delId)
			throws Exception;

	/**
	 * 通过id获取数据
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User getUserById(Connection connection, String id) throws Exception;

	/**
	 * 通过user对象更改数据
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int modify(Connection connection, User user) throws Exception;

	/**
	 * 修改当前密码
	 * @param connection
	 * @param id
	 * @param newPassword
	 * @return
	 */
	public boolean updatePwd(Connection connection, int id, String newPassword)
			throws Exception;

	/**
	 * 获得总记录数
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int getCount(Connection connection, String userName)
			throws Exception;
}

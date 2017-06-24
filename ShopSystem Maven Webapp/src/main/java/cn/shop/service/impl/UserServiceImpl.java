package cn.shop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shop.dao.BaseDao;
import cn.shop.dao.UserDao;
import cn.shop.pojo.User;
import cn.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public boolean add(User user) {

		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);// 开启JDBC事务
			int updateRows = userDao.add(connection, user);
			connection.commit();
			if (updateRows > 0) {
				flag = true;
				System.out.println("update success");
			} else {
				System.out.println("update failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				System.out.println("rollback=================");
				connection.rollback();// 事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return flag;
	}

	@Override
	public User login(String userCode, String userPassword) {
		Connection connection = null;
		User user = null;
		try {
			connection = BaseDao.getConnection();
			user = userDao.getLonginUser(connection, userCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		// 匹配密码
		if (user != null) {
			if (!user.getUserPassword().equals(userPassword)) {
				user = null;
			}
		}
		return user;
	}

	@Override
	public List<User> getuserList(String userName, int pageNo, int pageSize) {
		Connection connection = null;
		List<User> userList = new ArrayList<>();

		try {
			connection = BaseDao.getConnection();
			userList = userDao.getUserList(connection, userName, pageNo,
					pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return userList;
	}

	@Override
	public User selectUserCodeExist(String userCode) {
		User user = null;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			user = userDao.getLonginUser(connection, userCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return user;
	}

	@Override
	public boolean deleteUserById(Integer delId) {
		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);// 开启事务
			int execute = userDao.deleteUserById(connection, delId);
			connection.commit();
			if (execute > 0) {
				flag = true;
				System.out.println("delete success");
			} else {
				System.out.println("delete failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return flag;
	}

	@Override
	public User getUserById(String id) {
		User user = null;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			user = userDao.getUserById(connection, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return user;
	}

	@Override
	public boolean modify(User user) {
		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			int execute = userDao.modify(connection, user);
			connection.commit();
			if (execute > 0) {
				flag = true;
				System.out.println("modify success");
			} else {
				System.out.println("modify false");
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return flag;
	}

	@Override
	public boolean updatePwd(int id, String newPassword) {
		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			flag = userDao.updatePwd(connection, id, newPassword);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return flag;
	}

	@Override
	public int getCount(String userName) {
		int count = 0;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			count = userDao.getCount(connection, userName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return count;
	}
}

package cn.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.shop.dao.BaseDao;
import cn.shop.dao.UserDao;
import cn.shop.pojo.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public int add(Connection connection, User user) throws Exception {
		PreparedStatement pstm = null;
		int updateRows = 0;
		if (connection != null) {
			String sql = "insert into shop_user (userCode,userName,userPassword,"
					+ "phone,address,userType,birthday,createdBy,creationDate,gender) values(?,?,"
					+ "?,?,?,?,?,?,?,?)";
			Object[] params = { user.getUserCode(), user.getUserName(),
					user.getUserPassword(), user.getPhone(), user.getAddress(),
					user.getUserType(), user.getBirthday(),
					user.getCreatedBy(), user.getCreationDate(),
					user.getGender() };
			updateRows = BaseDao.execute(connection, pstm, sql, params);
			BaseDao.closeSource(null, pstm, null);
		}
		return updateRows;
	}

	@Override
	public User getLonginUser(Connection connection, String userCode)
			throws Exception {
		User user = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (connection != null) {
			String sql = "select * from shop_user where userCode=?";
			Object[] params = { userCode };
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserCode(rs.getString("userCode"));
				user.setUserName(rs.getString("userName"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setGender(rs.getInt("gender"));
				user.setBirthday(rs.getDate("birthday"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setUserType(rs.getInt("userType"));
				user.setCreatedBy(rs.getInt("createdBy"));
				user.setCreationDate(rs.getTimestamp("creationDate"));
				user.setModifyBy(rs.getInt("modifyBy"));
				user.setModifyDate(rs.getTimestamp("modifyDate"));
			}
			BaseDao.closeSource(null, pstm, rs);
		}
		return user;
	}

	@Override
	public List<User> getUserList(Connection connection, String userName,
			int pageNo, int pageSize) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		pageNo = (pageNo - 1) * pageSize;
		String sql = "select * from shop_user where userName like ? and userName is not null ORDER BY creationDate DESC LIMIT ?, ? ;";
		Object[] params = { "%" + userName + "%", pageNo, pageSize };
		List<User> userList = new ArrayList<>();
		if (connection != null) {
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			while (rs.next()) {
				User _user = new User();
				_user.setId(rs.getInt("id"));
				_user.setBirthday(rs.getDate("birthday"));
				_user.setUserCode(rs.getString("userCode"));
				_user.setUserName(rs.getString("userName"));
				_user.setUserType(rs.getInt("userType"));
				_user.setPhone(rs.getString("phone"));
				_user.setGender(rs.getInt("gender"));
				userList.add(_user);
			}
		}
		BaseDao.closeSource(null, pstm, rs);
		return userList;
	}

	@Override
	public int deleteUserById(Connection connection, Integer delId)
			throws Exception {
		int execute = 0;
		PreparedStatement pstm = null;
		String sql = "delete from shop_user where id=?";
		Object[] params = { delId };
		if (connection != null) {
			execute = BaseDao.execute(connection, pstm, sql, params);
		}
		BaseDao.closeSource(null, pstm, null);
		return execute;
	}

	@Override
	public User getUserById(Connection connection, String id) throws Exception {
		User _user = null;
		String sql = "select * from shop_user where id=?";
		Object[] params = { Integer.parseInt(id) };
		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet rs = null;
		rs = BaseDao.execute(connection, pstm, rs, sql, params);
		if (rs.next()) {
			_user = new User();
			_user.setId(rs.getInt("id"));
			_user.setBirthday(rs.getDate("birthday"));
			_user.setUserCode(rs.getString("userCode"));
			_user.setUserName(rs.getString("userName"));
			_user.setUserType(rs.getInt("userType"));
			_user.setPhone(rs.getString("phone"));
			_user.setGender(rs.getInt("gender"));
			_user.setAddress(rs.getString("address"));
		}
		BaseDao.closeSource(null, pstm, rs);
		return _user;
	}

	@Override
	public int modify(Connection connection, User user) throws Exception {
		int execute = 0;
		String sql = "UPDATE shop_user SET `userName`=? ,`gender`=? ,"
				+ "`birthday`=?, `phone`=?, `userType`=?, `modifyBy`=?, `modifyDate`=?"
				+ " WHERE id=?";
		Object[] params = { user.getUserName(), user.getGender(),
				user.getBirthday(), user.getPhone(), user.getUserType(),
				user.getModifyBy(), user.getModifyDate(), user.getId() };
		PreparedStatement pstm = connection.prepareStatement(sql);
		execute = BaseDao.execute(connection, pstm, sql, params);
		BaseDao.closeSource(null, pstm, null);
		return execute;
	}

	@Override
	public boolean updatePwd(Connection connection, int id, String newPassword)
			throws Exception {
		boolean flag = false;
		PreparedStatement pstm = null;
		if (connection != null) {
			String sql = "update shop_user set userPassword = ? where id = ?";
			Object[] params = { newPassword, id };
			if (BaseDao.execute(connection, pstm, sql, params) > 0) {
				flag = true;
			}
			BaseDao.closeSource(null, pstm, null);
		}
		return flag;
	}

	@Override
	public int getCount(Connection connection, String userName)
			throws Exception {
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (connection != null) {
			String sql = "select count(1) AS 'count' from shop_user where userName like ? and userName is not null ;";
			Object[] params = { "%" + userName + "%" };
			if (connection != null) {
				rs = BaseDao.execute(connection, pstm, rs, sql, params);
				if (rs.next()) {
					count = rs.getInt("count");
				}
			}
			BaseDao.closeSource(null, pstm, rs);
		}
		return count;
	}

}

package cn.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.shop.dao.BaseDao;
import cn.shop.dao.ProviderDao;
import cn.shop.pojo.Provider;

@Repository
public class ProviderDaoImpl implements ProviderDao {

	@Override
	public boolean deleteProviderById(Connection connection, Integer delId)
			throws Exception {
		boolean flag = false;
		PreparedStatement pstm = null;
		String sql = "delete from shop_provider where id = ?";
		Object[] params = { delId };
		if (connection != null) {
			if (BaseDao.execute(connection, pstm, sql, params) > 0) {
				flag = true;
			}
		}
		BaseDao.closeSource(null, pstm, null);
		return flag;
	}

	@SuppressWarnings("null")
	@Override
	public Provider getProviderById(Connection connection, int id)
			throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from shop_provider where id = ?";
		Object[] params = { id };
		Provider provider = null;
		if (connection != null) {
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			if (rs.next()) {
				provider = new Provider();
				provider.setId(rs.getInt("id"));
				provider.setProCode(rs.getString("proCode"));
				provider.setProName(rs.getString("proName"));
				provider.setProDesc(rs.getString("proDesc"));
				provider.setProContact(rs.getString("proContact"));
				provider.setProPhone(rs.getString("proPhone"));
				provider.setProAddress(rs.getString("proAddress"));
				provider.setProFax(rs.getString("proFax"));
				provider.setCreatedBy(rs.getInt("createdBy"));
				provider.setModifyBy(rs.getInt("modifyBy"));
				provider.setCreationDate(rs.getTimestamp("creationDate"));
				provider.setModifyDate(rs.getTimestamp("modifyDate"));
			}
		}
		BaseDao.closeSource(null, pstm, rs);
		return provider;
	}

	@Override
	public boolean modifyProvider(Connection connection, Provider provider)
			throws Exception {
		PreparedStatement pstm = null;
		boolean flag = false;
		String sql = "update shop_provider set `proCode`= ? ,`proDesc`= ? ,"
				+ " `proContact` = ? , `proPhone`=?,`proAddress`=?,"
				+ "`proFax`=?,`modifyBy`=?,`modifyDate`=? where id=?";
		Object[] params = { provider.getProCode(), provider.getProDesc(),
				provider.getProContact(), provider.getProPhone(),
				provider.getProAddress(), provider.getProFax(),
				provider.getModifyBy(), provider.getModifyDate(),
				provider.getId() };
		if (connection != null) {
			if (BaseDao.execute(connection, pstm, sql, params) > 0) {
				flag = true;
				System.out.println("provider更新成功");
			} else {
				System.out.println("provider更新失败");
			}
		}
		BaseDao.closeSource(null, pstm, null);
		return flag;
	}

	@Override
	public boolean addProvider(Connection connection, Provider provider)
			throws Exception {
		PreparedStatement pstm = null;
		boolean flag = false;
		String sql = "insert into shop_provider(proCode,proName,proDesc,"
				+ "proContact,proPhone,proAddress,proFax,createdBy,"
				+ "creationDate) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = { provider.getProCode(), provider.getProName(),
				provider.getProDesc(), provider.getProContact(),
				provider.getProPhone(), provider.getProAddress(),
				provider.getProFax(), provider.getCreatedBy(),
				provider.getCreationDate() };
		if (connection != null) {
			if (BaseDao.execute(connection, pstm, sql, params) > 0) {
				flag = true;
				// System.out.println("provider增加成功");
			} else {
				// System.out.println("provider增加失败");
			}
		}
		BaseDao.closeSource(null, pstm, null);
		return flag;
	}

	@Override
	public List<Provider> getProviderList(Connection connection,
			Provider provider, int pageNo, int pageSize) throws Exception {
		PreparedStatement pstm = null;
		pageNo = (pageNo - 1) * pageSize;
		List<Provider> providerList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shop_provider where proName like ? ORDER BY creationDate DESC LIMIT ?, ? ;";
		Object[] params = { "%" + provider.getProName() + "%", pageNo, pageSize };
		if (connection != null) {
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			Provider _provider = null;
			while (rs.next()) {
				_provider = new Provider();
				_provider.setId(rs.getInt("id"));
				_provider.setProCode(rs.getString("proCode"));
				_provider.setProName(rs.getString("proName"));
				_provider.setProDesc(rs.getString("proDesc"));
				_provider.setProContact(rs.getString("proContact"));
				_provider.setProPhone(rs.getString("proPhone"));
				_provider.setProAddress(rs.getString("proAddress"));
				_provider.setProFax(rs.getString("proFax"));
				_provider.setCreationDate(rs.getTimestamp("creationDate"));
				_provider.setCreatedBy(rs.getInt("createdBy"));
				_provider.setModifyBy(rs.getInt("modifyBy"));
				_provider.setModifyDate(rs.getTimestamp("modifyDate"));
				providerList.add(_provider);
			}
		}
		BaseDao.closeSource(null, pstm, rs);
		return providerList;
	}

	@Override
	public List<Provider> getProviderList(Connection connection, String proName)
			throws Exception {
		PreparedStatement pstm = null;
		List<Provider> providerList = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from shop_provider where proName like ?;";
		Object[] params = { "%" + proName + "%" };
		if (connection != null) {
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			Provider provider = null;
			while (rs.next()) {
				provider = new Provider();
				provider.setId(rs.getInt("id"));
				provider.setProCode(rs.getString("proCode"));
				provider.setProName(rs.getString("proName"));
				provider.setProDesc(rs.getString("proDesc"));
				provider.setProContact(rs.getString("proContact"));
				provider.setProPhone(rs.getString("proPhone"));
				provider.setProAddress(rs.getString("proAddress"));
				provider.setProFax(rs.getString("proFax"));
				provider.setCreationDate(rs.getTimestamp("creationDate"));
				provider.setCreatedBy(rs.getInt("createdBy"));
				provider.setModifyBy(rs.getInt("modifyBy"));
				provider.setModifyDate(rs.getTimestamp("modifyDate"));
				providerList.add(provider);
			}
		}
		BaseDao.closeSource(null, pstm, rs);
		return providerList;
	}

	@Override
	public Provider selectProviderExist(Connection connection, String proCode)
			throws Exception {
		PreparedStatement pstm = null;
		Provider provider = null;
		ResultSet rs = null;
		String sql = "select * from shop_provider where proCode = ?";
		Object[] params = { proCode };
		if (connection != null) {
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			if (rs.next()) {
				provider = new Provider();
				provider.setId(rs.getInt("id"));
				provider.setProCode(rs.getString("proCode"));
				provider.setProName(rs.getString("proName"));
				provider.setProDesc(rs.getString("proDesc"));
				provider.setProContact(rs.getString("proContact"));
				provider.setProPhone(rs.getString("proPhone"));
				provider.setProAddress(rs.getString("proAddress"));
				provider.setProFax(rs.getString("proFax"));
				provider.setCreationDate(rs.getTimestamp("creationDate"));
				provider.setCreatedBy(rs.getInt("createdBy"));
				provider.setModifyBy(rs.getInt("modifyBy"));
				provider.setModifyDate(rs.getTimestamp("modifyDate"));
			}
		}
		BaseDao.closeSource(null, pstm, rs);
		return provider;
	}

	@Override
	public int getCount(Connection connection, Provider provider)
			throws Exception {
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (connection != null) {
			String sql = "select count(1) AS 'count' from shop_provider where proName like ?;";
			Object[] params = { "%" + provider.getProName() + "%" };
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

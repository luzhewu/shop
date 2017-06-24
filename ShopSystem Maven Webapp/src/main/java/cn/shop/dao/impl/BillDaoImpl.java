package cn.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.shop.dao.BaseDao;
import cn.shop.dao.BillDao;
import cn.shop.pojo.Bill;

import com.mysql.jdbc.StringUtils;

@Repository
public class BillDaoImpl implements BillDao {

	@Override
	public List<Bill> getBillList(Connection connection, Bill bill, int pageNo,
			int pageSize) throws Exception {
		List<Bill> billList = new ArrayList<>();
		pageNo = (pageNo - 1) * pageSize;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (connection != null) {
			// 拼接sql语句和params
			StringBuffer sql = new StringBuffer();

			List<Object> list = new ArrayList<>();
			sql.append("select b.*,p.proName as providerName"
					+ " from shop_bill b,shop_provider p where b.providerId=p.id");
			if (!StringUtils.isNullOrEmpty(bill.getProductName())) {
				sql.append(" and productName like ? ");
				list.add("%" + bill.getProductName() + "%");
			}
			if (bill.getProviderId() > 0) {
				sql.append(" and providerId = ? ");
				list.add(bill.getProviderId());
			}
			if (bill.getIsPayment() > 0) {
				sql.append(" and isPayment = ? ");
				list.add(bill.getIsPayment());
			}
			list.add(pageNo);
			list.add(pageSize);
			Object[] params = list.toArray();
			sql.append(" ORDER BY creationDate DESC LIMIT ?, ? ;");
			rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
			Bill _bill = null;
			while (rs.next()) {
				_bill = new Bill();
				_bill.setId(rs.getInt("id"));
				_bill.setBillCode(rs.getString("billCode"));
				_bill.setProductName(rs.getString("productName"));
				_bill.setProductDesc(rs.getString("productDesc"));
				_bill.setProductUnit(rs.getString("productUnit"));
				_bill.setProductCount(rs.getBigDecimal("productCount"));
				_bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
				_bill.setProviderId(rs.getInt("providerId"));
				_bill.setProviderName(rs.getString("providerName"));
				_bill.setIsPayment(rs.getInt("isPayment"));
				_bill.setCreatedBy(rs.getInt("createdBy"));
				_bill.setModifyBy(rs.getInt("modifyBy"));
				_bill.setCreationDate(rs.getTimestamp("creationDate"));
				_bill.setModifyDate(rs.getTimestamp("modifyDate"));
				billList.add(_bill);
			}
			BaseDao.closeSource(null, pstm, rs);
		}
		return billList;
	}

	@Override
	public int getBillCountProviderId(Connection connection, Integer providerId)
			throws Exception {
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (null != connection) {
			String sql = "select count(1) as billCount from shop_bill where providerId = ?";
			Object[] params = { providerId };
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			if (rs.next()) {
				count = rs.getInt("billCount");
			}
			BaseDao.closeSource(null, pstm, rs);
		}
		return count;
	}

	@Override
	public boolean addBill(Connection connection, Bill bill) throws Exception {
		boolean flag = false;
		PreparedStatement pstm = null;
		String sql = "insert into shop_bill(billCode,productName,productUnit,"
				+ "productCount,totalPrice,providerId,isPayment,createdBy"
				+ ",creationDate) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = { bill.getBillCode(), bill.getProductName(),
				bill.getProductUnit(), bill.getProductCount(),
				bill.getTotalPrice(), bill.getProviderId(),
				bill.getIsPayment(), bill.getCreatedBy(),
				bill.getCreationDate() };
		if (connection != null) {
			if (BaseDao.execute(connection, pstm, sql, params) > 0) {
				flag = true;
			} else {
				flag = true;
			}
		}
		BaseDao.closeSource(null, pstm, null);
		return flag;
	}

	@Override
	public boolean modifyBill(Connection connection, Bill bill)
			throws Exception {
		boolean flag = false;
		PreparedStatement pstm = null;
		String sql = "update shop_bill set billCode=?,productName=?,productUnit=?,productCount=?,"
				+ "totalPrice=?,providerId=?,isPayment=?,"
				+ "modifyBy=?,modifyDate=? where id=?";
		Object[] params = { bill.getBillCode(), bill.getProductName(),
				bill.getProductUnit(), bill.getProductCount(),
				bill.getTotalPrice(), bill.getProviderId(),
				bill.getIsPayment(), bill.getModifyBy(), bill.getModifyDate(),
				bill.getId() };
		if (connection != null) {
			if (BaseDao.execute(connection, pstm, sql, params) > 0) {
				flag = true;
			} else {
				flag = true;
			}
		}
		BaseDao.closeSource(null, pstm, null);
		return flag;
	}

	@Override
	public boolean deleteBill(Connection connection, Bill bill)
			throws Exception {
		boolean flag = false;
		PreparedStatement pstm = null;
		String sql = "delete from shop_bill where id=?";
		Object[] params = { bill.getId() };
		if (connection != null) {
			if (BaseDao.execute(connection, pstm, sql, params) > 0) {
				flag = true;
			} else {
				flag = true;
			}
		}
		BaseDao.closeSource(null, pstm, null);
		return flag;
	}

	@Override
	public Bill getBillById(Connection connection, Integer id) throws Exception {
		Bill bill = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (connection != null) {
			String sql = "select b.*,p.proName as providerName"
					+ " from shop_bill b,shop_provider p where b.providerId=p.id and b.id=?";
			Object[] params = { id };
			rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
			while (rs.next()) {
				bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setBillCode(rs.getString("billCode"));
				bill.setProductName(rs.getString("productName"));
				bill.setProductDesc(rs.getString("productDesc"));
				bill.setProductUnit(rs.getString("productUnit"));
				bill.setProductCount(rs.getBigDecimal("productCount"));
				bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
				bill.setProviderId(rs.getInt("providerId"));
				bill.setProviderName(rs.getString("providerName"));
				bill.setIsPayment(rs.getInt("isPayment"));
				bill.setModifyBy(rs.getInt("modifyBy"));
				bill.setModifyDate(rs.getTimestamp("modifyDate"));
			}
			BaseDao.closeSource(null, pstm, rs);
		}
		return bill;
	}

	@Override
	public Bill selectBillExist(Connection connection, String billCode)
			throws Exception {
		Bill bill = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (connection != null) {
			String sql = "select * from shop_bill  where billCode=?";
			Object[] params = { billCode };
			rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
			while (rs.next()) {
				bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setBillCode(rs.getString("billCode"));
			}
			BaseDao.closeSource(null, pstm, rs);
		}
		return bill;
	}

	@Override
	public int getCount(Connection connection, Bill bill) throws Exception {
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (connection != null) {
			// 拼接sql语句和params
			StringBuffer sql = new StringBuffer();

			List<Object> list = new ArrayList<>();
			sql.append("select count(1) AS 'count' from shop_bill where 1=1");
			if (!StringUtils.isNullOrEmpty(bill.getProductName())) {
				sql.append(" and productName like ? ");
				list.add("%" + bill.getProductName() + "%");
			}
			if (bill.getProviderId() > 0) {
				sql.append(" and providerId = ? ");
				list.add(bill.getProviderId());
			}
			if (bill.getIsPayment() > 0) {
				sql.append(" and isPayment = ? ");
				list.add(bill.getIsPayment());
			}
			Object[] params = list.toArray();
			rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
			if (rs.next()) {
				count = rs.getInt("count");
			}
			BaseDao.closeSource(null, pstm, rs);
		}
		return count;
	}
}

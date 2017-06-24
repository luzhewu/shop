package cn.shop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shop.dao.BaseDao;
import cn.shop.dao.BillDao;
import cn.shop.pojo.Bill;
import cn.shop.service.BillService;

@Service
public class BillServiceImpl implements BillService {
	@Resource
	private BillDao billDao = null;

	@Override
	public boolean addBill(Bill bill) {
		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			flag = billDao.addBill(connection, bill);
			connection.commit();
			if (flag) {
				System.out.println("增加成功");
			} else {
				System.out.println("增加失败");
			}
		} catch (Exception e) {
			try {
				connection.rollback();
				flag = false;
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
	public boolean modifyBill(Bill bill) {
		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			flag = billDao.modifyBill(connection, bill);
			connection.commit();
			if (flag) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			try {
				connection.rollback();
				flag = false;
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
	public boolean deleteBill(Bill bill) {
		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			flag = billDao.deleteBill(connection, bill);
			connection.commit();
			if (flag) {
				System.out.println("删除成功");
			} else {
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			try {
				connection.rollback();
				flag = false;
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
	public List<Bill> getBillList(Bill bill, int pageNo, int pageSize) {
		List<Bill> billList = new ArrayList<>();
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			billList = billDao.getBillList(connection, bill, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return billList;
	}

	@Override
	public Bill getBillById(Integer id) {
		Connection connection = null;
		Bill bill = null;
		try {
			connection = BaseDao.getConnection();
			bill = billDao.getBillById(connection, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return bill;
	}

	@Override
	public Bill selectBillExist(String billCode) {
		Connection connection = null;
		Bill bill = null;
		try {
			connection = BaseDao.getConnection();
			bill = billDao.selectBillExist(connection, billCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return bill;
	}

	@Override
	public int getCount(Bill bill) {
		int count = 0;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			count = billDao.getCount(connection, bill);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return count;
	}

}

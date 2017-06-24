package cn.shop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shop.dao.BaseDao;
import cn.shop.dao.BillDao;
import cn.shop.dao.ProviderDao;
import cn.shop.pojo.Provider;
import cn.shop.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	@Resource
	private ProviderDao providerDao;
	@Resource
	private BillDao billDao;

	@Override
	/**
	 * 业务：根据ID删除供应商表的数据之前，需要先去订单表里进行查询操作
	 * 若表中无该供应商的订单数据，则可以删除
	 * 若有该供应商的订单数据，则不可以删除
	 * 1 billCount ==  0  删除 ---1 成功（0） 2 不成功（-1）
	 * 2 billCount >   0 不能删除 查询成功（0）查询不成功（-1）
	 * 
	 * --判断--如果billCount = -1 失败
	 * 若billCount >=0  成功
	 */
	public int deleteProviderById(Integer delId) {
		Connection connection = null;
		int billCount = -1;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			billCount = billDao.getBillCountProviderId(connection, delId);
			if (billCount == 0) {
				providerDao.deleteProviderById(connection, delId);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				billCount = -1;
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return billCount;
	}

	@Override
	public Provider getProviderById(int id) {
		Provider provider = null;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			provider = providerDao.getProviderById(connection, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return provider;
	}

	@Override
	public boolean modifyProvider(Provider provider) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			flag = providerDao.modifyProvider(connection, provider);
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
	public boolean addProvider(Provider provider) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			flag = providerDao.addProvider(connection, provider);
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
	public List<Provider> getProviderList(Provider provider, int pageNo,
			int pageSize) {
		List<Provider> providerList = new ArrayList<>();
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			providerList = providerDao.getProviderList(connection, provider,
					pageNo, pageSize);
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
		return providerList;
	}

	@Override
	public List<Provider> getProviderList(String proName) {
		List<Provider> providerList = new ArrayList<>();
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			providerList = providerDao.getProviderList(connection, proName);
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
		return providerList;
	}

	@Override
	public Provider selectProCodeExist(String proCode) {
		Provider provider = new Provider();
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);
			provider = providerDao.selectProviderExist(connection, proCode);
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
		return provider;
	}

	@Override
	public int getCount(Provider provider) {
		int count = 0;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			count = providerDao.getCount(connection, provider);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeSource(connection, null, null);
		}
		return count;
	}
}

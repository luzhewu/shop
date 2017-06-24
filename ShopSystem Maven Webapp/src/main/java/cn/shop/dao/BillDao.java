package cn.shop.dao;

import java.sql.Connection;
import java.util.List;

import cn.shop.pojo.Bill;

public interface BillDao {
	/**
	 * 根据前台条件查询供应商列表
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	List<Bill> getBillList(Connection connection, Bill bill, int pageNo,
			int pageSize) throws Exception;

	/**
	 * 根据供应商id查询供应商的id查询订单表是否对应相应的供应商
	 * @param connection
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	int getBillCountProviderId(Connection connection, Integer providerId)
			throws Exception;

	/**
	 * 增加商品信息
	 * @return
	 * @throws Exception
	 */
	boolean addBill(Connection connection, Bill bill) throws Exception;

	/**
	 * 修改商品信息
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	boolean modifyBill(Connection connection, Bill bill) throws Exception;

	/**
	 * 删除商品信息
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	boolean deleteBill(Connection connection, Bill bill) throws Exception;

	Bill getBillById(Connection connection, Integer id) throws Exception;

	Bill selectBillExist(Connection connection, String billCode)
			throws Exception;

	int getCount(Connection connection, Bill bill) throws Exception;
}

package cn.shop.service;

import java.util.List;

import cn.shop.pojo.Bill;

public interface BillService {
	/**
	 * 增加商品信息
	 * @param bill
	 * @return
	 */
	boolean addBill(Bill bill);

	/**
	 * 修改商品信息
	 * @param bill
	 * @return
	 */
	boolean modifyBill(Bill bill);

	/**
	 * 删除商品信息
	 * @param bill
	 * @return
	 */
	boolean deleteBill(Bill bill);

	/**
	 * 查询商品列表
	 * @param bill
	 * @return
	 */
	List<Bill> getBillList(Bill bill, int pageNo, int pageSize);

	/**
	 * 通过id查询账单
	 * @param parseInt
	 * @return
	 */
	Bill getBillById(Integer parseInt);

	Bill selectBillExist(String billCode);

	int getCount(Bill bill);

}

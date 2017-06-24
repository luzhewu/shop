package cn.shop.dao;

import java.sql.Connection;
import java.util.List;

import cn.shop.pojo.Provider;

public interface ProviderDao {
	/**
	 * 根据id删除供应商
	 * @param connection
	 * @param delId
	 * @return
	 */
	boolean deleteProviderById(Connection connection, Integer delId)
			throws Exception;

	/**
	 * 根据id获取该供应商
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Provider getProviderById(Connection connection, int id) throws Exception;

	/**
	 * 修改供应商明细
	 * @param connection
	 * @param provider
	 * @return
	 * @throws Exception
	 */
	boolean modifyProvider(Connection connection, Provider provider)
			throws Exception;

	/**
	 * 增加供应商
	 * @param connection
	 * @param provider
	 * @return
	 * @throws Exception
	 */
	boolean addProvider(Connection connection, Provider provider)
			throws Exception;

	/**
	 * 通过前台信息，查询供应商列表明细
	 * @param connection
	 * @param provider
	 * @return
	 * @throws Exception
	 */
	List<Provider> getProviderList(Connection connection, Provider provider,
			int pageNo, int pageSize) throws Exception;

	List<Provider> getProviderList(Connection connection, String proName)
			throws Exception;

	Provider selectProviderExist(Connection connection, String proCode)
			throws Exception;

	int getCount(Connection connection, Provider provider) throws Exception;
}

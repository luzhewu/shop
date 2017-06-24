package cn.shop.service;

import java.util.List;

import cn.shop.pojo.Provider;

public interface ProviderService {
	/**
	 * 根据ID删除供应商表数据
	 * @param delId
	 * @return
	 */
	int deleteProviderById(Integer delId);

	Provider getProviderById(int id);

	boolean modifyProvider(Provider provider);

	boolean addProvider(Provider provider);

	List<Provider> getProviderList(Provider provider, int pageNo, int pageSize);

	List<Provider> getProviderList(String proName);

	Provider selectProCodeExist(String proCode);

	int getCount(Provider provider);
}

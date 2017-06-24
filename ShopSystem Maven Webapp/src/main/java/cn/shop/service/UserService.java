package cn.shop.service;

import java.util.List;

import cn.shop.pojo.User;

public interface UserService {
	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	boolean add(User user);

	/**
	 * 用户登录，获取User对象
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	User login(String userCode, String userPassword);

	/**
	 * 通过用户名获取用户集合
	 * @param pageNo 当前多少页
	 * @param pageSize 每页显示条数
	 * @param userName
	 * @return
	 */
	List<User> getuserList(String userName, int pageNo, int pageSize);

	/**
	 * 通过userCode查询数据库中是否存在此userCode
	 * @param userCode
	 * @return
	 */
	User selectUserCodeExist(String userCode);

	/**
	 * 判断删除的id是否存在
	 * @param delId
	 * @return
	 */
	boolean deleteUserById(Integer delId);

	/**
	 * 通过id获得user对象
	 * @param id
	 * @return
	 */
	User getUserById(String id);

	boolean modify(User user);

	/**
	 * 
	 * @param id 要修改的用户的id
	 * @param newPassword修改的密码
	 * @return
	 */
	boolean updatePwd(int id, String newPassword);

	/**
	 * 获取总条数	
	 * @return
	 */
	int getCount(String userName);

}

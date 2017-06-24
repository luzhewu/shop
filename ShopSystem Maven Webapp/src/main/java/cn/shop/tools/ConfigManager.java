package cn.shop.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的工具类------单例模式
 * @author Administrator
 *@param configManager 单例模式工具类实例对象
 *@param properties 读取配置文件对象
 */
public class ConfigManager {
	private static ConfigManager configManager;
	private static Properties properties;

	/**
	 * 私有构造器----读取配置文件
	 */
	private ConfigManager() {
		String configFile = "database.properties";
		properties = new Properties();
		// 通过classpath找资源
		InputStream is = ConfigManager.class.getClassLoader()
				.getResourceAsStream(configFile);
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提供一个全局访问点
	 * @return	ConfigManager 单例模式之双重锁，返回该类型的实例
	 */
	public static ConfigManager getInstance() {
		if (configManager == null) {
			synchronized (ConfigManager.class) {
				if (configManager == null) {
					configManager = new ConfigManager();
				}
			}
		}
		return configManager;
	}

	/**
	 * 
	 * @param key 配置文件的key值
	 * @return	配置文件的相关信息value
	 */
	public static String getValue(String key) {
		return properties.getProperty(key);
	}
}

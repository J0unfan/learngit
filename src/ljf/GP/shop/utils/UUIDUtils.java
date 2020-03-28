package ljf.GP.shop.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * 
 * @author ljf
 */
public class UUIDUtils {
	/**
	 * 生成随机字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}

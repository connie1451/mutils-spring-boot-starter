package cn.minsin.core.tools;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

	// js提交到后台可能出现的错误情况,但提交上的值并不是null或""
	private static final String[] keys = { "undefined", "null" };

	/**
	 * 检查是否缺少参数
	 *
	 * @param param 需要检查的为空的参数们
	 * @return true 缺少 false 不缺少
	 * @date 2018年6月11日
	 */
	public static boolean isBlank(Object... param) {
		if (param == null || param.length == 0) {
			return false;
		}
		for (Object object : param) {
			if (object == null || "".equals(object) || object.toString().length() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否全部为空
	 *
	 * @param param 需要检查的为空的参数们
	 * @return true 缺少 false 不缺少
	 * @date 2018年6月11日
	 * @author mintonzhang@163.com
	 */
	public static boolean isAllBlank(Object... param) {
		if (param == null || param.length == 0) {
			return false;
		}
		for (Object object : param) {
			if (object != null && !"".equals(object) && object.toString().length() >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 关键字去空格及过滤
	 * 
	 * @param str
	 * @param filterKey
	 * @return 2018年7月27日
	 */
	public static final String filterSearchKey(String str, String... filterKey) {
		str = filterSpace(str);
		if (filterKey != null && filterKey.length > 0 && str != null) {
			for (String string : filterKey) {
				if (string.equals(str))
					return null;
			}
		}
		return str;
	}

	/**
	 * 去除两端空格
	 * 
	 * @param str
	 * @return 2018年9月21日
	 */
	public static final String filterSpace(String str) {
		str = StringUtils.isBlank(str) ? null : str.trim();
		for (String key : keys) {
			if (key.equals(str))
				return null;
		}
		return str;
	}

	/**
	 * 去除所有空格
	 * 
	 * @param str
	 * @return 2018年9月21日
	 */
	public static final String filterAllSpace(String str) {
		return filterSpace(str) == null ? null : str.replace(" ", "");
	}

	/**
	 * 关键字 模糊查询
	 * 
	 * @param key
	 * @param type -1 为%key 0为 %key% 1为key% 其他默认为0
	 * @return 2018年7月27日
	 */
	public static final String likeSearch(String key, Integer type) {

		key = filterSearchKey(key);
		if (key != null) {
			return type == -1 ? "%" + key : type == 1 ? key + "%" : "%" + key + "%";
		}
		return null;
	}

	/**
	 * 关键字 模糊查询
	 *
	 * @param key
	 * @param type -1 为%key 0为 %key% 1为key% 其他默认为0
	 * @return 2018年7月27日
	 */
	public static final String likeSearch(String key, String filterKey, Integer type) {

		key = filterSearchKey(key, filterKey);
		if (key != null) {
			return type == -1 ? "%" + key : type == 1 ? key + "%" : "%" + key + "%";
		}
		return null;
	}

	/**
	 * 生成length位随机UUID随机主键 不得超过32位或小于1,不满足条件默认返回32位长度
	 * 
	 * @return 2018年9月12日
	 */
	public static final String getUUIDForLength(int length) {
		if (length < 1 || length > 32) {
			length = 32;
		}
		return UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, length);
	}

	/**
	 * 如果字符串中有中文的分号 替换为英文的分号
	 * 
	 * @param str
	 * @return 2018年9月30日
	 */
	public static final String replaceSemicolonToEnglish(String str) {
		if (StringUtil.isBlank(str))
			return null;
		return str.replace("；", ";");
	}

	/**
	 * 如果字符串中有中文的冒号 替换为英文的冒号
	 * 
	 * @param str
	 * @return 2018年9月30日
	 */
	public static final String replaceColonToEnglish(String str) {
		if (StringUtil.isBlank(str))
			return null;
		return str.replace("：", ":");
	}

	/**
	 * 判断字符串是否超长
	 *
	 * @param str       待定字符串
	 * @param length    长度
	 * @param allowNull 是否可以为空
	 * @return true为不满足条件 false为满足条件 2018年9月8日
	 */
	public static final boolean checkStringLength(String str, int length, boolean allowNull) {
		if (StringUtil.isBlank(str)) {
			return !allowNull;
		}
		return str.length() > length;
	}

	/**
	 * 替换下划线 并且把下划线后的第一个单词大写
	 * 
	 * @param str
	 * @return
	 */
	public static String relaceUnderline(String str) {
		if (StringUtil.isBlank(str))
			return "";
		String[] split = str.split("_");
		for (int i = 0; i < split.length; i++) {
			split[i] = firstCharacterToUpper(split[i]);
		}
		return String.join("", split);
	}

	/**
	 * 替换下划线 并且把下划线后的第一个单词大写
	 * 
	 * @param str
	 * @return
	 */
	public static String relaceUnderline(String[] str) {

		for (int i = 0; i < str.length; i++) {
			str[i] = firstCharacterToUpper(str[i]);
		}
		return String.join("", str);
	}

	/**
	 * 首字母大写
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String firstCharacterToUpper(String srcStr) {
		return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
	}

	/**
	 * 删除格式
	 * 
	 * @param str
	 * @return
	 */
	public static String removeFormat(String str) {
		str = StringUtil.filterAllSpace(str);
		if (str == null)
			return null;
		return str.replace("\r", "").replace("\n", "").replace("\t", "");
	}

	/**
	 * 字符串是否包含中文
	 * 
	 * @param str 待校验字符串
	 * @return true 包含中文字符 false 不包含中文字符
	 */
	public static boolean isContainChinese(String str) {

		if (StringUtil.isBlank(str))
			return false;
		Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
		Matcher m = p.matcher(str);
		return m.find();
	}
}

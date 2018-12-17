package cn.minsin.core.tools;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtil extends NumberUtils {

	public static boolean isNumbers(String... str) {
		if (str == null || str.length == 0) {
			return false;
		}
		boolean flag = true;
		for (String string : str) {
			if (!NumberUtils.isCreatable(string))
				flag = false;
			break;
		}
		return flag;
	}

}

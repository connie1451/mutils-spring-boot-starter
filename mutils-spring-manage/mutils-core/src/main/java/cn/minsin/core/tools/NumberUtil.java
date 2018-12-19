package cn.minsin.core.tools;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtil extends NumberUtils {

	public static boolean isNumbers(String... str) {
		try {
			for (String string : str) {
				if (!NumberUtils.isCreatable(string))
					return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

package com.odwallet.common.util;

import java.util.Random;

public class Rand {
	private static Rand inst;

	private Random random;

	private Rand() {
		this.random = new Random();
	}

	private static Rand get_instance() {
		if (Rand.inst == null) {
			Rand.inst = new Rand();
		}

		return Rand.inst;
	}

	public static int RandNum(int num) {

		if (num > 0) {
			return Rand.get_instance().random.nextInt(num);
		}

		return 0;
	}

	public static int RandNum(int min, int max) {
		if (min > 0 && max > 0) {
			return Math.abs(Rand.get_instance().random.nextInt(max + 1 - min))+ min;
		}
		return 0;
	}
	 
	public static int RandRate(int randNum, int rate) {
		if(RandNum(randNum) < rate) return 1;
		else return 0;
	}


	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ%-+#$";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
 
}

/**
 * 
 */
package com.taiji.admin.utils;

/**
 * 
 * sw-view com.taiji.admin.utils RandomUtil.java
 *
 * @author hsl
 *
 * 2019年11月24日 下午12:05:00
 *
 * desc:
 */
public class RandomUtil {

	//产生随机数
	public static int getRandom() {
		return (int)Math.floor(Math.random() * 100);
	}
	
	//产生指定大小范围随机数
	public static int getRandom(int size, int key) {
		if (key == -1)
			return getRandom();
		else
			return (int)Math.floor(Math.random() * size + key);
	}
	
	//产生不等概率随机数
	public static Object getUnequProbRandom(Object[][]nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += (Integer)nums[i][1];
		}
		int r = (int)Math.floor(Math.random() * sum + 1);
//		System.out.print("r = [" + r + "]");

		Object k = null;
		for (int i = 0; i < nums.length; i++) {
			if (r > (Integer)nums[i][1]) {
				r -= (Integer)nums[i][1];
			}
			else {
				k = nums[i][0];
				break;
			}
		}
//		System.out.print("  k = [" + k + "]");
		return k;
	}
	
	public static String randomString(int num) {
		StringBuffer sbf = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sbf.append((int)Math.floor(Math.random() * 9));
		}
		return sbf.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(randomString(12));
	}

}

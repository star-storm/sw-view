/**
 * 
 */
package com.taiji.admin.utils.encrypt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * sw-view com.taiji.admin.utils.encrypt Encrypt.java
 *
 * @author hsl
 *
 * 2019年12月7日 下午9:33:08
 *
 * desc:
 */
public class Encrypt {
	
	public static void encryptFile(String sourcefile, String destfile) throws Exception {
		BufferedInputStream reader;
		try {
			int readCount;
			reader = new BufferedInputStream(new FileInputStream(sourcefile));
			BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(destfile));
			byte[] bytes = new byte[127];

			while ((readCount = reader.read(bytes)) != -1) {
				byte[] encryptByte = RandomTreatByByte(bytes, (byte) readCount, -38);
				writer.write(encryptByte, 0, readCount);
			}
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String encryptString1(String srcString) {
		int readCount = srcString.length();

		byte[] encryptByte = RandomTreatByByte(srcString.getBytes(), (byte) readCount, 44);
		String result = "";
		try {
			result = new String(encryptByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String encryptField(String str) {
		String result = "";

		for (int i = 0; i < str.length(); ++i) {
			result = result + (char) ((str.charAt(i) & 0xF) + 'a');
			result = result + (char) ((str.charAt(i) >> '\4' & 0xF) + 97);
			result = result + (char) ((str.charAt(i) >> '\b' & 0xF) + 97);
			result = result + (char) ((str.charAt(i) >> '\f' & 0xF) + 97);
			result = result + "0";
		}

		return result;
	}

	public static byte[] encryptByte(byte[] str) {
		byte[] buf = new byte[5120];
		int c = 0;
		for (int i = 0; i < str.length; ++i) {
			buf[(c++)] = (byte) ((str[i] & 0xF) + 97);
			buf[(c++)] = (byte) ((str[i] >> 4 & 0xF) + 97);
			buf[(c++)] = (byte) ((str[i] >> 8 & 0xF) + 97);
			buf[(c++)] = (byte) ((str[i] >> 12 & 0xF) + 97);
			buf[(c++)] = 48;
		}

		return buf;
	}

	public static String encryptString(String str) {
		String sn = "ziyu";
		int[] snNum = new int[str.length()];
		String result = "";
		String temp = "";

		int i = 0;
		for (int j = 0; i < str.length();) {
			if (j == sn.length())
				j = 0;
			snNum[i] = (str.charAt(i) ^ sn.charAt(j));

			++i;
			++j;
		}

		for (int k = 0; k < str.length(); ++k) {
			if (snNum[k] < 10) {
				temp = "00" + snNum[k];
			} else if (snNum[k] < 100) {
				temp = "0" + snNum[k];
			}

			result = result + temp;
		}
		return result;
	}

	public static String getEncryptString(String hexStrArr) {
		String result = "";
		if ((hexStrArr == null) || ((hexStrArr != null) && (hexStrArr.equalsIgnoreCase(""))))
			return "";
		for (int i = 0; i < hexStrArr.length() - 4; i += 5) {
			result = result + (char) (hexStrArr.charAt(i) - 'a' | hexStrArr.charAt(i + 1) - 'a' << 4
					| hexStrArr.charAt(i + 2) - 'a' << 8 | hexStrArr.charAt(i + 3) - 'a' << 12);
		}

		return result;
	}

	public static byte[] getEncryptByte(byte[] hexStrArr) {
		byte[] buf = new byte[1024];
		if (hexStrArr == null)
			return buf;
		int j = 0;
		for (int i = 0; i < hexStrArr.length - 4; i += 5) {
			buf[(j++)] = (byte) (hexStrArr[i] - 97 | hexStrArr[(i + 1)] - 97 << 4 | hexStrArr[(i + 2)] - 97 << 8
					| hexStrArr[(i + 2)] - 97 << 12);
		}

		return buf;
	}

	private static byte[] RandomTreatByByte(byte[] phead, byte dwNum, int bRandom) {
		if (phead == null)
			return null;
		@SuppressWarnings("unused")
		char[] result = (char[]) null;
		for (byte i = 0; i < dwNum; i = (byte) (i + 1)) {
			byte tmp20_18 = i;
			byte[] tmp20_17 = phead;
			tmp20_17[tmp20_18] = (byte) (tmp20_17[tmp20_18] ^ bRandom);
			bRandom = (byte) (bRandom + i);
		}

		return phead;
	}

	public static String ssoEncrypt(String str, boolean flag) {
		char[] A = new char[62];
		A[0] = 'U';
		A[1] = 'o';
		A[2] = 'g';
		A[3] = '8';
		A[4] = 'X';
		A[5] = '3';
		A[6] = 'D';
		A[7] = 'J';
		A[8] = 'l';
		A[9] = '5';
		A[10] = 't';
		A[11] = 'I';
		A[12] = 'd';
		A[13] = 'v';
		A[14] = 'x';
		A[15] = 'm';
		A[16] = 'W';
		A[17] = 'A';
		A[18] = 'f';
		A[19] = 'C';
		A[20] = 'u';
		A[21] = 'L';
		A[22] = 'Y';
		A[23] = 'P';
		A[24] = 'H';
		A[25] = '4';
		A[26] = 'c';
		A[27] = 'Q';
		A[28] = 'b';
		A[29] = 'z';
		A[30] = 'M';
		A[31] = '6';
		A[32] = 'B';
		A[33] = 'V';
		A[34] = 'k';
		A[35] = 'h';
		A[36] = '2';
		A[37] = 'T';
		A[38] = 'O';
		A[39] = 'R';
		A[40] = '7';
		A[41] = 'E';
		A[42] = 'N';
		A[43] = 'n';
		A[44] = 'K';
		A[45] = 's';
		A[46] = 'p';
		A[47] = 'a';
		A[48] = 'i';
		A[49] = 'w';
		A[50] = '1';
		A[51] = 'r';
		A[52] = 'G';
		A[53] = 'j';
		A[54] = 'F';
		A[55] = '9';
		A[56] = 'S';
		A[57] = '0';
		A[58] = 'q';
		A[59] = 'y';
		A[60] = 'Z';
		A[61] = 'e';
		String result = "";
		for (int i = 0; i < str.length(); ++i) {
			if (flag) {
				if ((str.charAt(i) <= '9') && (str.charAt(i) >= '0'))
					result = result + A[java.lang.Character.getNumericValue(str.charAt(i))];

				if ((str.charAt(i) <= 'z') && (str.charAt(i) >= 'a'))
					result = result + A[(new Integer(str.charAt(i) - 'a').intValue() + 10)];

				if ((str.charAt(i) <= 'Z') && (str.charAt(i) >= 'A'))
					result = result + A[(new Integer(str.charAt(i) - 'A').intValue() + 36)];
			} else {
				int j = 0;
				for (j = 0; j < 62; ++j)
					if (str.charAt(i) == A[j])
						break;

				if ((j <= 9) && (j >= 0))
					result = result + Integer.valueOf(j).toString();

				if ((j < 36) && (j >= 10))
					result = result + (char) Integer.valueOf(j - 10 + 97).intValue();
				if ((j < 62) && (j >= 36))
					result = result + (char) Integer.valueOf(j - 36 + 65).intValue();

			}

		}

		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//密码
		String s = "123456";
		String pwd = encryptString(s);
		System.out.println(pwd);
//		String s = "123456";
//		String pwd = Encrypt.encryptString(s);
//		System.out.println(pwd);
//		String s = "8538Jtx8JomDXtI5I3XXUUIUoomDmJm8";//no:39537ae371f64ab9b54400b011f6f7f3
//		String pwd = ssoEncrypt(s, false);//yes:39537ae371f64ab9b544008011f6f7f3
//		System.out.println(pwd);
		
//		System.out.println(getEncryptString("39537ae371f64ab9b544008011f6f7f3"));
//		System.out.println(encryptString("123456"));
		
//		String s = "123456";
//		String pwd = ssoEncrypt(s, true);
//		System.out.println(pwd);
//		String s = "445bf818696841ad971aed65aeb7dfb5";
//		String pwd = ssoEncrypt(s, true);
//		System.out.println(pwd);
		
		//32位码加密
		String ss = "39537ae371f64ab9b544008011f6f7f3";
		String ticket = ssoEncrypt(ss, true);
		System.out.println("encode="+ticket);
		//32位码解密
		String sss = ssoEncrypt(ticket, false);
		System.out.println("decode="+sss);

	}

}



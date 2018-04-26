package project001.util;

import java.security.MessageDigest;

public class Md5 {
	private static String toHex(byte buffer[]) {  
        StringBuffer sb = new StringBuffer(buffer.length * 2);  
        for (int i = 0; i < buffer.length; i++) {  
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));  
            sb.append(Character.forDigit(buffer[i] & 15, 16));  
        }  
  
        return sb.toString();  
    }
	public static String pdwMD5(String str) {  
        try {  
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.update(str.getBytes());  
            return toHex(messageDigest.digest());
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;
    }  

}
package com.mit.util;

import java.util.Random;

//import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;
@Component
public class RandomUtil {
	public static String getRandomString(int length) { //length表示生成字符串的长度  !@#$%^&*_+=-?{}[]<>().,/|:;`~'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
	    String base = "0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }
	
	public static void main(String[] args) {
//		System.out.println(RandomUtils.nextInt(1000, 10000));
//		System.out.println(new String(RandomUtils.nextBytes(100)));
		Random r=new Random();
		r.setSeed(10000000);
		System.out.println(r.nextInt());
	}
	

}

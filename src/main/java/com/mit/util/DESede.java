package com.mit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESede {
	
	public void generateKey(File keyFile){
		try {
			KeyGenerator kg = KeyGenerator.getInstance("DESede");
			kg.init(168);
			SecretKey k = kg.generateKey();
			byte[] b=k.getEncoded();
			OutputStream os=new FileOutputStream(keyFile);
			os.write(b);
			os.flush();
			os.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public byte[] encrypt(String data,byte[] key){
		try {
			SecretKey k = new SecretKeySpec(key, "DESede");// 得到密锁

			Cipher cp = Cipher.getInstance("DESede");// 生成加密工厂对象,DESede为加密算法
			cp.init(Cipher.ENCRYPT_MODE, k);// 初始化加密器,Cipher.ENCRYPT_MODE指定加密,k为密锁
			byte[] rb = data.getBytes();// 得到明文大小
			
			byte[] cb = cp.doFinal(rb);
			return cb;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String decrypt(byte[] data,byte[] key){
		try {
			SecretKey k = new SecretKeySpec(key, "DESede");// 将密锁存在SecretKey对象中
			
			Cipher cp = Cipher.getInstance("DESede");// 生成解密工厂,DESede为算法
			cp.init(Cipher.DECRYPT_MODE, k);// 初始化解密,Cipher.DECRYPT_MODE为解密方式,k为密锁
			
			byte[] m = cp.doFinal(data);// 解密,将结果存在m字节数组中
			return new String(m,"utf-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args[]) throws Exception {
		System.out.println("7Lw4f/Qq5Q09bTTVlPvj4Oy8OH/0KuUN".length());
//		new String(null, Charset.forName("GBK"));
		String s="1234567812345678123456781234567";
		byte[] a=new DESede().encrypt("aaaa", Base64.decodeBase64(s));
		System.out.println(Base64.encodeBase64String(a));
		

		//String b3=new DESede().decrypt(com.alibaba.druid.util.Base64.base64ToByteArray("h3JsClP+wW4="), com.alibaba.fastjson.util.Base64.decodeFast("12345678123456781234567812345678"));
		//System.out.println(b3);
		
		
//		byte[] en=new DESede().encrypt("你好",new File("D:\\DESede.key"));
//		for(byte b: en){
//			System.out.print(b);
//			System.out.print(",");
//		}
		
//		String de=new DESede().decrypt(new byte[]{-75,-46,1,98,78,-93,-68,-115},new File("D:\\DESede.key"));
//		System.out.println(de);
//
//		new DESede().generateKey(new File("D:\\DESede.key"));
		System.out.println("--------------------------");
		System.out.println(new String(encrypt("aaaa".getBytes(), "123456789")));
		System.out.println(Base64.encodeBase64String(encrypt("aaa".getBytes(), "123456789")));
	}
	
	
	/**
	* 加密
	* @param datasource byte[]
	* @param password String
	* @return byte[]
	*/
	public static byte[] encrypt(byte[] datasource, String password) { 
	try{
	SecureRandom random = new SecureRandom();
	DESKeySpec desKey = new DESKeySpec(password.getBytes());
	//创建一个密匙工厂，然后用它把DESKeySpec转换成
	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	SecretKey securekey = keyFactory.generateSecret(desKey);
	//Cipher对象实际完成加密操作
	Cipher cipher = Cipher.getInstance("DES");
	//用密匙初始化Cipher对象
	cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
	//现在，获取数据并加密
	//正式执行加密操作
	return cipher.doFinal(datasource);
	}catch(Throwable e){
	e.printStackTrace();
	}
	return null;
	}
}
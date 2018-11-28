package com.mit.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import net.rubyeye.xmemcached.utils.ByteUtils;

//import org.apache.activemq.protobuf.BufferInputStream;
import org.apache.commons.codec.binary.Base32;
//import org.apache.tools.ant.taskdefs.Sleep;
//import org.bouncycastle.jce.provider.JDKMessageDigest.SHA1;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

//import com.alibaba.druid.util.Base64;

public class Md5AndSha1 {
	public static byte[] getMD5Code(File file){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			return md.digest(ByteTool.file2Byte(file));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] getMD5Code(String data){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			return md.digest(data.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] getSha1Code(String data){
		try {
			MessageDigest md=MessageDigest.getInstance("sha1");
			return md.digest(data.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		byte[] bb=Md5AndSha1.getSha1Code("12314593235131763737747");
		System.out.println(ByteTool.bytesToHexStr(bb));
		
//		System.out.println(MD5Encoder.encode(bb));
//		System.out.println(Md5Crypt.apr1Crypt(bb));
//		System.out.println(Md5Crypt.md5Crypt(bb));
		
//		for(byte b:bb){
//			System.out.print(b);
//			System.out.print(",");
//		}
//		System.out.println(new Base32().encodeAsString(bb));
//		System.out.println(new Base32().encodeToString(bb));
//		System.out.println(1);
//		System.out.println(org.apache.commons.net.util.Base64.encodeBase64String(bb));
//		System.out.println(org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(bb));
//		System.out.println(org.apache.commons.codec.binary.Base64.encodeBase64String(bb));
//		System.out.println(Base64.byteArrayToBase64(bb));
	}
	@Test
	public void test() throws IOException {
		File f=new File("f:/a.txt");
		FileReader fis=new FileReader(f);
		BufferedReader br=new BufferedReader(fis);
		
		File fw=new File("f:/b.txt");
		FileWriter out=new FileWriter(fw);
		BufferedWriter bw=new BufferedWriter(out);
		int i=0;
		while (br.ready()) {
			String sr=br.readLine();
			String s2="";
			if(!sr.equals("123456")){
				String s=ByteTool.bytesToHexStr(new Md5AndSha1().getMD5Code(sr));
				System.out.print(s);
				System.out.print("="+i+++"=");
				s2=s.substring(24)+s.substring(8, 24)+s.substring(0, 8);
				System.out.println(s2);
			}else{
				s2="f20f883e49ba59abbe56e057e10adc39";
			}
			bw.write(s2);
			bw.newLine();
			bw.flush();
		}
		//e10adc39 49ba59abbe56e057 f20f883e=1370=7f20f883 e49ba59abbe56e05 e10adc39
		//e10adc39 49ba59abbe56e057 f20f883e=1370=f20f883e   9ba59abbe56e057 e10adc39
	    //e10adc39 49ba59abbe56e057 f20f883e=1370=f20f883e 49ba59abbe56e057 e10adc39
	}
}
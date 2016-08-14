package com.chenxushao.crypto;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.chenxushao.common.crypto.Cryptos;
import com.chenxushao.common.crypto.Digests;
import com.chenxushao.common.crypto.Encodes;
import com.chenxushao.common.exceptions.Exceptions;

public class MD5Demo {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		byte[] bytes = Digests.md5("chenxushao");
		System.out.println(Encodes.encodeHex(bytes));
		System.out.println(Encodes.encodeHex(bytes).length());
		
		bytes = Digests.sha1("chenxushao");
		System.out.println(Encodes.encodeHex(bytes));
		System.out.println(Encodes.encodeHex(bytes).length());
		
		
		
		bytes = Cryptos.hmac("cxs".getBytes(),"mm".getBytes(), "HmacSHA256");
		
		System.out.println(Encodes.encodeHex(bytes));
		System.out.println(Encodes.encodeHex(bytes).length());
		
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		
		System.out.println("keyGenerator: "+ keyGenerator);
		
		Mac mac = Mac.getInstance("HmacSHA256");
		System.out.println(mac);
		
		MessageDigest digest = MessageDigest.getInstance("sha-1");
		System.out.println(digest);

	}

	
	private static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, "DES");
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(mode, secretKey, ivSpec);
			return cipher.doFinal(input);
		} catch (GeneralSecurityException e) {
			throw Exceptions.unchecked(e);
		}
	}
	
}

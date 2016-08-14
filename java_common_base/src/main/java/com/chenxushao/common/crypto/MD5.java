package com.chenxushao.common.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.chenxushao.common.bytes.HexUtils;

public class MD5 {
	public static String getMD5HexString(String clear)
			throws NoSuchAlgorithmException {
		byte[] enc = MessageDigest.getInstance("MD5").digest(clear.getBytes());
		return HexUtils.encodeHexStr(enc, 0, enc.length);
	}
}
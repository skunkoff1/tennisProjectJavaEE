package com.tennis.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

public class EncryptDao {
	
	public static String sha(String input) {
		String sha = null;
		try {
			MessageDigest msDigest = MessageDigest.getInstance("SHA-256");
			msDigest.update(input.getBytes("UTF-8"), 0, input.length());
			sha = DatatypeConverter.printHexBinary(msDigest.digest());
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			Logger.getLogger(EncryptDao.class.getName()).log(null);
		}
		return sha;
	}

}

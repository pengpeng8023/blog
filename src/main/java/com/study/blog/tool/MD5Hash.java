package com.study.blog.tool;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Hash {
	
	public static String getMd5Hash(String password,String username){
		Md5Hash md5Hash = new Md5Hash(password,username,3);
		return md5Hash.toString();
	}
	
	public static void main(String[] args) {
		/**
		 * source  传入的明码
		 * salt 盐  自己规定的加密片段
		 * hashIterations 哈希值的计算次数
		 */
		
		Md5Hash md5Hash = new Md5Hash("admin", "admin", 3);
		String password = md5Hash.toString();
		System.out.println(password);
	}
}
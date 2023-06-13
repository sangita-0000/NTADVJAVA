package com.nt.jdbc;

public class StringBuffer01 {

	public static void main(String[] args) {
		/*
		 * StringBuffer sb=new StringBuffer(); System.out.println(sb.capacity());
		 * 
		 * sb.ensureCapacity(17); System.out.println(sb.capacity());
		 * 
		 * sb.ensureCapacity(100); System.out.println(sb.capacity());
		 * 
		 * 
		 * StringBuffer sb=new StringBuffer("abcdefghijk");
		 * System.out.println(sb.capacity()); System.out.println(sb.length());
		 * System.out.println(sb); System.out.println("Sangita");
		 */
		String s=new String("a");
		String s1=s.concat("b");
		System.out.println(s==s1);
		System.out.println(s);
		System.out.println(s1);
		s1.concat("c");
		System.out.println(s1);
		s1=s1.concat("d");
		System.out.println(s1);
		
		StringBuffer sb=new StringBuffer("a");
		StringBuffer sb2 = sb.append("b");
		System.out.println(sb==sb2);
		System.out.println(sb);
		System.out.println(sb2);
		sb2.append("c");
		System.out.println(sb2);
		
	}

}

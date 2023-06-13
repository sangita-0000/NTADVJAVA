package com.nt.jdbc;

public class StringRevers {

	public static void main(String[] args) {


		String s="madam";
		/*StringBuilder b=new StringBuilder(s);
		b=b.reverse();
		if(s.equals(b)) {
			System.out.println("Pallindrome");
		}
		else {
			System.out.println("Not pallindrome");
		}		*/
		String rev="";
		for(int i=s.length()-1;i<=0;i--) {
			char ch=rev.charAt(i);
			rev=ch+rev;
		}
		if(s.equals(rev)) {
			System.out.println("Palindrome");
		}
		else {
			System.out.println("Not palindrome");
		}
	}

}

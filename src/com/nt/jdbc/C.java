package com.nt.jdbc;
class A{
	void m1(){
		System.out.println("A m1");
	}
}
class B extends A{
	void m1(){
		System.out.println("B m1");
	}
}
public class C {
	static {
		System.out.println("Sb1");
	}
	public static void main(String[] args) {
		A a=new B();
		a.m1();
	}
	static {
	System.out.println("SB2");
	}
}

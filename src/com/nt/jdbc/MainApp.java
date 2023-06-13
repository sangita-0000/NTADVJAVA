package com.nt.jdbc;

import com.nt.jdbc.WishMassageGenerator;
public class MainApp {

	public static void main(String[] args) {
		WishMassageGenerator generator=new WishMassageGenerator();
		String result=generator.sayHello("raja");
		System.out.println(result);
	}

}

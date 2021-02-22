package com.uqac.aspects;

import java.util.Scanner;

import javax.management.RuntimeErrorException;

public aspect SecuritéAspect {
	private String login;
	private String pwd;
	pointcut securite(): call(*..App.new(..));
	
	Object around() : securite() {
		if(login == null){
			Scanner sc = new Scanner(System.in);
			System.out.println("donner le login : ");
			String login = sc.next();
			
			System.out.println("donner le password : ");
			String pwd = sc.next();
			
			if(login.equals("root") && pwd.equalsIgnoreCase("root")){
				this.login=login;
				this.pwd=pwd;
				return proceed();
			}else{
				throw new RuntimeException("Accés réfusé");
			}
			
		}
		return null;
	}

}

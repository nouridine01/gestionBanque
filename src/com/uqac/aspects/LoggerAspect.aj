package com.uqac.aspects;

import java.util.logging.Logger;

public aspect LoggerAspect {
	long t1;
	long t2;
	Logger log = Logger.getLogger(this.getClass().getName());
	//toute les meth de tout les pack de toute les class et quelque soit le type de retour : * *.*.*()
	//pointcut log() : call(* metier.Compte.*(..));
	pointcut log() : initialization( metier..Compte.new(..)) || call(* *.metier.Compte.*(..));
	
	
	before():log(){
		System.out.println("***************************");
		log.info("avant execution de la methode "+thisJoinPoint.getSignature());
		t1=System.currentTimeMillis();
	}
	
	after():log(){
		log.info("apres execution de la methode "+thisJoinPoint.getSignature());
		t2 = System.currentTimeMillis();
		log.info("la durée d'execution de la methode = "+(t2-t1));
		System.out.println("***************************");
	}
	

}

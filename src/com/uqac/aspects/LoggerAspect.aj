package com.uqac.aspects;

import java.io.File;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;  
import java.util.Date; 

public aspect LoggerAspect {
	/*long t1;
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
	}*/
	
	pointcut sauvegardeOperation(): execution (* virement(..)) || execution(* retrait(..)) || execution(* versement(..));
	after() : sauvegardeOperation() {
		String methodeName = thisJoinPoint.getSignature().getName();
		File file = new File("journalDesOperations.txt");
		String typeOperation= "";
		String compteSource= "";
		String compteDestination= "";
		String montantOperation ="";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
		System.out.println(formatter.format(date));
		
		if(methodeName.matches("virement")) {//Long codeFrom, Long codeTo, double montant
			// Récupération des paramètres des méthodes de Banque Service
			Long codeFrom = (Long)thisJoinPoint.getArgs()[0];
			Long codeTo = (Long)thisJoinPoint.getArgs()[1];
			double montant = (double)thisJoinPoint.getArgs()[2];
			
			//Casting en string 
			typeOperation= "Virement <->";
			compteSource= String.valueOf(codeFrom);
			compteDestination= String.valueOf(codeTo);
			montantOperation = String.valueOf(montant);
			
		}else if(methodeName.matches("retrait")) {//Long codeFrom, double montant
			// Récupération des paramètres des méthodes de Banque Service
			Long codeFrom = (Long)thisJoinPoint.getArgs()[0];
			
			double montant = (double)thisJoinPoint.getArgs()[1];
			
			
			//Casting en string 
			typeOperation= "Retrait -";
			compteSource= String.valueOf(codeFrom);
			montantOperation = String.valueOf(montant);
			
		}else if(methodeName.matches("versement")) {//Long codeTo, double montant
			
			// Récupération des paramètres des méthodes de Banque Service
			
			Long codeTo = (Long)thisJoinPoint.getArgs()[0];
			double montant =(double) thisJoinPoint.getArgs()[1];
			
			//Casting en string 
			typeOperation= "Versement +";
			compteDestination= String.valueOf(codeTo);
			montantOperation = String.valueOf(montant);
		}
		
		String data = formatter.format(date)+", "+typeOperation+", "+compteSource+", "+compteDestination+", "+montantOperation;
		GestionDesFichiers.saveData(file, data);
		
	}
	

}

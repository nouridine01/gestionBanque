package com.uqac.aspects;

public aspect TransactionAspect {
//Sur les m�thodes des DAO 
	pointcut validationTransaction() : call(* .ClientDao.*)|| call (* .CompteDao.*)|| call (* .UserDao.*);
	before(): validationTransaction(){
		
	}
	
}

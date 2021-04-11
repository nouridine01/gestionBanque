package com.uqac.aspects;

public aspect TransactionAspect {
//Sur les méthodes des DAO 
	pointcut validationTransaction() : call(* .ClientDao.*)|| call (* .CompteDao.*)|| call (* .UserDao.*);
	before(): validationTransaction(){
		
	}
	
}

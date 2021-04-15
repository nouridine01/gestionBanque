package com.uqac.aspects;

import java.util.List;

import javax.persistence.Query;

import com.uqac.entities.Compte;
import com.uqac.utils.TransactionManager;

public aspect TransactionAspect {
//Sur les m�thodes des DAO 
	pointcut validationTransaction() : call(* find(..)) || call (* create(..))  //|| execution(* .*.ClientDao.*())
	|| call (* update(..) ) || call (* delete(..)) || call(* findAll()) || call(* chercher(..));//|| call (* .CompteDao.*)|| call (* .UserDao.*);
	before(): validationTransaction(){
		
	}
	
	

	
	
}

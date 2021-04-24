package com.uqac.aspects;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.uqac.entities.Compte;
import com.uqac.utils.TransactionManager;

public aspect TransactionAspect {
//Sur les m�thodes des DAO 
	pointcut transaction(): call(* com.uqac.dao.DAO+.*(..));
	Object around() : transaction(){ 
		TransactionManager tm = new TransactionManager();
		EntityTransaction et = tm.getEntityTransaction();
		try {
			
			Object ret= proceed(); 
			et.commit();
			return ret;
		}catch (Exception e) {
			// TODO: handle exception
			et.rollback();
		}
		
		return null;
	}

	before(): transaction(){
		
	}
	
	

	
	
}

package com.uqac.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.uqac.dao.Singleton;

public class TransactionManager implements ITransactionManager {
	private static EntityManager em;
	

	public static EntityManager getEm() {
		return em;
	}

	@Override
	public EntityTransaction getEntityTransaction() {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Singleton.getEntityManagetFactory();
		em = emf.createEntityManager(); 
		
		return em.getTransaction();
	}

	@Override
	public void commit(EntityTransaction t) {
		// TODO Auto-generated method stub
		t.commit();
		em.close();
	}

	@Override
	public void rollback(EntityTransaction t) {
		// TODO Auto-generated method stub
		t.rollback();
		em.close();
	}
	
	

}

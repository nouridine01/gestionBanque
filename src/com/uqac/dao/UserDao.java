package com.uqac.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.uqac.entities.User;


public class UserDao extends DAO<User>{
	
	public static User userConnected=null;
	
	@Override
	public User find(long id) {
		EntityManagerFactory emf = Singleton.getEntityManagetFactory();
		EntityManager em = emf.createEntityManager(); 
		em.getTransaction().begin();
		User u = em.find(User.class, id); 
		em.getTransaction().commit();
		return u;
	}

	public static User getUserConnected() {
		return userConnected;
	}

	public static void setUserConnected(User userConnected) {
		UserDao.userConnected = userConnected;
	}

	@Override
	public User create(User obj) {
		EntityManagerFactory emf = Singleton.getEntityManagetFactory();
		EntityManager em = emf.createEntityManager(); 
		em.getTransaction().begin();
		em.persist(obj);
		Query query=em.createQuery("select t from User t order by t.id desc");
		em.getTransaction().commit();
		return (User) query.getResultList().get(0);
		
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Singleton.getEntityManagetFactory();
		EntityManager em = emf.createEntityManager(); 
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
		return obj;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Singleton.getEntityManagetFactory();
		EntityManager em = emf.createEntityManager(); 
		em.getTransaction().begin();
		obj =em.find(User.class, obj.getId());
		em.remove(obj);
		em.getTransaction().commit();
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Singleton.getEntityManagetFactory();
		EntityManager em = emf.createEntityManager();
		Query query=em.createQuery("SELECT s FROM User s"); 
		List<User> list=(List<User>)query.getResultList(); 
		return list;
	}

	@Override
	public List<User> chercher(String mc) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Singleton.getEntityManagetFactory();
		EntityManager em = emf.createEntityManager(); 
		Query query=em.createQuery("select t from User t where t.nom like :mc or t.prenom like :mp"); 
		query.setParameter("mc", mc);
		query.setParameter("mp", mc);
		return query.getResultList();
			
	}
	
	public static User connexion(String login,String pwd) {
		EntityManagerFactory emf = Singleton.getEntityManagetFactory();
		EntityManager em = emf.createEntityManager(); 
		Query query=em.createQuery("select t from User t where t.login=:log and t.password=:mp "); 
		query.setParameter("log", login);
		query.setParameter("mp", pwd);
		try {
			User u = (User) query.getResultList().get(0);
			return u;
		}catch(IndexOutOfBoundsException e) {
			return null;
		}
		
	}
}

package com.pmf.musicmax.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmf.musicmax.model.User;

@Repository
@Transactional
public class UserRepo {

	@PersistenceContext
	EntityManager em;
	
	public User login(String username, String password) {
		User user =  null;
		try{
			Query q = em.createQuery("select u from User u where u.username = :username and u.password = :password");
			q.setParameter("username", username);
			q.setParameter("password", password);
			try{
				user = (User) q.getSingleResult();
				return user;
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}

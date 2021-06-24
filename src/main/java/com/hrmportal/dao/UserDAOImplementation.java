package com.hrmportal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hrmportal.entity.User;

/*
 * @author sakshilavalkar
 * This is a repository which implements UserDAO interface
 */
@Repository
public class UserDAOImplementation implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	/*
	 * @param User user
	 * @see com.hrmportal.dao.UserDAO#getUser(com.hrmportal.entity.User)
	 * This method fetch particular user from the database
	 * @return User user
	 */
	@Transactional
	public User getUser(User user) {
		System.out.println("in get user");
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
//		User u=session.find(User.class, user.getUserId());
		Query<User> query = session.createQuery("from User where user_id = :userid" ,User.class);
		query.setParameter("userid", user.getUserId());
		List<User> result = query.list();
		System.out.println("after querying "+user);
		if(result.size()>0) {
			return result.get(0);
		}else {
			return null;
		}
	}
	
	
	/*
	 * @param User user
	 * @see com.hrmportal.dao.UserDAO#loginUser(com.hrmportal.entity.User)
	 * This method handles login action
	 * @return boolean
	 */
	public boolean loginUser(User user) {
		System.out.println("In dao login");
		User u=getUser(user);
		if(u!=null) {
			if(u.getPassword().equals(user.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	
	/*
	 * @param User user
	 * @see com.hrmportal.dao.UserDAO#createUser(com.hrmportal.entity.User)
	 * This method creates new user in the database
	 * @return boolean
	 */
	@Transactional
	public boolean createUser(User user) {
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		User u=getUser(user);
		if(u==null) {
			session.save(user);
			return true;
		}
		else {
			return false;
		}
	}
}

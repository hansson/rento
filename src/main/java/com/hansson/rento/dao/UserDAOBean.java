package com.hansson.rento.dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hansson.rento.entities.Role;
import com.hansson.rento.entities.User;

public class UserDAOBean implements UserDAO, UserDetailsService {

	private SessionFactory mSessionFactory;

	@Override
	public User create(User user) {
		Session session = mSessionFactory.getCurrentSession();
		return (User) session.merge(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String username) {
		Session session = mSessionFactory.getCurrentSession();
		List<User> userList = new LinkedList<User>();
		Query query = session
				.createQuery("from User u where u.mUsername = :username");
		query.setParameter("username", username);
		userList = query.list();
		if (userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		User user = getUser(arg0);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, getAuthorites(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> getAuthorites(Role role) {
		List<GrantedAuthority> authList = new LinkedList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(Role.ADMIN.name()));
		authList.add(new SimpleGrantedAuthority(Role.USER.name()));
		return authList;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		mSessionFactory = sessionFactory;
	}
}

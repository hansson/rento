package org.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;

import org.hibernate.classic.Session;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

public class MockSessionFactory implements SessionFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8312206971520297279L;
	public static final String AUDIT_EVENT_DAO = "AUDIT_EVENT_DAO";
	public static final String DAILY_AUDIT_EVENT_DAO = "DAILY_AUDIT_EVENT_DAO";
	
	private String mType;

	public MockSessionFactory(String type) {
		this.mType = type;
	}

	@Override
	public Reference getReference() throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean containsFetchProfileDefinition(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void evict(Class arg0) throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void evict(Class arg0, Serializable arg1) throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void evictCollection(String arg0) throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void evictCollection(String arg0, Serializable arg1)
			throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void evictEntity(String arg0) throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void evictEntity(String arg0, Serializable arg1)
			throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void evictQueries() throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void evictQueries(String arg0) throws HibernateException {
		// TODO Auto-generated method stub

	}

	@Override
	public Map getAllClassMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getAllCollectionMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cache getCache() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassMetadata getClassMetadata(Class arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassMetadata getClassMetadata(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionMetadata getCollectionMetadata(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session getCurrentSession() throws HibernateException {
		return (Session) new MockSession(mType);
	}

	@Override
	public Set getDefinedFilterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterDefinition getFilterDefinition(String arg0)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statistics getStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Session openSession() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session openSession(Interceptor arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session openSession(Connection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session openSession(Connection arg0, Interceptor arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatelessSession openStatelessSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatelessSession openStatelessSession(Connection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

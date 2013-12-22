package org.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.jdbc.Work;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.type.Type;
import org.hibernate.classic.Session;

public class MockSession implements Session{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4214745186837190634L;
	private String mType;
	
	public MockSession(String type) {
		mType = type;
	}

	@Override
	public EntityMode getEntityMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.hibernate.Session getSession(EntityMode entityMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFlushMode(FlushMode flushMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FlushMode getFlushMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCacheMode(CacheMode cacheMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CacheMode getCacheMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection connection() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection close() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelQuery() throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDirty() throws HibernateException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDefaultReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDefaultReadOnly(boolean readOnly) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Serializable getIdentifier(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void evict(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object load(Class theClass, Serializable id, LockMode lockMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(Class theClass, Serializable id, LockOptions lockOptions)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(String entityName, Serializable id, LockMode lockMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(String entityName, Serializable id,
			LockOptions lockOptions) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(Class theClass, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(String entityName, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(Object object, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replicate(Object object, ReplicationMode replicationMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replicate(String entityName, Object object,
			ReplicationMode replicationMode) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Serializable save(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable save(String entityName, Object object)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(String entityName, Object object)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String entityName, Object object)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object merge(Object object) throws HibernateException {
		return object;
	}

	@Override
	public Object merge(String entityName, Object object)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void persist(String entityName, Object object)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String entityName, Object object)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lock(Object object, LockMode lockMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lock(String entityName, Object object, LockMode lockMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LockRequest buildLockRequest(LockOptions lockOptions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object object, LockMode lockMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object object, LockOptions lockOptions)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LockMode getCurrentLockMode(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction beginTransaction() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criteria createCriteria(Class persistentClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criteria createCriteria(Class persistentClass, String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criteria createCriteria(String entityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criteria createCriteria(String entityName, String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String queryString) throws HibernateException {
		return new MockQuery(mType);
	}

	@Override
	public SQLQuery createSQLQuery(String queryString)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createFilter(Object collection, String queryString)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query getNamedQuery(String queryName) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(Class clazz, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(Class clazz, Serializable id, LockMode lockMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(Class clazz, Serializable id, LockOptions lockOptions)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String entityName, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String entityName, Serializable id, LockMode lockMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String entityName, Serializable id,
			LockOptions lockOptions) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEntityName(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter enableFilter(String filterName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter getEnabledFilter(String filterName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disableFilter(String filterName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SessionStatistics getStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReadOnly(Object entityOrProxy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setReadOnly(Object entityOrProxy, boolean readOnly) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doWork(Work work) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Connection disconnect() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reconnect() throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reconnect(Connection connection) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFetchProfileEnabled(String name)
			throws UnknownProfileException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableFetchProfile(String name) throws UnknownProfileException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableFetchProfile(String name) throws UnknownProfileException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object saveOrUpdateCopy(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object saveOrUpdateCopy(Object object, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object saveOrUpdateCopy(String entityName, Object object)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object saveOrUpdateCopy(String entityName, Object object,
			Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String query) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String query, Object value, Type type)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String query, Object[] values, Type[] types)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate(String query) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate(String query, Object value, Type type)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate(String query, Object[] values, Type[] types)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection filter(Object collection, String filter)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection filter(Object collection, String filter, Object value,
			Type type) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection filter(Object collection, String filter, Object[] values,
			Type[] types) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String query) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String query, Object value, Type type)
			throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String query, Object[] values, Type[] types)
			throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Query createSQLQuery(String sql, String returnAlias,
			Class returnClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createSQLQuery(String sql, String[] returnAliases,
			Class[] returnClasses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Object object, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String entityName, Object object, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object object, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String entityName, Object object, Serializable id)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}


}

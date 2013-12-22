package org.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;

import com.hansson.rento.entities.AuditEvent;
import com.hansson.rento.entities.DailyAuditEvent;

public class MockQuery implements Query {
	
	private String mType;
	
	public MockQuery(String type) {
		mType = type;
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type[] getReturnTypes() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getReturnAliases() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getNamedParameters() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScrollableResults scroll() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScrollableResults scroll(ScrollMode scrollMode)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List list() throws HibernateException {
		if(mType.equals(MockSessionFactory.AUDIT_EVENT_DAO)) {
			return mockedAuditEventList();
		} else if (mType.equals(MockSessionFactory.DAILY_AUDIT_EVENT_DAO)) {
			return mockDailyAuditEventList();
		}
		return null;
	}

	private List mockDailyAuditEventList() {
		List<DailyAuditEvent> list = new LinkedList<DailyAuditEvent>();
		return list;
	}

	private List<AuditEvent> mockedAuditEventList() {
		List<AuditEvent> list = new LinkedList<AuditEvent>();
		return list;
	}

	@Override
	public Object uniqueResult() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate() throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Query setMaxResults(int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setFirstResult(int firstResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Query setReadOnly(boolean readOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setCacheable(boolean cacheable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setCacheRegion(String cacheRegion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setTimeout(int timeout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setFetchSize(int fetchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setLockOptions(LockOptions lockOptions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setLockMode(String alias, LockMode lockMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setComment(String comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setFlushMode(FlushMode flushMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setCacheMode(CacheMode cacheMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(int position, Object val, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(String name, Object val, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(int position, Object val)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameter(String name, Object val)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameters(Object[] values, Type[] types)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameterList(String name, Collection vals, Type type)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameterList(String name, Collection vals)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameterList(String name, Object[] vals, Type type)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setParameterList(String name, Object[] vals)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setProperties(Object bean) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setProperties(Map bean) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setString(int position, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setCharacter(int position, char val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setBoolean(int position, boolean val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setByte(int position, byte val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setShort(int position, short val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setInteger(int position, int val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setLong(int position, long val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setFloat(int position, float val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setDouble(int position, double val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setBinary(int position, byte[] val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setText(int position, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setSerializable(int position, Serializable val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setLocale(int position, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setBigDecimal(int position, BigDecimal number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setBigInteger(int position, BigInteger number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setDate(int position, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setTime(int position, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setTimestamp(int position, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setCalendar(int position, Calendar calendar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setCalendarDate(int position, Calendar calendar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setString(String name, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setCharacter(String name, char val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setBoolean(String name, boolean val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setByte(String name, byte val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setShort(String name, short val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setInteger(String name, int val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setLong(String name, long val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setFloat(String name, float val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setDouble(String name, double val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setBinary(String name, byte[] val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setText(String name, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setSerializable(String name, Serializable val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setLocale(String name, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setBigDecimal(String name, BigDecimal number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setBigInteger(String name, BigInteger number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setDate(String name, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setTime(String name, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setTimestamp(String name, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setCalendar(String name, Calendar calendar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setCalendarDate(String name, Calendar calendar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setEntity(int position, Object val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setEntity(String name, Object val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query setResultTransformer(ResultTransformer transformer) {
		// TODO Auto-generated method stub
		return null;
	}

}

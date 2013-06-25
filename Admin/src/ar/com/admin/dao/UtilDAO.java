package ar.com.admin.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UtilDAO extends HibernateDaoSupport {

	public Integer executeUpdateDelete(String sql) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.executeUpdate();
	}

	public List executeSelect(String sql) {
		Query query = getSession().createQuery(sql);
		List results = query.list();
		return results;
	}

	public Set<String> getAllMappedClasses() {
		Set<String> clases = new HashSet<String>();
		Map<String, ClassMetadata> map = (Map<String, ClassMetadata>) this
				.getSessionFactory().getAllClassMetadata();
		for (String entityName : map.keySet()) {
			SessionFactoryImpl sfImpl = (SessionFactoryImpl) this
					.getSessionFactory();
			String tableName = ((AbstractEntityPersister) sfImpl
					.getEntityPersister(entityName)).getTableName();
			// String str = entityName + "\t" + tableName;
			clases.add(tableName);
		}

		return clases;
	}
}
